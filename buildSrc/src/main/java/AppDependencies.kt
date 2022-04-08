import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {

    //std lib
    private val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    //android - ui

    // 1 - default
    private val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    private val materialLayout = "com.google.android.material:material:${Versions.materialDesign}"
    private val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

    // 2 - HILT
    private val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    private val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    private val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltViewModel}"

    // 3 - Navigation
    private val navFrag = "androidx.navigation:navigation-fragment-ktx:${Versions.navComp}"
    private val navUi = "androidx.navigation:navigation-ui-ktx:${Versions.navComp}"

    //ThirdParty Libs

    // 1 - Retrofit
    private val retrofit = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"

    // 2 - OkHttp
    private val okHttp = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

    // 3 - GSON Factory
    private val gsonFactory = "com.squareup.retrofit2:converter-gson:${Versions.gsonFactory}"

    // 4- EventBus
    private val eventBus = "org.greenrobot:eventbus:${Versions.eventBus}"

    // 5 - GLIDE
    private val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    private val glideComplier = "com.github.bumptech.glide:compiler:${Versions.glide}"


    //test libs
    private val junit = "junit:junit:${Versions.junit}"
    private val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    private val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    var appLibraries = arrayListOf<String>().apply {
        add(kotlinStdLib)
        add(coreKtx)
        add(appcompat)
        add(constraintLayout)
        add(materialLayout)
        add(hilt)
        add(navFrag)
        add(navUi)
        add(coroutines)
    }

    var androidTestLibraries = arrayListOf<String>().apply {
        add(extJUnit)
        add(espressoCore)
    }

    var testLibraries = arrayListOf<String>().apply {
        add(junit)
    }

    var annotationProcessors = arrayListOf<String>().apply {
        add(hiltCompiler)
        add(glideComplier)
    }

    var externalLibraries = arrayListOf<String>().apply {
        add(retrofit)
        add(okHttp)
        add(gsonFactory)
        add(eventBus)
        add(glide)
    }
}

//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }

}