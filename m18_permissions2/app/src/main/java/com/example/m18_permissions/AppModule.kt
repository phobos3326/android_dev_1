package com.example.m18_permissions

import android.content.Context
import androidx.room.Room
import com.example.m18_permissions.database.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providePhotoDataBase(
        @ApplicationContext app:Context) = Room.databaseBuilder(
        app, AppDataBase::class.java,
        "db"
        ).build()

    @Singleton
    @Provides
    fun providePhotoDao(dataBase: AppDataBase)=dataBase.photoDao()

}