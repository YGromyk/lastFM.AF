// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()

    }
    dependencies {
        extra["kotlinVersion"] = "1.3.50"
        classpath("com.android.tools.build:gradle:3.5.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${extra["kotlinVersion"]}")
        classpath("android.arch.navigation:navigation-safe-args-gradle-plugin:1.0.0")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts.kts.kts files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven ("http://s2.appsfactory.de/APPSfactory/Maven" )
    }
}

tasks {
    registering(Delete::class) {
        delete(buildDir)
    }
}