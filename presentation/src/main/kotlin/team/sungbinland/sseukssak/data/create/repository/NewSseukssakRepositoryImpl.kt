/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.data.create.repository

import com.google.firebase.firestore.FirebaseFirestore
import team.sungbinland.sseukssak.data.create.model.NewCreateSseukssak
import javax.inject.Inject

class NewSseukssakRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : NewSseukssakRepository {
    override fun createSseukssak(newCreateSseukssak: NewCreateSseukssak) =
        fireStore.collection("newCreateSseukssak").add(newCreateSseukssak)
}
