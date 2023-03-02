package com.applemango.memodemo.di

import android.content.Context
import androidx.room.Room
import com.applemango.memodemo.repository.MemoRepositoryImpl
import com.applemango.memodemo.data.MemoDao
import com.applemango.memodemo.data.MemoDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DiModule {

    @Singleton
    @Provides
    fun provideMemoDatabase(@ApplicationContext context: Context) : MemoDataBase{
        return Room.databaseBuilder(context,MemoDataBase::class.java,"database_memo").build()
    }

    @Provides
    fun provideMemoDAO(memoDataBase: MemoDataBase) : MemoDao {
        return memoDataBase.memoDao()
    }

    @Singleton
    @Provides
    fun provideMemoRepository(memoDao: MemoDao) : MemoRepositoryImpl {
        return MemoRepositoryImpl(memoDao)
    }
}