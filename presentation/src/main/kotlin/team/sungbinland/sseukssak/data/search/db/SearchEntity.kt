/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.data.search.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_table")
data class SearchEntity(
    val searchText: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)