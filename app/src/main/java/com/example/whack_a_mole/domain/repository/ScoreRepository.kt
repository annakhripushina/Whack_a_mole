package com.example.whack_a_mole.domain.repository

interface ScoreRepository {
    fun getScore(): Int

    fun setScore(value: Int)

}