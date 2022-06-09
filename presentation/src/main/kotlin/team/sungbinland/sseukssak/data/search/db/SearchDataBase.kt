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

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SearchEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SearchDataBase  : RoomDatabase() {
    abstract fun searchDao(): SearchDao

    companion object{
        const val SEARCH_DATABASE = "search_database"
    }
}