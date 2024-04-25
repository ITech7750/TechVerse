import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.23"
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "ru.itech"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") // JPA support
    implementation("org.springframework.boot:spring-boot-starter-jdbc") // JDBC support
    implementation("org.springframework.boot:spring-boot-starter-web") // Web functionality
    implementation("org.springframework.boot:spring-boot-starter-security") // Security features
    implementation("io.jsonwebtoken:jjwt-api:0.12.3") // JWT API
    implementation("io.jsonwebtoken:jjwt-impl:0.12.3") // JWT implementation
    implementation("io.jsonwebtoken:jjwt-jackson:0.12.3") // JSON processing for JWT
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin") // JSON handling for Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect") // Kotlin reflection
    implementation("org.springdoc:springdoc-openapi-starter-common:2.1.0") // API documentation
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0") // Swagger UI for WebMVC
    runtimeOnly("com.mysql:mysql-connector-j") // MySQL JDBC driver
    testImplementation("org.springframework.boot:spring-boot-starter-test") // Testing utilities
    testImplementation("org.springframework.security:spring-security-test") // Security testing utilities
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mockito:mockito-core:3.3.3")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

sourceSets {
    main {
        kotlin.srcDirs("src/main/kotlin")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
