/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.activity

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import team.sungbinland.sseukssak.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class EmptyViewModel @Inject constructor() : BaseViewModel() {

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun openDrawer() {
        event(Event.OpenDrawer)
    }

    fun openSearch() {
        event(Event.OpenSearch)
    }

    fun openSseukssakList() {
        event(Event.OpenList)
    }

    fun openBoard() {
        event(Event.OpenBoard)
    }

    fun openProfile() {
        event(Event.OpenProfile)
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        object OpenDrawer : Event()
        object OpenSearch : Event()
        object OpenList : Event()
        object OpenBoard : Event()
        object OpenProfile : Event()
        object OpenService : Event()
        object OpenQuestion : Event()
    }
}