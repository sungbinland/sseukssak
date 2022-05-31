/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.dto

/**
 * 카카오 로그인으로 얻는 프로필 객체
 *
 * @property id 유저 UUID
 * @property nickname 유저 닉네임
 * @property profileImageSrc 프로필 사진
 * String(프로필 이미지 주소) or ColorDrawable(단일 색) 이 될 수 있음
 */
data class KakaoProfile(
    val id: Long,
    val nickname: String,
    val profileImageSrc: Any,
)