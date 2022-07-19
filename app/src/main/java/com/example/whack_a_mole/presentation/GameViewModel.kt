package com.example.whack_a_mole.presentation

import com.example.whack_a_mole.domain.usecase.BestScoreUseCase
import javax.inject.Inject

class GameViewModel @Inject constructor(
    private val bestScoreUseCase: BestScoreUseCase
) {

    fun setBestScore(value: Int) {
        if (value > bestScoreUseCase.getBestScore())
            bestScoreUseCase.setBestScore(value)

    }

}