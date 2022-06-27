/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.fragment.create

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jisungbin.logeukes.logeukes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import team.sungbinland.sseukssak.base.BaseViewModel
import team.sungbinland.sseukssak.data.create.model.NewCreateSseukssak
import team.sungbinland.sseukssak.data.create.repository.NewSseukssakRepository
import team.sungbinland.sseukssak.util.extensions.Result
import team.sungbinland.sseukssak.util.extensions.UiState
import javax.inject.Inject

@HiltViewModel
class NewCreateSseukssakViewModel @Inject constructor(
    private val repository: NewSseukssakRepository
) : BaseViewModel() {

    private val _uiState: MutableStateFlow<UiState<String>> =
        MutableStateFlow(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun newCreateSseukssak(data: NewCreateSseukssak) {

        viewModelScope.launch {

            repository.createSseukssak(data).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _uiState.emit(UiState.Success(result.data))
                        logeukes("viewModel") { "성공" }
                    }
                    is Result.Error -> {
                        _uiState.emit(UiState.Error(result.throwable))
                    }
                }
            }
        }
    }
}
