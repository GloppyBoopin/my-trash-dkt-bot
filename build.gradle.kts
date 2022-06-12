import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "me.couca"
version = "1.0-SNAPSHOT"
val kotestVersion = "5.3.0"
val discordKtVersion = "0.23.0-SNAPSHOT"

plugins {
  java
  kotlin("jvm") version "1.5.10"
}

repositories {
  mavenCentral()
  maven("https://oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
  implementation("me.jakejmattson:DiscordKt:$discordKtVersion")
  testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
  testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
}

tasks.test {
  useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "11"
}