/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.dto


/**
 * 쓱싹(게시글 정보) 객체
 *
 * @property id id
 * @property title 제목
 * @property contents 메모
 * @property img 이미지
 * @property comments 댓글 개수
 * @property likes 좋아요 개수
 * @property stars 즐겨찾기 개수
 */
data class Sseukssak(
    val id: String,
    val title: String,
    val contents: String,
    val img: String,
    val comments: String,
    val likes: String,
    val stars: String
)
