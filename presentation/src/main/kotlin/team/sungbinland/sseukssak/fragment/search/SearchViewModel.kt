/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.fragment.search

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import team.sungbinland.sseukssak.base.BaseViewModel
import team.sungbinland.sseukssak.data.search.SearchRepository
import team.sungbinland.sseukssak.data.search.db.SearchEntity
import team.sungbinland.sseukssak.util.UiState
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : BaseViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<SearchEntity>>> =
        MutableStateFlow(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    /**
     * Search 데이터 갱신
     * @return 실패시 uiState에 throwable 전달 한다.
     * @return 성공했을 시 uiState에 entity를 전달한다.
     *
     */
    fun getAll() {
        viewModelScope.launch {
            _uiState.emit(UiState.Loading)
            repository.getAll()
                .catch { e ->
                    _uiState.emit(UiState.Error(e))
                }
                .collect { entity ->
                    _uiState.emit(UiState.Success(entity))
                }
        }
    }

    /**
     * Search 데이터 추가
     */
    fun insert(entity: SearchEntity) {
        viewModelScope.launch {
            repository.insert(entity)
        }
    }

    /**
     * 특정 Search 데이터 삭제
     */
    fun delete(entity: SearchEntity) {
        viewModelScope.launch {
            repository.delete(entity)
        }
    }

    /**
     * 특정 Search 데이터 전부 삭제
     */
    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}
