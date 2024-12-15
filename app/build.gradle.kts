plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)

    application
}

repositories {
    /* Maven Central */
    mavenCentral()

    /* Gradle */
    gradlePluginPortal()
}

dependencies {
    implementation(kotlin("stdlib"))

    /* JUnit */
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation(libs.junit.jupiter.engine)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    /* Guava */
    implementation(libs.guava)

    /* Clikt (Commands) */
    implementation("com.github.ajalt.clikt:clikt:5.0.1")
    implementation("com.github.ajalt.clikt:clikt-markdown:5.0.1") /* Optional */

    /* Mordant */
    implementation("com.github.ajalt.mordant:mordant:3.0.1")
    implementation("com.github.ajalt.mordant:mordant-coroutines:3.0.1")
    implementation("com.github.ajalt.mordant:mordant-markdown:3.0.1")

    /* Kotter */
    implementation("com.varabyte.kotter:kotter-jvm:1.2.1")

    /* Serialization */
    implementation(libs.serialization)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(22)
    }
}

application {
    mainClass = "com.github.adriianh.app.AppKt"
}

tasks {
    withType<Jar> {
        archiveBaseName.set("exchange")
        destinationDirectory.set(file("$rootDir/bin"))

        manifest {
            attributes["Main-Class"] = "com.github.adriianh.app.AppKt"
        }
        configurations["compileClasspath"].forEach { file: File ->
            from(zipTree(file.absoluteFile))
        }
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }

    installDist {
        destinationDir = file("$rootDir/dist")
    }

    build {
        dependsOn(jar)
    }
}