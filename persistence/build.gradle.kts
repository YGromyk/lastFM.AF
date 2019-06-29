plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(28)

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    val timberVersion = "4.7.1"
    val roomVersion = "2.1.0"
    val gsonVersion = "2.8.5"
    val lifeCycleVersion = "2.0.0"

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // timber
    implementation("com.jakewharton.timber:timber:$timberVersion")

    // room
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    androidTestImplementation("androidx.room:room-testing:$roomVersion")

    // gson
    implementation( "com.google.code.gson:gson:$gsonVersion")

    // liveData
    implementation("androidx.lifecycle:lifecycle-extensions:$lifeCycleVersion")


    // tests
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}
