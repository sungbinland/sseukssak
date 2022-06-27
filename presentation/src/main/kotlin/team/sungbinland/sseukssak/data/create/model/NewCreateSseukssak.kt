/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.data.create.model

/**
 * @param link : 링크
 * @param title : 쓱싹 제목
 * @param link : 메모
 * @param hashTag : 해시태그
 */
data class NewCreateSseukssak(
    val link: String = "",
    val title: String = "",
    val memo: String = "",
    val hashTag: List<String> = emptyList()
)
