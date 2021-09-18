plugins {
    id("com.android.application")
    id("kotlin-kapt")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))
    implementation(dependency("composeRuntime"))
    implementation(dependency("composeUi"))
    implementation(dependency("composeFoundation"))
    implementation(dependency("composeFoundationLayout"))
    implementation(dependency("composeMaterial"))
    implementation(dependency("composeUiTooling"))
    implementation(dependency("composeUiToolingPreview"))
    implementation(dependency("activityCompose"))
    implementation(dependency("material"))
    implementation(dependency("appcompat"))
    implementation(dependency("coreKtx"))
    implementation(dependency("lifecycleRuntime"))
    implementation(dependency("koinAndroid"))
    implementation(dependency("koinCompat"))
    implementation(dependency("koinCompose"))
}

android {
    compileSdk = get("targetSdk")
    defaultConfig {
        applicationId = "com.fechenko.missumobile.android"
        minSdk = get("minSdk")
        targetSdk = get("targetSdk")
        versionCode = get("versionCode")
        versionName = get("versionName")
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = get("composeVersion")
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

@kotlin.Suppress("UNCHECKED_CAST")
fun <T : Any> get(name: String): T =
    rootProject.ext.get(name) as? T ?: throw groovy.lang.MissingPropertyException(name)

fun dependency(name: String): String =
    get<Map<*, *>>("dependency")[name]?.toString() ?: throw groovy.lang.MissingPropertyException(name)