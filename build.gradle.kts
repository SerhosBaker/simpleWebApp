import org.gradle.api.JavaVersion.VERSION_25

plugins {
    java
    id("org.springframework.boot") version "3.5.13"
    id("io.spring.dependency-management") version "1.1.7"
    id("nu.studer.jooq") version "10.2.1"
}

group = "ru.smarkov"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = VERSION_25
java.targetCompatibility = VERSION_25

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://artifactory.tcsbank.ru/artifactory/maven-all")
    }
    maven {
        url = uri("https://nexus.tcsbank.ru/repository/mvn-tap")
        name = "esb"
    }
    maven {
        url = uri("https://nexus.tcsbank.ru/repository/mvn-maven-proxy")
        name = "tcs-central"
    }
    maven {
        url = uri("https://nexus.tcsbank.ru/repository/mvn-jvm-core")
        name = "jvm-core"
    }
}

dependencies {
    // Lombok
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")

    // MapStruct
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

    // Spring Boot
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    
    // Spring
    implementation("org.springframework:spring-context:6.0.13")
    implementation("org.springframework:spring-jdbc:6.0.13")
    
    // Database
    runtimeOnly("org.postgresql:postgresql:42.7.3")
    
    // jOOQ
    implementation("org.jooq:jooq:3.19.18")
    
    // Kafka
    implementation("org.springframework.kafka:spring-kafka:3.2.4")
    
    // Jackson
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    
    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // jOOQ codegen
    jooqGenerator("org.postgresql:postgresql:42.7.3")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// jOOQ configuration
jooq {
    version.set("3.19.18")
    
    configurations {
        create("main") {
            generateSchemaSourceOnCompilation.set(true)
            
            jooqConfiguration.apply {
                jdbc.apply {
                    driver = "org.postgresql.Driver"
                    url = "jdbc:postgresql://admin-qa-invest-social-education-1.ix-m5.pg-test.tcsbank.ru:5432/admin_qa2"
                    user = "admin_rw"
                    password = "some"
                }
                generator.apply {
                    database.apply {
                        name = "org.jooq.meta.postgres.PostgresDatabase"
                        includes = ".*"
                        excludes = ""
                        inputSchema = "smarkov"
                    }
                    generate.apply {
                        isDeprecated = false
                        isRecords = true
                        isImmutablePojos = false
                        isFluentSetters = true
                    }
                    target.apply {
                        packageName = "ru.smarkov.demo.jooq"
                        directory = "build/generated-src/jooq/main"
                    }
                }
            }
        }
    }
}
