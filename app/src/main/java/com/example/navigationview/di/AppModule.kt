package com.example.navigationview.di

import android.content.Context
import androidx.room.Room
import com.example.navigationview.NavigationApplication
import com.example.navigationview.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return NavigationApplication.instance
    }

    @Provides
    @Singleton
    fun provideApplicationContext(): NavigationApplication {
        return NavigationApplication()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}