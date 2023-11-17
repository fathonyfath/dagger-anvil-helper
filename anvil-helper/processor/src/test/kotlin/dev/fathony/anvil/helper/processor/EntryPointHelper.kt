package dev.fathony.anvil.helper.processor

import com.squareup.anvil.annotations.ExperimentalAnvilApi
import com.squareup.anvil.compiler.internal.buildFile
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import dev.fathony.anvil.helper.api.DefineEntryPoint
import org.intellij.lang.annotations.Language
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.KClass

@ExperimentalAnvilApi
@Language("kotlin")
fun createSampleComponentKotlinClass(
    packageName: String,
    className: String,
    scope: KClass<*>
): String {
    return FileSpec.buildFile(
        packageName = packageName,
        fileName = className
    ) {
        addType(
            TypeSpec.classBuilder(className)
                .addAnnotation(Singleton::class)
                .addAnnotation(
                    AnnotationSpec.builder(DefineEntryPoint::class)
                        .addMember("scope = %T::class", scope)
                        .addMember("parentScope = %T::class", Unit::class)
                        .build()
                )
                .addProperty(
                    PropertySpec.builder("url", String::class)
                        .addAnnotation(
                            AnnotationSpec.builder(Inject::class)
                                .build()
                        )
                        .mutable()
                        .addModifiers(KModifier.LATEINIT)
                        .build()
                )
                .build()
        )
    }
}
