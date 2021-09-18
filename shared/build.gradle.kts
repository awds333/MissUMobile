import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

val config = rootProject.ext

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
}

version = "1.0"

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }

    iosTarget("ios") {}

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }
    
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting
        val iosTest by getting
    }

    dependencies {
        implementation(dependency("koinCore"))
        implementation(dependency("koinKtor"))
    }
}

android {
    compileSdk = get("targetSdk")
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = get("minSdk")
        targetSdk = get("targetSdk")
    }
}

@kotlin.Suppress("UNCHECKED_CAST")
fun <T : Any> get(name: String): T =
    rootProject.ext.get(name) as? T ?: throw groovy.lang.MissingPropertyException(name)

fun dependency(name: String): String =
    get<Map<*, *>>("dependency")[name]?.toString() ?: throw groovy.lang.MissingPropertyException(name)