package net.archiloque.tacticalnexus.solver.database

import org.ktorm.database.Database

class DatabaseMigrations {

    companion object {
        val migrations = arrayOf(
            "001",
            "002",
            "003",
            "004",
            )

        fun run(database: Database) {
            clearDatabase(database)
            migrations.forEach { migration ->
                runMigration(database, migration)
            }
        }

        private fun runMigration(database: Database, migration: String) {
            database.useConnection { conn ->
                val migrationPath = "/migrations/${migration}.sql"
                println("Running SQL migration [${migrationPath}]")
                val migrationContent = database.javaClass.getResource(migrationPath).readText()
                conn.prepareStatement(migrationContent).use { statement ->
                    statement.execute()
                }
            }
        }

        private fun clearDatabase(database: Database) {
            database.useConnection { conn ->
                val sql = "SELECT count(*) FROM pg_catalog.pg_tables where schemaname = ?"
                conn.prepareStatement(sql).use { statement ->
                    statement.setString(1, "public")
                    val resultSet = statement.executeQuery()
                    resultSet.next()
                    val tablesCount = resultSet.getInt(1)
                    if (tablesCount != 0) {
                        println("Clearing database")
                        val clearQueries = database.javaClass.getResource("/migrations/clear.sql").readText()
                        clearQueries.split("\n").forEach { clearQuery ->
                            conn.prepareStatement(clearQuery).use { statement ->
                                statement.execute()
                            }
                        }
                    }
                }
            }
        }
    }
}
