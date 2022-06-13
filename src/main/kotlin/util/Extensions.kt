package util

import dev.kord.core.behavior.channel.MessageChannelBehavior
import dev.kord.core.behavior.channel.createEmbed
import dev.kord.rest.builder.message.EmbedBuilder
import me.jakejmattson.discordkt.api.dsl.Responder

suspend fun Responder.respondColored(construct: suspend EmbedBuilder.() -> Unit) =
  channel.createEmbedColored(construct)

suspend fun MessageChannelBehavior.createEmbedColored(construct: suspend EmbedBuilder.() -> Unit) {
  createEmbed {
    color = Utility.randomColorK()
    construct.invoke(this)
  }
}
