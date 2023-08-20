package net.archiloque.tacticalnexus.solver.input.towers

import javax.`annotation`.processing.Generated
import kotlin.Array
import kotlin.Int
import kotlin.arrayOf
import net.archiloque.tacticalnexus.solver.entities.Door
import net.archiloque.tacticalnexus.solver.entities.Enemy
import net.archiloque.tacticalnexus.solver.entities.Exit
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
      null, Enemy(320, 110, 40, 5, Items.guard_piece), null, Enemy(600, 145, 65, 10,
      Items.guard_gem), null, null, Enemy(1000, 260, 85, 18, Items.guard_gem), null, null, null,
      Enemy(2000, 440, 120, 30, Items.guard_potion), )

  private val rangers: Array<Enemy?> = arrayOf(null, null, Enemy(200, 200, 10, 4, Items.red_potion),
      null, Enemy(200, 350, 20, 16, Items.red_potion), null, null, Enemy(800, 280, 40, 36,
      Items.blue_potion), null, null, Enemy(800, 540, 60, 64, Items.blue_potion), null, null,
      Enemy(800, 1050, 80, 100, Items.blue_potion), null, null, null, null, null, null, null, null,
      Enemy(3000, 2350, 360, 256, Items.heavenly_potion), )

  private val levels: Array<Level> = arrayOf(Level(9, 15, arrayOf(arrayOf(null, null, null, null,
      null, null, Wall.instance, Items.guard_gem, Items.guard_potion, Items.guard_gem, rangers[2],
      null, Wall.instance, null, null, ), arrayOf(null, null, null, null, null, Wall.instance,
      Items.power_piece, Wall.instance, Items.guard_gem, Wall.instance, null, Wall.instance,
      Items.red_potion, Wall.instance, null, ), arrayOf(null, null, null, null, Wall.instance,
      Items.power_piece, Items.power_gem, Items.power_piece, fighters[3], null, Items.power_gem,
      fighters[1], Items.life_potion, Items.red_potion, Wall.instance, ), arrayOf(Wall.instance,
      Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, Items.power_piece,
      Wall.instance, Items.blue_potion, Wall.instance, null, Wall.instance, rangers[4],
      Wall.instance, Wall.instance, ), arrayOf(null, PlayerStartPosition.instance, null,
      Items.power_gem, Items.life_potion, null, fighters[1], Items.blue_potion, fighters[1],
      Items.heavenly_potion, fighters[1], fighters[3], Key(KeyOrDoorColor.yellow),
      Door(KeyOrDoorColor.yellow), Staircase.up, ), arrayOf(Wall.instance, Wall.instance,
      Wall.instance, Wall.instance, Wall.instance, Wall.instance, Items.guard_piece, Wall.instance,
      Items.blue_potion, Wall.instance, null, Wall.instance, rangers[4], Wall.instance,
      Wall.instance, ), arrayOf(null, null, null, null, Wall.instance, Items.guard_piece,
      Items.guard_gem, Items.guard_piece, fighters[3], null, Items.power_gem, fighters[1],
      Items.life_potion, Items.red_potion, Wall.instance, ), arrayOf(null, null, null, null, null,
      Wall.instance, Items.guard_piece, Wall.instance, null, Wall.instance, null, Wall.instance,
      Items.red_potion, Wall.instance, null, ), arrayOf(null, null, null, null, null, null,
      Wall.instance, null, Items.power_potion, null, rangers[2], null, Wall.instance, null, null, ),
      )), Level(15, 15, arrayOf(arrayOf(Items.guard_deck, Wall.instance, null, Items.guard_piece,
      null, fighters[5], null, Items.power_gem, null, fighters[8], Key(KeyOrDoorColor.yellow),
      Items.power_potion, null, fighters[12], Items.power_card, ), arrayOf(fighters[8],
      Wall.instance, Items.guard_piece, Items.power_gem, Items.guard_piece, Wall.instance,
      Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
      Wall.instance, Wall.instance, Wall.instance, ), arrayOf(Key(KeyOrDoorColor.yellow),
      Wall.instance, null, Items.guard_piece, fighters[3], Items.blue_potion, null, null,
      fighters[3], null, Items.power_gem, Items.life_potion, null, rangers[2], Wall.instance, ),
      arrayOf(fighters[8], null, Items.heavenly_potion, null, Wall.instance,
      Door(KeyOrDoorColor.blue), Wall.instance, Wall.instance, null, Wall.instance, Wall.instance,
      Wall.instance, Wall.instance, Items.blue_potion, Wall.instance, ), arrayOf(null,
      Items.power_potion, null, Wall.instance, Wall.instance, Items.life_potion, Items.power_potion,
      Wall.instance, Items.power_gem, Door(KeyOrDoorColor.yellow), Items.guard_gem,
      Items.guard_potion, Wall.instance, Items.power_potion, Wall.instance, ), arrayOf(null, null,
      rangers[4], Items.power_potion, Wall.instance, Items.power_potion, Items.heavenly_potion,
      Wall.instance, null, Wall.instance, Items.guard_potion, Items.guard_gem, Wall.instance,
      fighters[1], Wall.instance, ), arrayOf(Wall.instance, Wall.instance, null, Items.blue_potion,
      Wall.instance, Wall.instance, Wall.instance, Wall.instance, null, Wall.instance,
      Wall.instance, Wall.instance, Wall.instance, null, Wall.instance, ), arrayOf(Exit.instance,
      Items.drop_of_dream_ocean, fighters[5], null, fighters[3], Items.power_gem, null, null,
      fighters[3], null, Items.life_potion, rangers[2], null, Items.heavenly_potion, Staircase.down,
      ), arrayOf(Wall.instance, Wall.instance, null, Items.blue_potion, Wall.instance,
      Wall.instance, Wall.instance, Wall.instance, null, Wall.instance, Wall.instance,
      Wall.instance, Wall.instance, null, Wall.instance, ), arrayOf(null, null, rangers[4],
      Items.guard_potion, Wall.instance, Items.power_potion, Items.power_gem, Wall.instance, null,
      Wall.instance, Items.guard_card, Items.guard_potion, Wall.instance, fighters[1],
      Wall.instance, ), arrayOf(null, Items.guard_potion, null, Wall.instance, Wall.instance,
      Items.power_gem, Items.power_potion, Door(KeyOrDoorColor.yellow), Items.guard_gem,
      Wall.instance, Items.guard_potion, Items.guard_card, Wall.instance, Items.guard_potion,
      Wall.instance, ), arrayOf(fighters[8], null, Items.heavenly_potion, null, Wall.instance,
      Wall.instance, Wall.instance, Wall.instance, null, Wall.instance, Wall.instance,
      Door(KeyOrDoorColor.blue), Wall.instance, Items.blue_potion, Wall.instance, ),
      arrayOf(Key(KeyOrDoorColor.blue), Wall.instance, null, Items.power_piece, fighters[3],
      Items.blue_potion, null, null, rangers[4], null, Items.power_gem, Items.life_potion, null,
      rangers[2], Wall.instance, ), arrayOf(rangers[22], Wall.instance, Items.power_piece,
      Items.guard_gem, Items.power_piece, Wall.instance, Wall.instance, Wall.instance,
      Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance, Wall.instance,
      Wall.instance, ), arrayOf(Items.life_crown, Wall.instance, null, Items.power_piece, null,
      rangers[7], null, Items.life_potion, null, rangers[10], null, Items.heavenly_potion, null,
      rangers[13], Key(KeyOrDoorColor.blue), ), )), )

  public val enemies: Array<Array<Enemy>> = arrayOf()

  override fun levels(): Array<Level> = levels

  override fun atk(): Int = 50

  override fun def(): Int = 0

  override fun hp(): Int = 1_000
}
