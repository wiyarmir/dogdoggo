plugins {
    id("com.android.application")
    id("kotlin-android")
}

val compose_release_version = "dev13"
val compose_version = "0.1.0-$compose_release_version"
val compose_compiler_extension_version = "0.1.0-$compose_release_version"

android {
    compileSdkVersion = "android-29"

    defaultConfig {
        setApplicationId("es.guillermoorellana.dogdoggo")
        setMinSdkVersion(23)
        setTargetSdkVersion(29)

        versionCode = 1
        versionName = "1.0.0"

        setTestInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerVersion = "1.3.70-dev-withExperimentalGoogleExtensions-20200424"
        kotlinCompilerExtensionVersion = "$compose_compiler_extension_version"
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.1.0")

    implementation("androidx.compose:compose-runtime:$compose_version")

    implementation("androidx.constraintlayout:constraintlayout:1.1.3")

    implementation("androidx.core:core-ktx:1.3.0")

    implementation("androidx.fragment:fragment-ktx:1.2.5")

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")

    implementation("androidx.navigation:navigation-dynamic-features-fragment:2.3.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.0")

    implementation("androidx.ui:ui-animation:$compose_version")
    implementation("androidx.ui:ui-core:$compose_version")
    implementation("androidx.ui:ui-foundation:$compose_version")
    implementation("androidx.ui:ui-layout:$compose_version")
    implementation("androidx.ui:ui-material-icons-extended:$compose_version")
    implementation("androidx.ui:ui-material:$compose_version")
    implementation("androidx.ui:ui-tooling:$compose_version")

    implementation("com.google.android.material:material:1.1.0")

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.72")

    testImplementation("junit:junit:4.13")

    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}
