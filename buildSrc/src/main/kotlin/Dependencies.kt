/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

object Dependencies {
    const val FirebaseBom = "com.google.firebase:firebase-bom:${Versions.FirebaseBom}"

    val Firebase = listOf(
        "com.google.firebase:firebase-firestore-ktx"
    )

    val Essential = listOf(
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Essential.Coroutines}"
    )

    val Ktx = listOf(
        "androidx.core:core-ktx:${Versions.Ktx.Core}",
        "androidx.fragment:fragment-ktx:${Versions.Ktx.Fragment}",
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Ktx.LifeCycle}",
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Ktx.ViewModel}",
        "androidx.navigation:navigation-fragment-ktx:${Versions.Ktx.Navigation}",
        "androidx.navigation:navigation-ui-ktx:${Versions.Ktx.Navigation}"
    )

    val Ui = listOf(
        "io.coil-kt:coil:${Versions.Ui.Coil}",
        "com.airbnb.android:lottie:${Versions.Ui.Lottie}",
        "androidx.appcompat:appcompat:${Versions.Ui.AppCompat}",
        "androidx.core:core-splashscreen:${Versions.Ui.Splash}",
        "com.google.android.flexbox:flexbox:${Versions.Ui.Flexbox}",
        "com.google.android.material:material:${Versions.Ui.Material}",
        "androidx.constraintlayout:constraintlayout:${Versions.Ui.ConstraintLayout}",
        "com.google.android.gms:play-services-oss-licenses:${Versions.OssLicense.Main}",
    )

    val Util = listOf(
        "com.kakao.sdk:v2-user:${Versions.Kakao}",
        "land.sungbin:erratum:${Versions.Util.Erratum}",
        "land.sungbin:logeukes:${Versions.Util.Logeukes}",
        "land.sungbin:systemuicontroller:${Versions.Util.SystemUiController}"
    )

    val Jetpack = listOf(
        "com.google.dagger:hilt-android:${Versions.Jetpack.Hilt}"
    )

    val Compiler = listOf(
        "com.google.dagger:hilt-android-compiler:${Versions.Jetpack.Hilt}"
    )

    val Debug = listOf(
        "com.squareup.leakcanary:leakcanary-android:${Versions.Util.LeakCanary}"
    )
}
