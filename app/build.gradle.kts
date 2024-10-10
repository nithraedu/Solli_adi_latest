@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.google.services)
}

android {
    namespace = "nithra.tamil.word.game.solliadi"
    compileSdk = 34
    useLibrary("org.apache.http.legacy")

    defaultConfig {
        applicationId = "nithra.tamil.word.game.solliadi"
        minSdk = 24
        targetSdk = 34
        versionCode = 72 //71
        versionName = "6.20" //6.19
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    lint {
        abortOnError = false
        checkReleaseBuilds = false
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(libs.installreferrer)
    implementation(libs.glide)
    implementation(libs.androidx.multidex)
    implementation(libs.picasso)
    implementation(libs.firebase.config)
    androidTestImplementation(libs.androidx.runner)
    implementation(libs.firebase.messaging)
    implementation(libs.firebase.core)
    implementation(libs.billing)
    implementation(libs.gson)
    implementation(libs.volley)
  // implementation(libs.core)
    //replace code for com.google.android.play:core:1.10.3
    implementation(libs.play.review)
    implementation(libs.play.update)
    //------------------
    implementation(libs.firebase.inappmessaging.display)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.lottie)
    implementation(libs.play.services.ads)
    implementation(libs.androidx.legacy.support.v4)
}