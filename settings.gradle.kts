pluginManagement {
    repositories {
        mavenLocal()
        maven("https://nexus.tcsbank.ru/repository/gradle-plugins")
        maven("https://nexus.tcsbank.ru/repository/mvn-jvm-core")
    }
}

buildscript {
    repositories {
        mavenLocal()
        maven("https://nexus.tcsbank.ru/repository/gradle-plugins")
        maven("https://nexus.tcsbank.ru/repository/mvn-jvm-core")
    }
}

rootProject.name = "demo"
