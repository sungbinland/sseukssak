/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.util.extensions

import android.graphics.drawable.ColorDrawable
import kotlin.random.Random

/**
 * 기본 사용자 정보 객체
 *
 * 카카오 로그인으로 들어오는 정보가 null 일 때 대체로 사용
 */
object DefaultUserConfig {
    /**
     * 프로필 사진용 랜덤 색상
     *
     * @return [ColorDrawable] 랜덤 색상 drawable
     */
    val randomColorDrawable get() = ColorDrawable((Math.random() * 16777215).toInt() or (0xFF shl 24))

    /**
     * 닉네임용 랜덤 닉네임
     *
     * 규칙: 쓱싹+{1,000 부터 10,000 까지 랜덤 숫자}
     *
     * @return [String] 랜덤 닉네임
     */
    val randomNickname get() = "쓱싹${Random.nextInt(1000, 10000)}"
}
