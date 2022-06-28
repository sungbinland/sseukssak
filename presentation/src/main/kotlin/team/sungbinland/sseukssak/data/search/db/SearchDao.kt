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

/**
 * 함수설명
 * insert : 쓱싹 아이템 추가
 * deleteAll : 모든 쓱싹 아이템 제거
 * delete : 특정 쓱싹 아이템 제거
 * getAll : 모든 쓱싹아이템 가져오기
 */

@Dao
interface SearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: SearchEntity)

    @Query("DELETE FROM search_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM search_table")
    fun getAll(): Flow<List<SearchEntity>>

    @Delete
    suspend fun delete(entity: SearchEntity)
}
