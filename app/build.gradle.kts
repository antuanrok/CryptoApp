plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
//    id ("kotlin-android-extensions")
}

android {
    namespace = "com.example.cryptoapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cryptoapp"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        //sourceCompatibility = <MAJOR_JDK_VERSION>
        //targetCompatibility = <MAJOR_JDK_VERSION>
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    packagingOptions { resources.excludes.add("META-INF/*") }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.google.code.gson:gson:2.9.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
  //  implementation ("io.reactivex:rxjava:1.1.6")
  //  implementation ("io.reactivex:rxandroid:1.2.1")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.9.0")
    implementation ("io.reactivex.rxjava2:rxkotlin:2.4.0")
    implementation ("io.reactivex.rxjava2:rxandroid:2.0.2")

    val lifecycle_version = "2.6.2"
   // val arch_version = "2.2.0"
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    // Annotation processor
    kapt("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")
    //implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycle_version")
    // optional - ReactiveStreams support for LiveData
    implementation("androidx.lifecycle:lifecycle-reactivestreams-ktx:$lifecycle_version")

    val room_version = "2.6.0"
    implementation("androidx.room:room-runtime:$room_version")
    //annotationProcessor("androidx.room:room-compiler:$room_version")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$room_version")
    // To use Kotlin Symbol Processing (KSP)
    //ksp("androidx.room:room-compiler:$room_version")

    implementation ("com.squareup.picasso:picasso:2.8")

}