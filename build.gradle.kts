plugins {
    id("java")
}

group = "br.com.agibank"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("mysql:mysql-connector-java:8.0.33")
}

tasks.test {
    useJUnitPlatform()
}