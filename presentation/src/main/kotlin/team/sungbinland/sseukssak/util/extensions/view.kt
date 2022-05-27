/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.util.extensions

import android.view.View

/**
 * 뷰 상태를 VISIBLE 로 설정하는 확장 함수
 */
fun View.show() {
    visibility = View.VISIBLE
}

/**
 * 뷰 상태를 GONE/INVISIBLE 로 설정하는 확장 함수
 *
 * @param isGone [View.GONE] 으로 설정 해야하는지 여부
 * 기본값 false, [View.INVISIBLE] 사용
 */
fun View.hide(isGone: Boolean = false) {
    visibility = when (isGone) {
        true -> View.GONE
        else -> View.INVISIBLE
    }
}
