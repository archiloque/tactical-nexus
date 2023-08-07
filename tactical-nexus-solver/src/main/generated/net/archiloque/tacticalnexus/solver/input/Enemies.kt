package net.archiloque.tacticalnexus.solver.input

import javax.`annotation`.processing.Generated
import kotlin.Array
import kotlin.arrayOf
import net.archiloque.tacticalnexus.solver.Enemy

@Generated
public class Enemies {
  public companion object {
    public val fighters: Array<Enemy?> = arrayOf(null, Enemy(120, 55, 25, 2, Items.guard_piece),
        null, Enemy(320, 110, 40, 5, Items.guard_piece), )

    public val rangers: Array<Enemy?> = arrayOf(null, null, Enemy(200, 200, 10, 4,
        Items.red_potion), null, Enemy(200, 350, 20, 16, Items.red_potion), )
  }
}
