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

package team.sungbinland.sseukssak.data.search.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import team.sungbinland.sseukssak.util.UiState


@Dao
interface SearchDao {

    @Insert
    fun insertSearch(entity: SearchEntity): Flow<UiState<String>>

    @Delete
    fun deleteSearchAll(): Flow<UiState<String>>

    @Query("SELECT * FROM search_table")
    fun getSearchAll(): Flow<List<SearchEntity>>

    @Query("DELETE FROM search_table WHERE id=:idx")
    fun deleteSearch(idx: Int): Flow<UiState<String>>

}