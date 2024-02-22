package dev.fathony.daggerapply

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

interface DaggerApplyPluginExtension {
    val applyProcessor: Property<Boolean>
    val applyScopes: Property<Boolean>
    val applyCommonScopes: Property<Boolean>
    val applyAndroidHelper: Property<Boolean>
}

class DaggerApplyPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create<DaggerApplyPluginExtension>("daggerApply")
        extension.applyProcessor.convention(false)
        extension.applyScopes.convention(false)
        extension.applyCommonScopes.convention(false)
        extension.applyAndroidHelper.convention(false)

        project.apply(plugin = "kotlin-kapt")

        project.dependencies {
            implementation("com.google.dagger:dagger:$DAGGER_VERSION")

            val kaptProcessor = extension.applyProcessor.mapKt { value ->
                return@mapKt if (value == true) {
                    "com.google.dagger:dagger-compiler:$DAGGER_VERSION"
                } else {
                    null
                }
            }
            kapt(kaptProcessor)

            val diScopes = extension.applyScopes
                .flatMapKt { value ->
                    return@flatMapKt if (value == true) {
                        project.provider { true }
                    } else {
                        extension.applyAndroidHelper
                    }
                }
                .mapKt { value ->
                    return@mapKt if (value == true) {
                        project(":di-scopes")
                    } else {
                        null
                    }
                }
            implementation(diScopes)

            val anvilScopes = extension.applyCommonScopes.mapKt { value ->
                return@mapKt if (value == true) {
                    project(":common-scopes")
                } else {
                    null
                }
            }
            implementation(anvilScopes)

            val diInterface = extension.applyAndroidHelper.mapKt { value ->
                return@mapKt if (value == true) {
                    project(":di-interface")
                } else {
                    null
                }
            }
            implementation(diInterface)
        }
    }

    private companion object {
        const val DAGGER_VERSION = "2.50"
    }
}

fun Project.daggerApply(configure: DaggerApplyPluginExtension.() -> Unit) =
    extensions.configure(configure)
