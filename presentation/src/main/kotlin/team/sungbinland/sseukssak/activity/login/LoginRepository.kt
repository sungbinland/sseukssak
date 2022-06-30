/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.activity.login

import android.content.SharedPreferences
import team.sungbinland.sseukssak.util.extensions.get
import team.sungbinland.sseukssak.util.extensions.set
import javax.inject.Inject

class LoginRepository @Inject constructor(private val preference: SharedPreferences) {
    fun saveUserId(userId: Long) {
        preference[USER_ID] = userId
    }

    fun checkLoggedIn(): Boolean {
        return preference[USER_ID, 0L] != 0L
    }

    companion object {
        private const val USER_ID = "user-id"
    }
}
