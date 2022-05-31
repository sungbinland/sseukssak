import name.remal.gradle_plugins.dsl.extensions.flattenAny

/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.android.gms.oss-licenses-plugin")
    id("name.remal.check-dependency-updates") version Versions.Util.CheckDependencyUpdates
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    compileSdk = Application.compileSdk
    namespace = "team.sungbinland.sseukssak"

    defaultConfig {
        minSdk = Application.minSdk
        targetSdk = Application.targetSdk
        versionCode = Application.versionCode
        versionName = Application.versionName
        multiDexEnabled = true
    }

    buildFeatures {
        dataBinding = true
    }

    buildTypes {
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
        }
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

    compileOptions {
        sourceCompatibility = Application.sourceCompat
        targetCompatibility = Application.targetCompat
    }

    kotlinOptions {
        jvmTarget = Application.jvmTarget
    }
}

dependencies {
    val dependencies = listOf(
        Dependencies.Ui,
        Dependencies.Ktx,
        Dependencies.Util,
        Dependencies.Firebase,
        Dependencies.Essential,
        Dependencies.Jetpack,
        platform(Dependencies.FirebaseBom)
    ).flattenAny()

    dependencies.forEach(::implementation)
    Dependencies.Debug.forEach(::debugImplementation)

    Dependencies.Compiler.forEach(::kapt)
}
