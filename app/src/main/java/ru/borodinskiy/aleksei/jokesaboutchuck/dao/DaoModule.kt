package ru.borodinskiy.aleksei.jokesaboutchuck.dao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.borodinskiy.aleksei.jokesaboutchuck.db.JokesDatabase

@InstallIn(SingletonComponent::class)
@Module
object DaoModule {
    @Provides
    fun provideJokesDao(db: JokesDatabase): JokesDao = db.jokesDao()
}