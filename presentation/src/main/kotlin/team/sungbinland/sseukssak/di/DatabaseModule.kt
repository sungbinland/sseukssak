/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import team.sungbinland.sseukssak.data.search.db.SearchDao
import team.sungbinland.sseukssak.data.search.db.SearchDataBase
import team.sungbinland.sseukssak.data.search.db.SearchDataBase.Companion.SEARCH_DATABASE
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): SearchDataBase =
        Room.databaseBuilder(
            context,
            SearchDataBase::class.java,
            SEARCH_DATABASE
        ).build()

    @Provides
    @Singleton
    fun provideDao(dataBase: SearchDataBase): SearchDao = dataBase.searchDao()

}