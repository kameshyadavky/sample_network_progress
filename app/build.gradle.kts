import com.beetlestance.sample.buildsrc.Libs
import com.beetlestance.sample.buildsrc.Project

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

android {
    compileSdkVersion(Project.compileSdkVersion)

    defaultConfig {
        applicationId = Project.applicationId
        minSdkVersion(Project.minSdkVersion)
        targetSdkVersion(Project.targetSdkVersion)
        versionCode = Project.versionCode
        versionName = Project.versionName

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")

        javaCompileOptions {
            annotationProcessorOptions {
                arguments(
                    mapOf(
                        "room.schemaLocation" to "$projectDir/schemas",
                        "room.incremental" to "true"
                    )
                )
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        dataBinding = true
    }

    kapt {
        arguments {
            arg("dagger.experimentalDaggerErrorMessages", "enabled")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    // Kotlin
    implementation(Libs.Kotlin.stdlib)

    // Testing
    testImplementation(Libs.Test.junit)
    androidTestImplementation(Libs.AndroidX.Test.Ext.junit)
    androidTestImplementation(Libs.AndroidX.Test.espressoCore)

    // AndroidX
    implementation(Libs.AndroidX.appcompat)
    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.paging)

    // Co-routines
    implementation(Libs.Coroutines.core)
    implementation(Libs.Coroutines.android)

    // Navigation
    implementation(Libs.AndroidX.Navigation.uiKtx)
    implementation(Libs.AndroidX.Navigation.fragmentKtx)

    // Work Manager
    implementation(Libs.AndroidX.Work.runtimeKtx)

    // Material Design
    implementation(Libs.Google.material)

    // Gson
    implementation(Libs.Google.gson)

    // Lifecycle
    implementation(Libs.AndroidX.Lifecycle.viewmodelKtx)

    // Dagger
    implementation(Libs.Dagger.dagger)
    kapt(Libs.Dagger.compiler)
    kapt(Libs.Dagger.processor)
    implementation(Libs.Dagger.support)

    compileOnly(Libs.AssistedInject.annotationDagger2)
    kapt(Libs.AssistedInject.processorDagger2)

    // Room
    implementation(Libs.AndroidX.Room.common)
    implementation(Libs.AndroidX.Room.runtime)
    implementation(Libs.AndroidX.Room.ktx)
    kapt(Libs.AndroidX.Room.compiler)

    // Coil
    implementation(Libs.Coil.coil)

    // Neomorphism
    implementation(Libs.NeoMorphism.neoMorph)
}