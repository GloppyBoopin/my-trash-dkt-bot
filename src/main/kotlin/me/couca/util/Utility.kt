package me.couca.util

import dev.kord.common.kColor
import java.awt.Color

object Utility {

  fun randomColorK() = randomColor().kColor

  fun randomColor() = Color(randomRGB(), randomRGB(), randomRGB())

  fun randomIP() = "${randomIPDigit()}.${randomIPDigit()}.${randomIPDigit()}.${randomIPDigit()}"

  private fun randomRGB() = (0..255).random()

  private fun randomIPDigit() = (1..255).random()
}