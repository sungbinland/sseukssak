/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.fragment.search

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import team.sungbinland.sseukssak.base.BaseViewModel
import team.sungbinland.sseukssak.data.search.SearchRepository
import team.sungbinland.sseukssak.data.search.db.SearchEntity
import team.sungbinland.sseukssak.util.UiState

class SearchViewModel(
    private val repository: SearchRepository
) : BaseViewModel() {

    val getAllSearch: StateFlow<UiState<List<SearchEntity>>> =
        repository.getSearchAll()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = UiState.Uninitialized
            )

    fun insertSearch(entity: SearchEntity): StateFlow<UiState<String>> =
        repository.insertSearch(entity)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = UiState.Uninitialized
            )

    fun deleteSearch(idx: Int): StateFlow<UiState<String>> =
        repository.deleteSearch(idx)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = UiState.Uninitialized
            )

    fun deleteAllSearch(): StateFlow<UiState<String>> =
        repository.deleteSearchAll()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = UiState.Uninitialized
            )


}