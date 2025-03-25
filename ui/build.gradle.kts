import com.github.gradle.node.npm.task.NpxTask
import org.teavm.gradle.TeaVMPlugin.JS_TASK_NAME

plugins {
    java
    id("org.teavm") version "0.11.0"
    id("com.github.node-gradle.node") version "7.1.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(teavm.libs.jsoApis)
}

val OUTPUT_PREFIX = "net/notjustanna/ui"

teavm {
    all {
        mainClass = "net.notjustanna.ui.Main"
    }
    js {
        targetFileName = "$OUTPUT_PREFIX/main.js"
    }
}

node {
    download = true
    version = "22.14.0"
}

tasks.create("tailwindcss", NpxTask::class) {
    val input = File("${projectDir}/src/main/resources/$OUTPUT_PREFIX/tailwind.css").absolutePath
    val output = File("${projectDir}/build/generated/tailwindcss/$OUTPUT_PREFIX/index.css").absolutePath
    sourceSets.main.get().allSource.srcDirs.forEach(inputs::dir)
    inputs.file(input)
    outputs.file(output)
    command = "@tailwindcss/cli"
    args = listOf("-i", input, "-o", output)
}

tasks.getByName<Jar>("jar") {
    dependsOn(JS_TASK_NAME, "tailwindcss")
    includeEmptyDirs = false
    from(file("build/generated/teavm/js"), file("build/generated/tailwindcss"))
    exclude("**/*.class")
}
