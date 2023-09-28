package ru.borodinskiy.aleksei.jokesaboutchuck.error

sealed class AppError(var code: String) : RuntimeException()
class ApiError(val status: Int, code: String) : AppError(code)
object NetworkError : AppError("Необходимо подключение к интернету")
