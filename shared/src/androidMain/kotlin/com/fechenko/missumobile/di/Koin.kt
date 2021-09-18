package com.fechenko.missumobile.di

import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration) = startKoin {
    appDeclaration()
    modules()
}

// called by iOS etc
fun initKoin() = initKoin{}