/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.data.search

import kotlinx.coroutines.flow.Flow
import team.sungbinland.sseukssak.data.search.db.SearchEntity
import team.sungbinland.sseukssak.util.UiState

interface SearchRepository {

    suspend fun insertSearch(entity: SearchEntity)

    suspend fun deleteSearchAll()

    fun getSearchAll(): Flow<UiState<List<SearchEntity>>>

    suspend fun deleteSearch(idx: Int)

}