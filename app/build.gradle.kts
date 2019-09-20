plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "com.gromyk.lastfmaf"
        minSdkVersion(21)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    androidExtensions {
        isExperimental = true
    }
}

dependencies {
    val kotlinVersion = "1.3.40"
    val kotlinCoroutineVersion = "1.3.0-M1"
    val lifeCycleVersion = "2.0.0"
    val koinVersion = "2.0.1"
    val timberVersion = "4.7.1"
    val picassoVersion = "2.71828"
    val expandableLayoutVersion = "2.9.2"
    val roomVersion = "2.1.0"
    val retrofitConverterVersion = "2.6.0"
    val navigationVersion = "2.1.0-alpha05"
    val mvpLibVersion = "3.5.1"

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    api(project(":api"))
    api(project(":persistence"))

    // ui
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion")
    implementation("androidx.appcompat:appcompat:1.0.2")
    implementation("androidx.core:core-ktx:1.0.2")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.recyclerview:recyclerview:1.1.0-alpha06")

    // expandable layout
    implementation("net.cachapa.expandablelayout:expandablelayout:$expandableLayoutVersion")

    //Picasso for Image Loading
    implementation("com.squareup.picasso:picasso:$picassoVersion")

    // viewModels
    implementation("androidx.lifecycle:lifecycle-extensions:$lifeCycleVersion")

    //Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinCoroutineVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutineVersion")

    // koin
    implementation("org.koin:koin-androidx-scope:$koinVersion")
    implementation("org.koin:koin-androidx-viewmodel:$koinVersion")

    // timber
    implementation("com.jakewharton.timber:timber:$timberVersion")

    // room
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    androidTestImplementation("androidx.room:room-testing:$roomVersion")

    // navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")

    // mvp library
    implementation("de.appsfactory:mvp:$mvpLibVersion")


    // tests
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}
