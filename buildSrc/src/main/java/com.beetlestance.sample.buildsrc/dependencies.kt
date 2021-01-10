package com.beetlestance.sample.buildsrc

object Versions {
    const val ktlint: String = "0.37.2"
}

object Project {
    const val applicationId: String = "com.beetlestance.sample"
    const val buildToolsVersion: String = "30.0.2"
    const val minSdkVersion: Int = 21
    const val targetSdkVersion: Int = 30
    const val compileSdkVersion: Int = 30
    const val versionCode: Int = 1
    const val versionName: String = "0.1"
}

object Libs {

    const val androidGradlePlugin: String = "com.android.tools.build:gradle:4.1.1"

    const val leakCanary: String = "com.squareup.leakcanary:leakcanary-android:2.2"

    const val timber: String = "com.jakewharton.timber:timber:4.7.1"

    object Test {
        const val junit: String = "junit:junit:4.13.1"
        const val robolectric: String = "org.robolectric:robolectric:4.3.1"
        const val mockK: String = "io.mockk:mockk:1.9.3"
    }

    object AndroidX {
        const val androidAnnotation: String = "androidx.annotation:annotation:1.2.0-alpha01"
        const val appcompat: String = "androidx.appcompat:appcompat:1.3.0-alpha02"
        const val coreKtx: String = "androidx.core:core-ktx:1.5.0-alpha05"
        const val palette: String = "androidx.palette:palette-ktx:1.0.0"
        const val paging = "androidx.paging:paging-runtime-ktx:3.0.0-alpha11"

        object Fragment {
            private const val version = "1.3.0-beta02"
            const val fragmentKtx: String = "androidx.fragment:fragment-ktx:$version"
        }

        object Test {
            private const val version = "1.3.0"
            const val core = "androidx.test:core:$version"
            const val rules = "androidx.test:rules:$version"
            const val runner: String = "androidx.test:runner:$version"

            object Ext {
                private const val version = "1.1.2"
                const val junit = "androidx.test.ext:junit-ktx:$version"
            }

            const val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
        }

        const val archCoreTesting = "androidx.arch.core:core-testing:2.1.0"

        object Lifecycle {
            private const val version = "2.3.0-rc01"
            const val extensions: String = "androidx.lifecycle:lifecycle-extensions:$version"
            const val viewmodelKtx: String = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val livedataKtx: String = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val compiler: String = "androidx.lifecycle:lifecycle-compiler:$version"
            const val common: String = "androidx.lifecycle:lifecycle-common-java8:$version"
        }

        object Room {
            private const val version = "2.2.6"
            const val common: String = "androidx.room:room-common:$version"
            const val runtime: String = "androidx.room:room-runtime:$version"
            const val compiler: String = "androidx.room:room-compiler:$version"

            // optional - Kotlin Extensions and Coroutines support for Room
            const val ktx: String = "androidx.room:room-ktx:$version"

            // optional - Test helpers
            const val testing: String = "androidx.room:room-testing:$version"
        }

        object Work {
            private const val version = "2.4.0"
            const val runtimeKtx: String = "androidx.work:work-runtime-ktx:$version"
        }

        object Navigation {
            private const val version = "2.3.2"
            const val fragmentKtx: String = "androidx.navigation:navigation-fragment-ktx:$version"
            const val uiKtx: String = "androidx.navigation:navigation-ui-ktx:$version"
            const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }
    }

    object Google {

        const val truth = "com.google.truth:truth:1.1"

        const val material: String = "com.google.android.material:material:1.3.0-beta01"
        const val gson: String = "com.google.code.gson:gson:2.8.6"

        object PlayServices {
            const val gmsGoogleServices: String = "com.google.gms:google-services:4.3.3"
        }

        object Firebase {
            const val performance: String = "com.google.firebase:firebase-perf:19.0.8"
            const val performancePlugin: String = "com.google.firebase:perf-plugin:1.3.1"

            const val crashlytics: String = "com.google.firebase:firebase-crashlytics:17.2.1"
            const val crashlyticsGradle: String =
                "com.google.firebase:firebase-crashlytics-gradle:2.2.0"

            const val auth: String = "com.google.firebase:firebase-auth:19.3.2"

            const val messaging: String = "com.google.firebase:firebase-messaging:20.2.4"
        }
    }

    object Coil {
        private const val version = "1.1.0"
        const val coil: String = "io.coil-kt:coil:$version"
        const val svgCoil: String = "io.coil-kt:coil-svg:$version"
    }

    object Accompanist {
        private const val version = "0.4.1"
        const val coil: String = "dev.chrisbanes.accompanist:accompanist-coil:$version"
        const val insets: String = "dev.chrisbanes.accompanist:accompanist-insets:$version"
    }

    object Dagger {
        private const val version = "2.30.1"
        const val dagger: String = "com.google.dagger:dagger:$version"
        const val compiler: String = "com.google.dagger:dagger-compiler:$version"
        const val processor = "com.google.dagger:dagger-android-processor:$version"
        const val support = "com.google.dagger:dagger-android-support:$version"
    }

    object AssistedInject {
        private const val version = "0.5.2"
        const val annotationDagger2 =
            "com.squareup.inject:assisted-inject-annotations-dagger2:$version"
        const val processorDagger2 =
            "com.squareup.inject:assisted-inject-processor-dagger2:$version"
    }

    object Kotlin {
        const val version: String = "1.4.21"
        const val stdlib: String = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val reflect: String = "org.jetbrains.kotlin:kotlin-reflect:$version"
        const val gradlePlugin: String = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit: String = "com.squareup.retrofit2:retrofit:$version"
        const val moshiConverter: String = "com.squareup.retrofit2:converter-moshi:$version"
    }

    object Moshi {
        private const val version = "1.11.0"
        const val moshi: String = "com.squareup.moshi:moshi:$version"
        const val moshiKotlin: String = "com.squareup.moshi:moshi-kotlin:$version"
        const val moshiAdapters: String = "com.squareup.moshi:moshi-adapters:$version"

    }

    object OkHttp {
        private const val version = "4.9.0"
        const val okhttp: String = "com.squareup.okhttp3:okhttp:$version"
        const val loggingInterceptor: String = "com.squareup.okhttp3:logging-interceptor:$version"
        const val urlConnection: String = "com.squareup.okhttp3:okhttp-urlconnection:$version"
    }

    object Coroutines {
        private const val version = "1.4.2"
        const val core: String = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android: String = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test: String = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Lint {
        private const val version = "27.3.0-alpha03"
        const val api: String = "com.android.tools.lint:lint-api:$version"
        const val checks: String = "com.android.tools.lint:lint-checks:$version"
        const val layoutlib: String = "com.android.tools.layoutlib:layoutlib-api:$version"
        const val sdkCommon: String = "com.android.tools:sdk-common:$version"
    }

    object Roomigrant {
        /**
         * We use a fork which has been migrated to AndroidX Room
         */
        private const val version = "master-SNAPSHOT"
        const val library: String = "com.github.chrisbanes.Roomigrant:RoomigrantLib:$version"
        const val compiler: String = "com.github.chrisbanes.Roomigrant:RoomigrantCompiler:$version"
    }

    object NeoMorphism {
        const val neoMorph = "com.github.fornewid:neumorphism:0.3.0"
    }
}
