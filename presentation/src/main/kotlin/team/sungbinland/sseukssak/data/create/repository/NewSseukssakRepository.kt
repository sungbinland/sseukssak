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
 * 쓱싹 생성하기
 *
 * @param newCreateSseukssak 쓱싹 생성하기 모델
 *
 * @return 쓱싹 생성하기가 성공되면 "성공" 메세지가 반환되고, 실패하면 throwable 반환됨
 */

interface NewSseukssakRepository {

    suspend fun createSseukssak(newCreateSseukssak: NewCreateSseukssak): Flow<Result>
}
