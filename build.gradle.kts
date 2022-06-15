import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "me.couca"
version = "1.0-SNAPSHOT"
val kotestVersion = "5.3.0"
val discordKtVersion = "0.23.0-SNAPSHOT"
val gsonVersion = "2.9.0"

plugins {
  kotlin("jvm") version "1.6.20"
}

repositories {
  mavenCentral()
  maven("https://oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
  implementation("me.jakejmattson:DiscordKt:$discordKtVersion")
  implementation("com.google.code.gson:gson:$gsonVersion")
  testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
  testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
}

tasks.test {
  useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "17"
}