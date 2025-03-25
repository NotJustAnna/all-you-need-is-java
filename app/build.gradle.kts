import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
    application
    id("com.gradleup.shadow") version "9.0.0-beta11"
}

group = "net.notjustanna"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

application.mainClass = "net.notjustanna.Main"

dependencies {
    implementation("com.linecorp.armeria:armeria:1.32.3")
    implementation("com.linecorp.armeria:armeria-logback:1.32.3")
    implementation("com.github.webview:webview_java:1.3.0")
    runtimeOnly("ch.qos.logback:logback-classic:1.5.17")
    runtimeOnly(project(":ui")) {
        isTransitive = false
    }
}

// This is a convenience more than anything else.
// Configures the shadowJar task to merge service files and exclude useless files from META-INF.
tasks.withType(ShadowJar::class) {
    mergeServiceFiles()
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    exclude(
        "META-INF/maven/**",
        "META-INF/license/**",
        "META-INF/*.versions.properties",
        "META-INF/LGPL2.1",
        "META-INF/AL2.0",
        "META-INF/*-LICENSE",
        "META-INF/LICENSE",
        "META-INF/LICENSE.txt",
        "META-INF/*-NOTICE",
        "META-INF/NOTICE",
        "META-INF/NOTICE.txt",
    )
}