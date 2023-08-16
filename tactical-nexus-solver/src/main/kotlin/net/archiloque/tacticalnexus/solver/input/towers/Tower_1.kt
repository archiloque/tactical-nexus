package net.archiloque.tacticalnexus.solver.input.towers

import javax.annotation.processing.Generated
import net.archiloque.tacticalnexus.solver.entities.Door
import net.archiloque.tacticalnexus.solver.entities.Enemy
import net.archiloque.tacticalnexus.solver.entities.Key
import net.archiloque.tacticalnexus.solver.entities.KeyOrDoorColor
import net.archiloque.tacticalnexus.solver.entities.Level
import net.archiloque.tacticalnexus.solver.entities.PlayerStartPosition
import net.archiloque.tacticalnexus.solver.entities.Staircase
import net.archiloque.tacticalnexus.solver.entities.Tower
import net.archiloque.tacticalnexus.solver.entities.Wall
import net.archiloque.tacticalnexus.solver.input.Items

@Generated
public class Tower_1 : Tower {
  private val fighters: Array<Enemy?> = arrayOf(null, Enemy(120, 55, 25, 2, Items.guard_piece),
      null, Enemy(320, 110, 40, 5, Items.guard_piece), )

  private val rangers: Array<Enemy?> = arrayOf(null, null, Enemy(200, 200, 10, 4, Items.red_potion),
      null, Enemy(200, 350, 20, 16, Items.red_potion), )

  private val levels: Array<Level> = arrayOf(Level(11, 17, arrayOf(arrayOf(null, null, null, null,
      null, null, null, null, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
      Wall.instance, null, null, null, null, ), arrayOf(null, null, null, null, null, null, null,
      Wall.instance, Items.guard_gem, Items.guard_potion, Items.guard_gem, rangers[2], null,
      Wall.instance, null, null, null, ), arrayOf(null, null, null, null, null, null, Wall.instance,
      Items.power_piece, Wall.instance, Items.guard_gem, Wall.instance, null, Wall.instance,
      Items.red_potion, Wall.instance, null, null, ), arrayOf(null, null, null, null, null,
      Wall.instance, Items.power_piece, Items.power_gem, Items.power_piece, fighters[3], null,
      Items.power_gem, fighters[1], Items.life_potion, Items.red_potion, Wall.instance, null, ),
      arrayOf(Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
      Wall.instance, Wall.instance, Items.power_piece, Wall.instance, Items.blue_potion,
      Wall.instance, null, Wall.instance, rangers[4], Wall.instance, Wall.instance, null, ),
      arrayOf(Wall.instance, null, PlayerStartPosition.instance, null, Items.power_gem,
      Items.life_potion, null, fighters[1], Items.blue_potion, fighters[1], Items.heavenly_potion,
      fighters[1], fighters[3], Key(KeyOrDoorColor.yellow), Door(KeyOrDoorColor.yellow),
      Staircase(Staircase.StaircaseDirection.up), Wall.instance, ), arrayOf(Wall.instance,
      Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
      Items.guard_piece, Wall.instance, Items.blue_potion, Wall.instance, null, Wall.instance,
      rangers[4], Wall.instance, Wall.instance, null, ), arrayOf(null, null, null, null, null,
      Wall.instance, Items.guard_piece, Items.guard_gem, Items.guard_piece, fighters[3], null,
      Items.power_gem, fighters[1], Items.life_potion, Items.red_potion, Wall.instance, null, ),
      arrayOf(null, null, null, null, null, null, Wall.instance, Items.guard_piece, Wall.instance,
      null, Wall.instance, null, Wall.instance, Items.red_potion, Wall.instance, null, null, ),
      arrayOf(null, null, null, null, null, null, null, Wall.instance, null, Items.power_potion,
      null, rangers[2], null, Wall.instance, null, null, null, ), arrayOf(null, null, null, null,
      null, null, null, null, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
      Wall.instance, null, null, null, null, ), )), Level(17, 17, arrayOf(arrayOf(Wall.instance,
      Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
      Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
      Wall.instance, Wall.instance, Wall.instance, Wall.instance, ), arrayOf(Wall.instance, null,
      Wall.instance, null, null, null, null, null, null, null, null, null, null, null, null,
      Items.power_card, Wall.instance, ), arrayOf(Wall.instance, null, Wall.instance, null, null,
      null, Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
      Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, ),
      arrayOf(Wall.instance, null, Wall.instance, null, null, null, null, null, null, null, null,
      null, null, null, null, Wall.instance, Wall.instance, ), arrayOf(Wall.instance, null, null,
      null, null, Wall.instance, null, Wall.instance, Wall.instance, null, Wall.instance,
      Wall.instance, Wall.instance, Wall.instance, null, Wall.instance, Wall.instance, ),
      arrayOf(Wall.instance, null, null, null, Wall.instance, Wall.instance, null, null,
      Wall.instance, null, null, null, null, Wall.instance, null, Wall.instance, Wall.instance, ),
      arrayOf(Wall.instance, null, null, null, null, Wall.instance, null, null, Wall.instance, null,
      Wall.instance, null, null, Wall.instance, null, Wall.instance, Wall.instance, ),
      arrayOf(Wall.instance, Wall.instance, Wall.instance, null, null, Wall.instance, Wall.instance,
      Wall.instance, Wall.instance, null, Wall.instance, Wall.instance, Wall.instance,
      Wall.instance, null, Wall.instance, Wall.instance, ), arrayOf(Wall.instance,
      Staircase(Staircase.StaircaseDirection.up), null, null, null, null, null, null, null, null,
      null, null, null, null, null, Staircase(Staircase.StaircaseDirection.down), Wall.instance, ),
      arrayOf(Wall.instance, Wall.instance, Wall.instance, null, null, Wall.instance, Wall.instance,
      Wall.instance, Wall.instance, null, Wall.instance, Wall.instance, Wall.instance,
      Wall.instance, null, Wall.instance, Wall.instance, ), arrayOf(Wall.instance, null, null, null,
      null, Wall.instance, null, null, Wall.instance, null, Wall.instance, null, null,
      Wall.instance, null, Wall.instance, Wall.instance, ), arrayOf(Wall.instance, null, null, null,
      Wall.instance, Wall.instance, null, null, null, null, Wall.instance, null, null,
      Wall.instance, null, Wall.instance, Wall.instance, ), arrayOf(Wall.instance, null, null, null,
      null, Wall.instance, Wall.instance, Wall.instance, Wall.instance, null, Wall.instance,
      Wall.instance, null, Wall.instance, null, Wall.instance, Wall.instance, ),
      arrayOf(Wall.instance, null, Wall.instance, null, null, null, null, null, null, null, null,
      null, null, null, null, Wall.instance, Wall.instance, ), arrayOf(Wall.instance, null,
      Wall.instance, null, null, null, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
      Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
      Wall.instance, ), arrayOf(Wall.instance, null, Wall.instance, null, null, null, null, null,
      null, null, null, null, null, null, null, null, Wall.instance, ), arrayOf(Wall.instance,
      Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
      Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
      Wall.instance, Wall.instance, Wall.instance, Wall.instance, ), )), )

  public val enemies: Array<Array<Enemy>> = arrayOf()

  override fun levels(): Array<Level> = levels

  override fun atk(): Int = 50

  override fun def(): Int = 0

  override fun hp(): Int = 1_000
}
