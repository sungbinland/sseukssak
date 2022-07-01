/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.data.create.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import team.sungbinland.sseukssak.data.create.model.NewCreateSseukssak
import team.sungbinland.sseukssak.util.extensions.Result
import javax.inject.Inject

class NewSseukssakRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : NewSseukssakRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun createSseukssak(newCreateSseukssak: NewCreateSseukssak) =
        suspendCancellableCoroutine<Result> { continuation ->
            fireStore.collection("sseukssak").add(newCreateSseukssak)
                .addOnSuccessListener {
                    continuation.resume(Result.Success("성공"), null)
                }.addOnFailureListener { exception ->
                    continuation.resume(Result.Error(exception), null)
                }
        }
}
