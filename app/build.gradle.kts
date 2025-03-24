plugins {
    java
    application
}

group = "net.notjustanna"
version = "1.0-SNAPSHOT"
val mainClass = "net.notjustanna.Main"


repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation(enforcedPlatform("io.helidon:helidon-dependencies:4.2.0"))
    implementation("io.helidon.webserver:helidon-webserver")
    implementation("io.helidon.webserver:helidon-webserver-static-content")
    implementation("io.helidon.http.media:helidon-http-media-jsonp")
    implementation("io.helidon.webserver.observe:helidon-webserver-observe-health")
    implementation("io.helidon.webserver.observe:helidon-webserver-observe-metrics")
    implementation("io.helidon.config:helidon-config-yaml")
    implementation("io.helidon.health:helidon-health-checks")
    implementation("org.slf4j:slf4j-api")
    implementation("io.helidon.logging:helidon-logging-slf4j")
    implementation("org.slf4j:jul-to-slf4j")
    implementation("ch.qos.logback:logback-classic:1.5.17")
    implementation(project(":ui"))
    implementation("com.github.webview:webview_java:1.3.0")
}

tasks {
    val jar by getting(Jar::class) {}
    val assemble by getting

    val copyLibs by creating(Copy::class) {
        from(configurations.runtimeClasspath)
        into("build/libs/libs")
    }

    copyLibs.dependsOn(jar)
    assemble.dependsOn(copyLibs)

    jar.manifest {
        attributes(
            "Main-Class" to mainClass,
            "Class-Path" to configurations.runtimeClasspath.get().files.joinToString(" ") { "libs/${it.name}" }
        )
    }
}

application.mainClass = mainClass
