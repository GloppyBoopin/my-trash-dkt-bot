package util

import dev.kord.common.kColor
import java.awt.Color

object Utility {

  fun randomColorK() = randomColor().kColor
  fun randomColor() = Color(randomRGB(), randomRGB(), randomRGB())

  private fun randomRGB() = (0..255).random()
}