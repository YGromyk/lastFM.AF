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

        buildConfigField(
            "String",
            "API_KEY",
            properties["API_KEY"] as String? ?: throw GradleException("Cannot build project without API-key")
        )
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
    val koinVersion = "2.0.1"
    val retrofitConverterVersion = "2.6.0"

    implementation(fileTree(mapOf("dir" to "libs", "include" to  listOf("*.jar"))))

    // ui
    implementation( "androidx.appcompat:appcompat:1.0.2")

    // logger
    implementation( "com.jakewharton.timber:timber:$timberVersion")

    // rest
    implementation( "com.google.code.gson:gson:$gsonVersion")
    implementation( "com.squareup.retrofit2:converter-gson:$retrofitConverterVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:3.14.2")

    //koin di
    implementation("org.koin:koin-core:$koinVersion")
    implementation("org.koin:koin-core-ext:$koinVersion")
    implementation("org.koin:koin-android:$koinVersion")

    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.0-M1")
    // coroutines callAdapter
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    // timber
    implementation("com.jakewharton.timber:timber:$timberVersion")

    // tests
    testImplementation( "junit:junit:4.12")
    androidTestImplementation( "androidx.test:runner:1.2.0")
    androidTestImplementation( "androidx.test.espresso:espresso-core:3.2.0")
}
