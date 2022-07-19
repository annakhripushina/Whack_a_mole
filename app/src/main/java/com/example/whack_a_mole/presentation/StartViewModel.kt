package com.example.whack_a_mole.presentation

import com.example.whack_a_mole.domain.usecase.BestScoreUseCase
import javax.inject.Inject

class StartViewModel @Inject constructor(
    private val bestScoreUseCase: BestScoreUseCase
) {

    fun getBestScore(): Int =
        bestScoreUseCase.getBestScore()

}