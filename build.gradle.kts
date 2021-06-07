import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val d2vVersion: String by project
val chartsVersion: String by project

plugins {
    kotlin("jvm") version "1.4.32"
    id("application")
    id("org.openjfx.javafxplugin") version "0.0.9"
    application
}

group = "me.nick"
version = "1.0-SNAPSHOT"

repositories {
    maven{
        url = uri("https://maven.pkg.jetbrains.space/data2viz/p/maven/dev")
    }
    maven{
        url = uri("https://maven.pkg.jetbrains.space/data2viz/p/maven/public")
    }
    jcenter()
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test-js"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    api("org.jetbrains.lets-plot:lets-plot-common:2.0.1")
    api("org.jetbrains.lets-plot:lets-plot-kotlin-api:2.0.1")
    implementation("org.jetbrains.kotlinx:kotlinx-html:0.7.2")
    implementation ("io.data2viz.d2v:core-jvm:$d2vVersion")
    implementation ("io.data2viz.charts:core:$chartsVersion")
    implementation("org.ejml:ejml-all:0.40")
}


tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "MainKt"
}

javafx {
    version = "15.0.1"
    modules = listOf("javafx.controls")
}