import com.example.gradle.JarCount

plugins {
    id("application")
    id("my-java-library")
}

tasks.register<Zip>("bundle") {
    group = "My Group"
    description = "My Custom Zip Task"

//    from("build/libs/app.jar")
    from(tasks.jar)
    from(configurations.runtimeClasspath)

    destinationDirectory.set(layout.buildDirectory.dir("distribution"))
}

tasks.register<JarCount>("countJars") {
    group = "My Group"
    description = "Counts!"

    allJars.from(tasks.jar)
    allJars.from(configurations.runtimeClasspath)

    countFile.set(layout.buildDirectory.file("gen/count.txt"))
}

tasks.build {
    dependsOn(tasks.named("bundle"))
}

// add a new lifecycle task
tasks.register("buildAll") {
    group = "build"
    description = "build all"

    dependsOn(tasks.build)
    dependsOn(tasks.named("countJars"))
}