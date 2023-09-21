package ru.borodinskiy.aleksei.jokesaboutchuck.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.borodinskiy.aleksei.jokesaboutchuck.entity.Jokes

@Dao
interface JokesDao {

    @Query("SELECT * FROM Jokes ORDER BY id DESC")
    fun getJokes(): Flow<List<Jokes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(joke: Jokes)

}