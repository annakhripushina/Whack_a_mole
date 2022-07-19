package com.example.whack_a_mole.di

import com.example.whack_a_mole.di.module.DataModule
import com.example.whack_a_mole.di.module.DomainModule
import com.example.whack_a_mole.ui.GameFragment
import com.example.whack_a_mole.ui.ResultFragment
import com.example.whack_a_mole.ui.StartFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DomainModule::class])
interface AppComponent {
    fun inject(gameFragment: GameFragment)
    fun inject(startFragment: StartFragment)
    fun inject(resultFragment: ResultFragment)

}