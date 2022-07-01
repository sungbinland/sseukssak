/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.fragment.create

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
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

    // UiState가 굳이 필요한지?, Resuilt로 만들면 되는거 아닌지 고민.
    private val _uiState: MutableStateFlow<UiState<String>> =
        MutableStateFlow(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    /**
     *
     * @param data  쓱싹 생성하기 모델
     * @return reulst가 성공했을 시 UiState에 성공시 성공 데이터를 UiState 넣어준다. result가 실패시 에러 메시지를 UiState에 넣어준다.
     *
     */

    fun newCreateSseukssak(data: NewCreateSseukssak) {

        viewModelScope.launch {
            _uiState.emit(UiState.Loading)
            when (val result = repository.createSseukssak(data)) {
                is Result.Success -> {
                    _uiState.emit(UiState.Success(result.data))
                }
                is Result.Error -> {
                    _uiState.emit(UiState.Error(result.throwable))
                }
            }
        }
    }
}
