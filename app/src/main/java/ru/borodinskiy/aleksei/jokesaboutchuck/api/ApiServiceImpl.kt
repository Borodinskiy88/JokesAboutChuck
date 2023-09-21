package ru.borodinskiy.aleksei.jokesaboutchuck.api

import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiServices: ApiService) {

    suspend fun getJoke() = apiServices.getJoke()
}