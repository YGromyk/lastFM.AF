
plugins {
    id( "com.android.library")
    kotlin("android")
    kotlin("android.extensions")
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

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    val timberVersion = "4.7.1"
    val gsonVersion = "2.8.5"
    val retrofitConverterVersion = "2.6.0"
    implementation(fileTree(mapOf("dir" to "libs", "include" to  listOf("*.jar"))))
    // ui
    implementation( "androidx.appcompat:appcompat:1.0.2")
    // logger
    implementation( "com.jakewharton.timber:timber:$timberVersion")
    // gson
    implementation( "com.google.code.gson:gson:${gsonVersion}")
    implementation( "com.squareup.retrofit2:converter-gson:${retrofitConverterVersion}")

    // tests
    testImplementation( "junit:junit:4.12")
    androidTestImplementation( "androidx.test:runner:1.2.0")
    androidTestImplementation( "androidx.test.espresso:espresso-core:3.2.0")
}
