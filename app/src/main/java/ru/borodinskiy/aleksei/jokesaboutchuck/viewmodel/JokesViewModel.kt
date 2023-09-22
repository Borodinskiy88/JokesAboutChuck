package ru.borodinskiy.aleksei.jokesaboutchuck.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.borodinskiy.aleksei.jokesaboutchuck.entity.Jokes
import ru.borodinskiy.aleksei.jokesaboutchuck.repository.JokesRepositoryImpl
import javax.inject.Inject

private val empty = Jokes(
    id = "",
    value = ""
)

@HiltViewModel
class JokesViewModel @Inject constructor(
    private val repository: JokesRepositoryImpl
) : ViewModel() {

    private val edited = MutableLiveData(empty)

    val allJokes = repository.allJokes.asLiveData()

    fun getJokeById(jokeId : Int) = repository.getJokeById(jokeId).asLiveData()

    fun getJoke() : LiveData<Jokes> = repository.getJoke().asLiveData()

    fun save() {
        edited.value?.let { insert(it) }
        clear()
    }

    private fun insert(jokes: Jokes) {
        viewModelScope.launch {
            repository.insert(jokes)
        }
    }

    private fun clear() {
        edited.value = empty
    }

    fun changeJoke(value : String, id : String) {
        val text = value.trim()
        if (edited.value?.value == text) {
            return
        }
        edited.value = edited.value?.copy(value = text, id = id)
    }


}