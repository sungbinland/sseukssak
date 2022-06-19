/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.data.create.model

data class NewCreateSseukssak(
    val link: String = "",
    val title: String = "",
    val memo: String = "",
    val hashTag: List<String> = listOf()
)