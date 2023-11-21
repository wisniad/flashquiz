package domain

import domain.model.Achievement
import domain.model.Card
import domain.model.Quiz


interface LocalDataSource {
    fun getQuizzes(): List<Quiz>
    fun getQuizAchievements(id: Int): List<Achievement>
    fun getQuizCards(id: Int): List<Card>
    fun deleteCard(id: Int)
    fun deleteQuiz(id: Int)
    suspend fun insertQuiz(quiz: Quiz)
}