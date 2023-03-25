package com.ithirteeng.cinemaproject.di

import androidx.navigation.NavController
import org.koin.dsl.module

val appModule = module {
    single { NavController(context = get()) }
}