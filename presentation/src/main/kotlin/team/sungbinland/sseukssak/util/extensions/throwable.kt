/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.util.extensions

import android.content.Context
import team.sungbinland.sseukssak.BuildConfig

/**
 * 런타임에서 발생한 에러 메시지를 빌드 환경에 맞게
 * 제공하는 확장함수
 *
 * 만약 디버그 빌드라면 에러 메시지 그대로 반환함
 * 만약 릴리즈 빌드라면 대체 메시지를 반환함
 *
 * @return [String] 수정된 에러 메시지
 */
fun Throwable.toMessage(context: Context) = when (BuildConfig.DEBUG) {
    true -> message ?: "Unexpected error occured, but exception message is null."
    else -> "알 수 없은 에러 발생" // TODO
}
