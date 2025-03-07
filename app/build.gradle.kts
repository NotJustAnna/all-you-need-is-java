plugins {
    java
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "net.notjustanna"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
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
}

javafx {
    version = "23.0.2"
    modules("javafx.base", "javafx.controls", "javafx.web")
}

application.mainClass = "net.notjustanna.Main"
