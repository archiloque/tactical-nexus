package net.archiloque.tacticalnexus.solver.database

import org.ktorm.database.Database

class Migrations {

    companion object {
        private val migrations = arrayOf(
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
            println("Running SQL migration [${migration}]")
            database.useConnection { conn ->
                conn.prepareStatement(readSqlFile("migrations/${migration}.sql")).use { statement ->
                    statement.execute()
                }
            }
        }

        private fun clearDatabase(database: Database) {
            database.useConnection { conn ->
                conn.prepareStatement(readSqlFile("migrations/count_tables.sql")).use { statement ->
                    statement.setString(1, "public")
                    val resultSet = statement.executeQuery()
                    resultSet.next()
                    val tablesCount = resultSet.getInt(1)
                    if (tablesCount != 0) {
                        println("Clearing database")
                        val clearQueries = readSqlFile("migrations/clear.sql")
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
