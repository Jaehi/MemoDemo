package com.applemango.memodemo.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.applemango.memodemo.repository.MemoRepositoryImpl
import com.applemango.memodemo.data.MemoDao
import com.applemango.memodemo.data.MemoDataBase
import com.applemango.memodemo.repository.MemoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DiModule {

    @Singleton
    @Provides
    fun provideMemoDatabase(@ApplicationContext context: Context): MemoDataBase {

        val MIGRATION_1_2 = object  : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DROP TABLE table_memo")
                database.execSQL("""CREATE TABLE new_memo_table ( title TEXT NOT NULL DEFAULT '', content TEXT NOT NULL DEFAULT '', date TEXT NOT NULL DEFAULT '', id INTEGER PRIMARY KEY NOT NULL DEFAULT 0)""".trimIndent())
                database.execSQL("ALTER TABLE new_memo_table RENAME TO table_memo")

            }

        }
        return Room.databaseBuilder(context, MemoDataBase::class.java, "database_memo").addMigrations(MIGRATION_1_2).build()
    }
    @Provides
    fun provideMemoDAO(memoDataBase: MemoDataBase): MemoDao {
        return memoDataBase.memoDao()
    }

}

@Module
@InstallIn(ViewModelComponent::class)
abstract class BindModule {
    @Binds
    abstract fun bindMemoRepository(memoRepositoryImpl: MemoRepositoryImpl): MemoRepository
}