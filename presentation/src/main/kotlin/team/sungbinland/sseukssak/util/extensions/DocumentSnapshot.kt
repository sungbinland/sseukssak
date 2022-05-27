/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.util.extensions

import com.google.firebase.firestore.DocumentSnapshot

/**
 * 파이어베이스 [DocumentSnapshot] 를 NonNull 인 객체로 변환하는데 사용
 *
 * **!! 으로 캐스팅 하기에 무조건 null 이 아닌 대상에만 사용해야 함**
 *
 * ```
 * documents.map(DocumentSnapshot::toObjectNonNull)
 * ```
 *
 * @return null 이 아닌 [T] 값
 */
inline fun <reified T> DocumentSnapshot.toObjectNonNull(): T = toObject(T::class.java)!!
