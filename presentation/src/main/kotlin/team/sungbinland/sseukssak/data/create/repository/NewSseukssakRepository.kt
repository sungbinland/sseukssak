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

/**
 * 함수 설명
 * @param newCreateSseukssak : 쓱싹  dto
 * createSseukssak : 쓱싹 생성하는 함수
 */

interface NewSseukssakRepository {

    suspend fun createSseukssak(newCreateSseukssak: NewCreateSseukssak): Flow<Result>
}
