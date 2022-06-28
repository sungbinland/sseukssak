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

    fun getAll() {
        viewModelScope.launch {
            _uiState.emit(UiState.Loading)
            repository.getAll()
                .catch { e ->
                    _uiState.emit(UiState.Error(e))
                }
                .collect {
                    _uiState.emit(UiState.Success(it))
                }
        }
    }

    fun insert(entity: SearchEntity) {
        viewModelScope.launch {
            repository.insert(entity)
        }
    }

    fun delete(entity: SearchEntity) {
        viewModelScope.launch {
            repository.delete(entity)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}
