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