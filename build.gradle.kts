import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import ru.itech.sno_api.gradle.Versions

plugins {
    kotlin("jvm") version "1.9.23"
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "ru.itech"
version = Versions.ROOT_PROJECT_VERSION

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
    Versions.apply {
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-jdbc")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("io.jsonwebtoken:jjwt-api:$JJWT_API_VERSION")
        implementation("io.jsonwebtoken:jjwt-impl:$JJWT_API_VERSION")
        implementation("io.jsonwebtoken:jjwt-jackson:$JJWT_API_VERSION")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.springdoc:springdoc-openapi-starter-common:$SPRING_DOC_VERSION")
        implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$SPRING_DOC_VERSION")
        runtimeOnly("com.mysql:mysql-connector-j")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework.security:spring-security-test")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:$MOCKITO_VERSION")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = Versions.JVM_VERSION
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

tasks.jar {
    archiveBaseName.set(rootProject.name)
    archiveVersion.set("${rootProject.version}")
}
