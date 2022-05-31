/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.util.extensions

import android.content.SharedPreferences
import androidx.core.content.edit

/**
 * [SharedPreferences] 확장 함수
 */

operator fun SharedPreferences.get(name: String, default: String? = null) = getString(name, default)

operator fun SharedPreferences.set(name: String, value: String) = edit { putString(name, value) }
