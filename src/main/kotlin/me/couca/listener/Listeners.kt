package me.couca.listener

import dev.kord.common.entity.Snowflake
import dev.kord.core.event.message.ReactionAddEvent
import me.jakejmattson.discordkt.dsl.listeners

fun defaultListeners() = listeners {
  val listenedToMessage = Snowflake(986180756785229824)
  val listenedToUser: ULong = 267293217307754506u
  on<ReactionAddEvent> {
    if (messageId == listenedToMessage && userId.value == listenedToUser) message.addReaction(emoji)
    // if (getUserAsMember()?.isBot == true) return@on
  }
}