package com.example.whack_a_mole.di.module

import com.example.whack_a_mole.data.repository.ScoreRepositoryImpl
import com.example.whack_a_mole.domain.repository.ScoreRepository
import com.example.whack_a_mole.domain.usecase.BestScoreUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class DomainModule {

    @Singleton
    @Binds
    abstract fun bindScoreRepository(impl: ScoreRepositoryImpl): ScoreRepository

    companion object {
        @Singleton
        @Provides
        fun provideBestScoreUseCase(
            scoreRepository: ScoreRepository
        ): BestScoreUseCase =
            BestScoreUseCase(scoreRepository)
    }
}