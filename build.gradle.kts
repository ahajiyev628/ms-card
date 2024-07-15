plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // Spring Boot dependencies
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.0")
    implementation("org.springframework.boot:spring-boot-starter-test:3.2.0")

    // PostgreSQL JDBC driver
    implementation("org.postgresql:postgresql:42.7.3")

    // Liquibase
    implementation("org.liquibase:liquibase-core:4.28.0")

    // Lombok
    compileOnly("org.projectlombok:lombok:1.18.28")
    annotationProcessor("org.projectlombok:lombok:1.18.28")

    // Spring Cloud OpenFeign
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.1.0")

    // Hibernate core dependency
    implementation("org.hibernate.orm:hibernate-core:6.5.2.Final")
}

tasks.test {
    useJUnitPlatform()
}
