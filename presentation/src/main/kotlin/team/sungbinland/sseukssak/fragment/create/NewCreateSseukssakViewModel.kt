/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.fragment.create

import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.DocumentReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import team.sungbinland.sseukssak.base.BaseViewModel
import team.sungbinland.sseukssak.data.create.model.NewCreateSseukssak
import team.sungbinland.sseukssak.data.create.repository.NewSseukssakRepository
import team.sungbinland.sseukssak.util.extensions.UiState
import javax.inject.Inject

@HiltViewModel
class NewCreateSseukssakViewModel @Inject constructor(
    private val repository: NewSseukssakRepository
) : BaseViewModel() {

    private val _uiState: MutableStateFlow<UiState<DocumentReference>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<DocumentReference>> = _uiState.asStateFlow()


    fun newCreateSseukssak(data: NewCreateSseukssak) {

        repository.createSseukssak(data).addOnSuccessListener {
            viewModelScope.launch {
                _uiState.emit(UiState.Success(it))
            }

        }
        repository.createSseukssak(data).addOnFailureListener {
            viewModelScope.launch {
                _uiState.emit(UiState.Error(it))
            }

        }
    }


}