plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.parxondemo1"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.parxondemo1"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.appcompat)
    implementation(libs.material)
<<<<<<< HEAD
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:4.9.1'
    // Optional: for logging requests/responses


}


=======
    implementation(libs.volley)
    implementation(libs.android.gif.drawable)
    implementation(libs.material)
}
>>>>>>> 1aea5d0d9bcc7b3793fc13dc0b187a27fa2bea7f
