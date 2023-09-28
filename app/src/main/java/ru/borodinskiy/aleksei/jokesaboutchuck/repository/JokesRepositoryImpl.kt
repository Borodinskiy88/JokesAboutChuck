package ru.borodinskiy.aleksei.jokesaboutchuck.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.borodinskiy.aleksei.jokesaboutchuck.api.ApiServiceImpl
import ru.borodinskiy.aleksei.jokesaboutchuck.dao.JokesDao
import ru.borodinskiy.aleksei.jokesaboutchuck.entity.Jokes
import javax.inject.Inject

class JokesRepositoryImpl @Inject constructor(
    private val apiServiceImpl: ApiServiceImpl,
    private val dao: JokesDao
) {

    val allJokes = dao.getJokes()

    fun getJokeById(jokeId : Int) = dao.getJokesById(jokeId)

    fun getJoke(): Flow<Jokes> = flow {
        try {
            val response = apiServiceImpl.getJoke()
            emit(response)
            dao.insert(response)
        } catch (e: Exception) {
            //           e.message
            return@flow
        }
    }.flowOn(Dispatchers.IO)

    suspend fun insert(jokes: Jokes) = dao.insert(jokes)

}