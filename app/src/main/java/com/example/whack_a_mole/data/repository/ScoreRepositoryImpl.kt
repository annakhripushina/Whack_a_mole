package com.example.whack_a_mole.data.repository

import android.content.Context
import com.example.whack_a_mole.domain.repository.ScoreRepository
import javax.inject.Inject

class ScoreRepositoryImpl @Inject constructor(private val context: Context) : ScoreRepository {

    override fun getScore(
    ): Int {
        return context.getSharedPreferences(
            BEST_SCORE_NAME,
            Context.MODE_PRIVATE
        ).getInt(BEST_SCORE_KEY, 0)
    }

    override fun setScore(value: Int) {
        context.getSharedPreferences(BEST_SCORE_NAME, Context.MODE_PRIVATE)
            .edit()
            .putInt(BEST_SCORE_KEY, value)
            .apply()
    }

    companion object {
        private const val BEST_SCORE_NAME = "BEST_SCORE_NAME"
        private const val BEST_SCORE_KEY = "BEST_SCORE_KEY"
    }

}