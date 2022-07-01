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
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchDao {
    /**
     * Search 데이터 추가
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: SearchEntity)

    /**
     * Search 데이터 모두 삭제
     */
    @Query("DELETE FROM search_table")
    suspend fun deleteAll()

    /**
     * Search 데이터 갱신
     * @return Search 데이터 갱신 후 반환된 데이터
     */
    @Query("SELECT * FROM search_table")
    fun getAll(): Flow<List<SearchEntity>>

    /**
     * 특정 Search 데이터 삭제
     */
    @Delete
    suspend fun delete(entity: SearchEntity)
}
