/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.util.extensions

sealed class UiState {
    object Loading: UiState()
    data class Success<T>(val data: T): UiState()
    data class Error(val error: Throwable?): UiState() 
}