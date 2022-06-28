/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.data.search

import team.sungbinland.sseukssak.data.search.db.SearchDao
import team.sungbinland.sseukssak.data.search.db.SearchEntity
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val dao: SearchDao
) : SearchRepository {

    override fun getAll() = dao.getAll()

    override suspend fun deleteAll() = dao.deleteAll()

    override suspend fun insert(entity: SearchEntity) = dao.insert(entity)

    override suspend fun delete(entity: SearchEntity) = dao.delete(entity)
}
