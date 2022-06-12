import dev.kord.common.annotation.KordPreview
import dev.kord.gateway.Intents
import dev.kord.x.emoji.Emojis
import exception.TrashBotException
import kotlinx.coroutines.flow.toList
import me.jakejmattson.discordkt.dsl.bot
import me.jakejmattson.discordkt.extensions.addField
import me.jakejmattson.discordkt.extensions.profileLink
import java.awt.Color

@KordPreview
fun main(args: Array<String>) {

  val token = args.firstOrNull()
    ?: throw TrashBotException("Expected the bot token as a command line argument")

  bot(token) {
    configure {
      theme = Color(0x00BFFF)
      commandReaction = Emojis.flagKz
      intents = Intents.nonPrivileged
    }

    mentionEmbed {
      title = "Trash bot"
      color = it.discord.configuration.theme

      author {
        with(it.author) {
          icon = avatar?.url
          name = tag
          url = profileLink
        }
      }

      thumbnail {
        url = it.discord.kord.getSelf().avatar?.url!!
      }

      footer {
        val versions = it.discord.versions
        text = "${versions.library} - ${versions.kord} - ${versions.kotlin}"
      }

      addField("Bruh", it.message?.content!!)
    }

    presence {
      playing("TrashBot")
    }

    onStart {
      val guilds = kord.guilds.toList().joinToString { it.name }
      println("Guilds: $guilds")
    }
  }

}