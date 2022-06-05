/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.fragment.create

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import team.sungbinland.sseukssak.base.BaseViewModel
import team.sungbinland.sseukssak.util.extensions.UiState

class NewCreateSseukssakViewModel : BaseViewModel() {

    private val _uiState = MutableStateFlow(UiState.Success(Unit))
    val uiState: StateFlow<UiState> = _uiState

    fun newCreateSseukssak(data: NewCreateSseukssak) {
        //todo 파이어베이스 연결
    }


    data class NewCreateSseukssak(
        val link: String,
        val title: String,
        val memo: String,
        val hashTag: ArrayList<String>
    )
}