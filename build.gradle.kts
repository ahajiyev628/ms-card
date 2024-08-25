plugins {
    id("org.springframework.boot") version "3.2.0"
    id("java")
    id("groovy")
    id("application")
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

    // Shedlock dependencies
    implementation("net.javacrumbs.shedlock:shedlock-spring:5.13.0")
    implementation("net.javacrumbs.shedlock:shedlock-provider-jdbc-template:5.13.0")

    // Spring AOP
    implementation("org.springframework.boot:spring-boot-starter-aop:3.2.0")

    // Jackson dependencies
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.0")

    // Groovy and Spock
    implementation("org.apache.groovy:groovy:4.0.22")
    testImplementation("org.spockframework:spock-spring:2.4-M4-groovy-4.0")

    // Random Beans
    implementation("io.github.benas:random-beans:3.9.0")

    // Redis
    implementation("org.redisson:redisson:3.20.0")

    // RabbitMQ
    implementation("org.springframework.boot:spring-boot-starter-amqp:3.2.0")

    // Swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")

    // Spring Security
    implementation("org.springframework.boot:spring-boot-starter-security:3.2.0")
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-parameters")
}

tasks.test {
    useJUnitPlatform()
}

springBoot {
    mainClass.set("org/example/spring/Main")
}
