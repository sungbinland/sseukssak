/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.sungbinland.sseukssak.data.search.SearchRepository
import team.sungbinland.sseukssak.data.search.SearchRepositoryImpl
import team.sungbinland.sseukssak.data.search.db.SearchDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideSearchRepository(dao: SearchDao): SearchRepository {
        return SearchRepositoryImpl(dao)
    }
}