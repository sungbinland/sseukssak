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
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import team.sungbinland.sseukssak.data.search.SearchRepositoryImpl
import team.sungbinland.sseukssak.data.search.db.SearchDao

@InstallIn(ViewModelComponent::class)
@Module
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideSearchRepository(dao: SearchDao) = SearchRepositoryImpl(dao)

}