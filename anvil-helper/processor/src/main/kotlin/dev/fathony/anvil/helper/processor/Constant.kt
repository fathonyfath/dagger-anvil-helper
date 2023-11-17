package dev.fathony.anvil.helper.processor

import com.squareup.anvil.annotations.ExperimentalAnvilApi
import com.squareup.anvil.compiler.internal.fqName
import dev.fathony.anvil.helper.api.DefineEntryPoint

@OptIn(ExperimentalAnvilApi::class)
val DefineEntryPointFqName = DefineEntryPoint::class.fqName
const val GeneratedEntryPointSuffix = "EntryPoint"
const val GeneratedBindingModuleName = "BindingModule"
const val GeneratedEntryPointFactoryName = "Factory"
