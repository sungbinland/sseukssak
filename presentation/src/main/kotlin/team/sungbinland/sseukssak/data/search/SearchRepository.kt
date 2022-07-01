/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.data.search

import kotlinx.coroutines.flow.Flow
import team.sungbinland.sseukssak.data.search.db.SearchEntity

interface SearchRepository {
    /**
     * Search 추가
     */
    suspend fun insert(entity: SearchEntity)

    /**
     * Search 데이터 모두 삭제
     */
    suspend fun deleteAll()

    /**
     * Search 데이터 갱신
     * @return Search 데이터 갱신 후 반환된 데이터
     */
    fun getAll(): Flow<List<SearchEntity>>

    /**
     * 특정 Search 데이터 삭제
     */
    suspend fun delete(entity: SearchEntity)
}
