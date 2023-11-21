package domain.data

import domain.model.Quiz
import com.pa1479.bth.g3.flashquiz.database.FlashCardsDB
import domain.LocalDataSource
import domain.model.Achievement
import domain.model.Card

class SqlDelightLocalDataSource(db: FlashCardsDB): LocalDataSource {
    private val queries = db.flashcardsQueries
    override fun getQuizzes(): List<Quiz> {
        return queries.getQuizzes().executeAsList().map{ Quiz(it.id.toInt(), it.name, listOf()) }
    }

    override fun getQuizAchievements(id: Int): List<Achievement> {
        return queries.getQuizAchievements(id.toLong()).executeAsList()
            .map { Achievement(it.quiz_id.toInt(), it.score, it.created) }
    }

    override fun getQuizCards(id: Int): List<Card> {
        return queries.getQuizCards(id.toLong()).executeAsList().map {
            Card(
                it.id.toInt(),
                it.text,
                it.correct_answer,
                it.option_1,
                it.option_2,
                it.option_3
            )
        }
    }

    override fun storeScore(score: Achievement) {
        queries.storeScore(score.quizId.toLong(), score.score, score.created)
    }

    override fun deleteCard(id: Int) {
        queries.deleteCard(id.toLong())
    }

    override fun deleteQuiz(id: Int) {
        queries.deleteQuiz(id.toLong())
    }

//    override fun getQuizWithCards(id: Int): Quiz {
//        val qNaRaw = queries.getQuizCards(id.toLong()).executeAsList()
//        val qNa: List<Card> = qNaRaw
//            .map {
//                Card(
//                    it.id.toInt(),
//                    it.text,
//                    it.correct_answer,
//                    it.option_1,
//                    it.option_2,
//                    it.option_3
//                )
//            }
//        val record = qNaRaw.first()
//        return Quiz(record.quiz_id.toInt(), record.quiz_name, qNa)
//    }

    override suspend fun insertQuiz(quiz: Quiz) {
        TODO("Not yet implemented")
    }
}