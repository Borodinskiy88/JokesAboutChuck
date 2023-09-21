package ru.borodinskiy.aleksei.jokesaboutchuck.api

import retrofit2.http.GET
import ru.borodinskiy.aleksei.jokesaboutchuck.entity.Jokes

interface ApiService {

    @GET("random")
    suspend fun getJoke() : Jokes
}