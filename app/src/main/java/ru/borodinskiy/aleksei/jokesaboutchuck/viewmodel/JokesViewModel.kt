package ru.borodinskiy.aleksei.jokesaboutchuck.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.borodinskiy.aleksei.jokesaboutchuck.entity.Jokes
import ru.borodinskiy.aleksei.jokesaboutchuck.repository.JokesRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class JokesViewModel @Inject constructor(
    private val repository: JokesRepositoryImpl
) : ViewModel() {

    val allJokes = repository.getJoke().asLiveData()

    fun getJoke() : LiveData<Jokes> = repository.getJoke().asLiveData()
}