plugins {
    id("java")
}

group = "com.gsb.tests"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    // TestNG
    testImplementation("org.testng:testng:7.7.1")

    // REST Assured for API Testing
    testImplementation("io.rest-assured:rest-assured:5.4.0")

    // Jackson for JSON Serialization/Deserialization
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")

    // Logging (SLF4J + Logback)
    implementation("org.slf4j:slf4j-api:2.0.7")
    implementation("ch.qos.logback:logback-classic:1.4.14")

    // Assertions
    testImplementation("org.assertj:assertj-core:3.24.2")
}

tasks.test {
    useTestNG()
    testLogging {
        events("passed", "skipped", "failed")
    }
}