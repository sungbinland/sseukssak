/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.di


import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import team.sungbinland.sseukssak.data.create.repository.NewSseukssakRepository
import team.sungbinland.sseukssak.data.create.repository.NewSseukssakRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    /**
     * 타입 지정안해주면 오류남
     * ex) :NewSseukssakRepository
     */
    @Provides
    fun provideNewSseukssaRepository(fireStore: FirebaseFirestore) : NewSseukssakRepository = NewSseukssakRepositoryImpl(fireStore)

}