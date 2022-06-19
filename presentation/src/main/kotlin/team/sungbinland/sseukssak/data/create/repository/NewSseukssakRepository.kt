/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.data.create.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import team.sungbinland.sseukssak.data.create.model.NewCreateSseukssak

interface NewSseukssakRepository {

    fun createSseukssak(newCreateSseukssak: NewCreateSseukssak): Task<DocumentReference>

}