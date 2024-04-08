plugins {
    id("java")
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
}

tasks.test {
    useJUnitPlatform()
}

// Configuring all tasks with a type. See: tasks.compileJava {}
//tasks.withType<JavaCompile>().configureEach {
//}
