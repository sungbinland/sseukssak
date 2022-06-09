/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.fragment.search

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
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

    init {
        getAllSearch()
    }

    fun getAllSearch(): StateFlow<UiState<List<SearchEntity>>> =
        repository.getSearchAll()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = UiState.Uninitialized
            )

    fun insertSearch(entity: SearchEntity) = viewModelScope.launch {
        repository.insertSearch(entity)
    }

    fun deleteSearch(entity: SearchEntity) = viewModelScope.launch {
        repository.deleteSearch(entity.id)
    }

    fun deleteAllSearch() = viewModelScope.launch {
        repository.deleteSearchAll()
    }


}