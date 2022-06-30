/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.data.create.repository

import com.google.firebase.firestore.FirebaseFirestore
import io.github.jisungbin.logeukes.logeukes
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import team.sungbinland.sseukssak.data.create.model.NewCreateSseukssak
import team.sungbinland.sseukssak.util.extensions.Result
import javax.inject.Inject

class NewSseukssakRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : NewSseukssakRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun createSseukssak(newCreateSseukssak: NewCreateSseukssak) = flow {
        runCatching {
            suspendCancellableCoroutine<Unit> { continuation ->
                fireStore.collection("newCreateSseukssak").add(newCreateSseukssak)
                continuation.resume(Unit, null)
            }
        }.onFailure {throwable->
            logeukes { throwable }
            emit(Result.Error(throwable))
        }.onSuccess {
            emit(Result.Success("성공"))
            logeukes { "성공" }
        }
    }
}
