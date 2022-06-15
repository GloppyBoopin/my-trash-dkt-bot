package me.couca.service

import me.couca.constant.Errors.IMG_READ_ERROR
import me.couca.exception.TrashBotException
import me.jakejmattson.discordkt.annotations.Service
import java.awt.image.BufferedImage
import java.io.IOException
import java.net.URL
import javax.imageio.ImageIO
import kotlin.math.abs

private val grayLevelToAscii = linkedMapOf(
  0 to 'B',
  25 to 'R',
  50 to 'K',
  75 to 'P',
  100 to 'L',
  125 to '[',
  150 to '|',
  175 to ';',
  200 to ',',
  230 to '.',
  255 to ' '
)

@Service
class ImageService {

  fun toAscii(urlStr: String): String? {
    val url = URL(urlStr)
    val bufferedImage = try {
      ImageIO.read(url)
    } catch (e: IOException) {
      throw TrashBotException(IMG_READ_ERROR)
    } ?: return null

    val initialDimensions = bufferedImage.width to bufferedImage.height
    val resizedDimensions = generateSequence(initialDimensions) { (w, h) ->
      (w / 1.1).toInt() to (h / 1.1).toInt()
    }.first { (w, h) -> w < 240 && h < 240 }

    val resized = BufferedImage(resizedDimensions.first, resizedDimensions.second,
      BufferedImage.TYPE_INT_RGB)
    val graphics = resized.createGraphics()
    graphics.drawImage(bufferedImage, 0, 0, resizedDimensions.first, resizedDimensions.second, null)
    graphics.dispose()

    val grayValues = mutableListOf<List<Int>>()
    for (y in 0 until resized.height) {
      val row = mutableListOf<Int>()
      for (x in 0 until resized.width) {
        val rgb = resized.getRGB(x, y)
        val r = rgb shr 16 and 0xFF
        val g = rgb shr 8 and 0xFF
        val b = rgb and 0xFF

        val grayValue = (r + g + b) / 3
        row.add(grayValue)
      }
      grayValues.add(row)
    }

    val builder = StringBuilder()
    grayValues.forEach { rows ->
      rows.forEach { value ->
        val fittingKey = grayLevelToAscii.keys
          .map { key -> Pair(key, abs(key - value)) }
          .sortedBy { it.second }
          .map { it.first }
          .first()
        builder.append(grayLevelToAscii[fittingKey])
      }
      builder.append("\n")
    }
    return builder.toString()
  }

}