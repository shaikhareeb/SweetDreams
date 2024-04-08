import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("com.squareup.okhttp3:okhttp:4.9.0")
                implementation("org.json:json:20210307")
                implementation("com.google.firebase:firebase-admin:7.0.1")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
                implementation("ch.qos.logback:logback-classic:1.2.3")
            }
        }
        jvmMain.dependencies {
            implementation(jvmMain.resources)
            implementation("com.squareup.okhttp3:okhttp:4.9.0")
            implementation("org.json:json:20210307")
            implementation("com.google.firebase:firebase-admin:7.0.1")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
            implementation("ch.qos.logback:logback-classic:1.2.3")
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "SweetDreams"
            packageVersion = "1.0.0"
        }
    }
}
