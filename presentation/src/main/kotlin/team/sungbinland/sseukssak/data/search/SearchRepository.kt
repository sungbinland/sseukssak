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

    fun insertSearch(entity: SearchEntity): Flow<UiState<String>>

    fun deleteSearchAll(): Flow<UiState<String>>


    fun getSearchAll(): Flow<UiState<List<SearchEntity>>>

    fun deleteSearch(idx: Int): Flow<UiState<String>>

}