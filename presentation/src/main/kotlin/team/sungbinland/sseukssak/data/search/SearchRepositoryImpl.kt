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

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import team.sungbinland.sseukssak.data.search.db.SearchDao
import team.sungbinland.sseukssak.data.search.db.SearchEntity
import team.sungbinland.sseukssak.util.UiState
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val dao: SearchDao
) : SearchRepository {


    override fun getSearchAll() = flow {
        emit(UiState.Loading)
        dao.getSearchAll().collect {
            emit(UiState.Success(it))
        }
    }.catch { e ->
        emit(UiState.Error(e))

    }
    override suspend fun deleteSearchAll() = dao.deleteSearchAll()

    override suspend fun insertSearch(entity: SearchEntity) = dao.insertSearch(entity)

    override suspend fun deleteSearch(idx: Int) = dao.deleteSearch(idx)


}