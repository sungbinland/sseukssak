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

class SearchRepositoryImpl(
    private val dao: SearchDao
) : SearchRepository {
    override fun insertSearch(entity: SearchEntity) = flow {
        emit(UiState.Loading)

        dao.insertSearch(entity).collect {
            emit(UiState.Success(SUCCESS_MESSAGE))
        }
    }.catch { e ->
        emit(UiState.Error(e))
    }


    override fun deleteSearchAll() = flow {
        emit(UiState.Loading)

        dao.deleteSearchAll().collect {
            emit(UiState.Success(SUCCESS_MESSAGE))
        }
    }.catch { e ->
        emit(UiState.Error(e))
    }

    override fun getSearchAll() = flow {
        emit(UiState.Loading)
        dao.getSearchAll().collect {
            emit(UiState.Success(it))
        }
    }.catch { e ->
        emit(UiState.Error(e))

    }


    override fun deleteSearch(idx: Int) = flow {
        emit(UiState.Loading)

        dao.deleteSearch(idx).collect {
            emit(UiState.Success(SUCCESS_MESSAGE))
        }
    }.catch { e ->
        emit(UiState.Error(e))
    }

    companion object {
        const val SUCCESS_MESSAGE = "성공했습니다."
    }
}