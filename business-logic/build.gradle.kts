plugins {
    id("my-java-base")
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(project(":data-model"))
    integrationTestsImplementation("org.junit.jupiter:junit-jupiter-api")
    integrationTestsRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")


}

// Configuring all tasks with a type. See: tasks.compileJava {}
//tasks.withType<JavaCompile>().configureEach {
//}
