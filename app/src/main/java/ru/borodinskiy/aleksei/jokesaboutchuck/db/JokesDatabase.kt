package ru.borodinskiy.aleksei.jokesaboutchuck.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.borodinskiy.aleksei.jokesaboutchuck.dao.JokesDao
import ru.borodinskiy.aleksei.jokesaboutchuck.entity.Jokes

@Database(entities = [Jokes::class], version = 1, exportSchema = false)
abstract class JokesDatabase : RoomDatabase() {

    abstract fun jokesDao(): JokesDao

}