// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        jcenter()
        mavenCentral()
    }

    dependencies {
        classpath(com.beetlestance.sample.buildsrc.Libs.androidGradlePlugin)
        classpath(com.beetlestance.sample.buildsrc.Libs.Kotlin.gradlePlugin)
        classpath(com.beetlestance.sample.buildsrc.Libs.AndroidX.Navigation.safeArgs)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}


allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
    }
}

subprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            // Enable experimental coroutines APIs, including Flow
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-Xopt-in=kotlinx.coroutines.FlowPreview",
                "-Xopt-in=kotlin.Experimental",
                "-Xallow-jvm-ir-dependencies",
                "-Xjvm-default=all"
            )

            jvmTarget = "1.8"
        }
    }
}

tasks.create<Delete>("clean") {
    delete = setOf(rootProject.buildDir)
}