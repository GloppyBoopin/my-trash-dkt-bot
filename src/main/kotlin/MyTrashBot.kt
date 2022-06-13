import constant.Errors.NO_TOKEN_ERROR
import dev.kord.common.annotation.KordPreview
import dev.kord.gateway.Intents
import dev.kord.x.emoji.Emojis
import exception.TrashBotException
import kotlinx.coroutines.flow.toList
import me.jakejmattson.discordkt.api.dsl.bot
import me.jakejmattson.discordkt.api.extensions.addField
import util.Utility

@KordPreview
fun main(args: Array<String>) {

  val token = args.firstOrNull()
    ?: throw TrashBotException(NO_TOKEN_ERROR)

  bot(token) {
    prefix {
      "."
    }

    configure {
      theme = Utility.randomColor()
      commandReaction = Emojis.flagKz
      intents = Intents.nonPrivileged
    }

    mentionEmbed {
      title = "Trash bot"
      color = Utility.randomColorK()

      author {
        with(it.author) {
          icon = avatar.url
          name = tag
          url = avatar.url
        }
      }

      thumbnail {
        url = it.discord.kord.getSelf().avatar.url
      }

      footer {
        val versions = it.discord.versions
        text = "DiscordKt: ${versions.library} - Kord: ${versions.kord} - Kotlin: ${versions.kotlin}"
      }

      addField("Why you ping me", it.author.mention)
    }

    presence {
      playing("TrashBot")
    }

    onStart {
      val guilds = kord.guilds.toList().joinToString { it.name }
      println("Servers: $guilds")
    }
  }

}