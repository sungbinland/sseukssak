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
    /**
     * 유저 토큰 저장
     *
     * @param userId UserApiClient.instance.me.user.id 저장
     * */
    fun saveUserId(userId: Long) {
        preference[USER_ID] = userId
    }

    /**
     * 로그인 상태 확인
     *
     * @return 유저 id가 존재하는 경우: true / 존재하지 않는 경우: false
     * */
    fun checkLoggedIn(): Boolean {
        return preference[USER_ID, 0L] != 0L
    }

    companion object {
        private const val USER_ID = "user-id"
    }
}
