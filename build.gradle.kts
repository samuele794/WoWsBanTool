import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.*


plugins {
    kotlin("jvm") version "1.5.21"
    id("org.jetbrains.compose") version "1.0.0-alpha4-build321"
    // https://awesomeopensource.com/project/gmazzo/gradle-buildconfig-plugin
    id("com.github.gmazzo.buildconfig") version "3.0.2"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.5.21"

    kotlin("plugin.serialization") version "1.5.21"
}

group = "it.github.samuele794"
version = "1.0"

repositories {
    mavenCentral()
    google()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
}

dependencies {
    val decomposeVersion = "0.3.1"
    val koinVersion = "3.1.2"

    implementation(files("libs/Warships-java-api-1.0.0.jar"))

    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
    api("com.google.code.gson:gson:2.8.6")

    implementation(kotlin("stdlib-jdk8"))
    implementation(compose.desktop.currentOs)
    // https://arkivanov.github.io/Decompose/
    implementation("com.arkivanov.decompose:decompose:$decomposeVersion")
    implementation("com.arkivanov.decompose:extensions-compose-jetbrains:$decomposeVersion")

    // https://insert-koin.io/
    implementation("io.insert-koin:koin-core:$koinVersion")

    implementation(platform("software.amazon.awssdk:bom:2.17.24"))
    implementation("software.amazon.awssdk:dynamodb")
    implementation("software.amazon.awssdk:dynamodb-enhanced")
    implementation("software.amazon.awssdk:kinesis")

}

buildConfig {
    val properties = Properties()
    properties.load(project.rootProject.file("local.properties").inputStream())

    useKotlinOutput { topLevelConstants = true }
    buildConfigField("String", "AWS_ACCESS_KEY", "\"${properties.getProperty("aws.key.id")}\"")
    buildConfigField("String", "AWS_SECRET_KEY", "\"${properties.getProperty("aws.key.secret")}\"")
    buildConfigField("String", "WOWS_API_KEY", "\"${properties.getProperty("wargaming.api.key")}\"")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "BanTool"
            packageVersion = "1.0.0"
        }
    }
}

noArg {
    annotation("annotation.NoArg")
}