package me.couca.integration

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import me.jakejmattson.discordkt.annotations.Service

const val HOST = "https://pastecord.com"
val CLIENT = HttpClient(CIO)

@Service
class PasteBinClient {

  suspend fun uploadAndGet(body: Any): String {
    val response = upload(body)
    val key = response.bodyAsText().substringAfter(":\"").substringBefore("\"}")
    return "$HOST/$key"
  }

  private suspend fun upload(body: Any) = CLIENT.post("$HOST/documents") {
    contentType(ContentType.Application.Json)
    setBody(body)
  }
}