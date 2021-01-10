package com.beetlestance.sample.di.modules

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

@Module(includes = [AssistedInject_AssistedModule::class])
@AssistedModule
abstract class AssistedModule