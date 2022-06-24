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

    suspend fun insert(entity: SearchEntity)

    suspend fun deleteAll()

    suspend fun getAll(): Flow<List<SearchEntity>>

    suspend fun delete(entity: SearchEntity)

}