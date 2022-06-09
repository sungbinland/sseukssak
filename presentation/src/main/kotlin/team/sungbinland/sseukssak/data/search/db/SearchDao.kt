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
    suspend fun insertSearch(entity: SearchEntity)

    @Query("DELETE FROM search_table")
    suspend fun deleteSearchAll()

    @Query("SELECT * FROM search_table")
    fun getSearchAll(): Flow<List<SearchEntity>>

    @Query("DELETE FROM search_table WHERE id=:idx")
    suspend fun deleteSearch(idx: Int)

}