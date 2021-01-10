package com.beetlestance.sample.di.scopes

import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.BINARY)
annotation class ActivityScoped

@Scope
@Retention(AnnotationRetention.BINARY)
annotation class FragmentScoped