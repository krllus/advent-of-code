import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.21"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))

    //implementation("org.jetbrains.kotlinx:multik-core:0.2.0")
    //implementation("org.jetbrains.kotlinx:multik-default:0.2.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.reflections:reflections:0.10.2")
    implementation("com.github.ajalt.mordant:mordant:2.2.0")
    implementation("guru.nidi:graphviz-kotlin:0.18.1")
    implementation("org.slf4j:slf4j-nop:2.0.9")
    //implementation("ch.qos.logback", "logback-classic", "1.2.3")
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "17"
        //languageVersion = "1.9"
        freeCompilerArgs = listOf("-Xcontext-receivers")
    }
}

application {
    mainClass.set("AdventOfCodeKt")
}
