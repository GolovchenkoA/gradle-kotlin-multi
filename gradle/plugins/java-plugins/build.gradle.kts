plugins {
    `kotlin-dsl`
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.8.0")
}