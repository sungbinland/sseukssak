/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.data.create.repository

import kotlinx.coroutines.flow.Flow
import team.sungbinland.sseukssak.data.create.model.NewCreateSseukssak
import team.sungbinland.sseukssak.util.extensions.Result

interface NewSseukssakRepository {

    suspend fun createSseukssak(newCreateSseukssak: NewCreateSseukssak): Flow<Result>
}