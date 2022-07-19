package com.example.whack_a_mole

import android.app.Application
import com.example.whack_a_mole.di.AppComponent
import com.example.whack_a_mole.di.DaggerAppComponent
import com.example.whack_a_mole.di.module.DataModule

class App : Application() {
    val appComponent: AppComponent =
        DaggerAppComponent.builder()
            .dataModule(DataModule(this))
            .build()
}