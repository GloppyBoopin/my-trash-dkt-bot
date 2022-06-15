package me.couca.exception

import dev.kord.core.entity.channel.GuildMessageChannel

data class TrashBotException(
  val channel: GuildMessageChannel?,
  override val message: String?
) : RuntimeException(message) {

  constructor(message: String) : this(null, message)
}