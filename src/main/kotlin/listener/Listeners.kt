package listener

import dev.kord.core.event.message.ReactionAddEvent
import me.jakejmattson.discordkt.api.dsl.listeners

fun defaultListeners() = listeners {
  on<ReactionAddEvent> {
    if (this.getUserAsMember()?.isBot == true) return@on
    message.addReaction(emoji)
  }
}