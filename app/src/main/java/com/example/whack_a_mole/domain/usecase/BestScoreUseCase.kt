package com.example.whack_a_mole.domain.usecase

import com.example.whack_a_mole.domain.repository.ScoreRepository
import javax.inject.Inject

class BestScoreUseCase @Inject constructor(
    private val scoreRepository: ScoreRepository
) {
    fun getBestScore(): Int =
        scoreRepository.getScore()

    fun setBestScore(value: Int) {
        scoreRepository.setScore(value)
    }

}