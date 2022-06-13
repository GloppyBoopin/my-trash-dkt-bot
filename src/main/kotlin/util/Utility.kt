package util

import dev.kord.common.kColor
import java.awt.Color

object Utility {

  fun randomColor() = Color(randomRGB(), randomRGB(), randomRGB()).kColor

  private fun randomRGB() = (0..255).random()
}