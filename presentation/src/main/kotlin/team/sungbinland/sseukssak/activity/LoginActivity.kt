/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import team.sungbinland.sseukssak.R
import team.sungbinland.sseukssak.base.BaseActivity
import team.sungbinland.sseukssak.databinding.ActivityLoginBinding
import team.sungbinland.sseukssak.util.extensions.repeatOnStarted
import team.sungbinland.sseukssak.activity.LoginViewModel.Event
import team.sungbinland.sseukssak.util.extensions.toast

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(R.layout.activity_login) {

    override val vm: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.vm = vm

        checkLoginState()

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun checkLoginState() {
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
                if (error == null && tokenInfo != null) {
                    Log.i(
                        TAG, "이미 로그인 된 상태임" +
                                "\n회원 번호: ${tokenInfo.id}" +
                                "\n만료시간: ${tokenInfo.expiresIn} 초"
                    )
                    // TODO 메인 화면으로 이동 혹은 로그아웃 처리
                }
            }
        }
    }

    private fun handleEvent(event: Event) = when (event) {
        is Event.KakaoLogin -> kakaoLogin()
        is Event.SkipLogin -> skipLogin()
    }

    private fun kakaoLogin() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e(TAG, "카카오계정으로 로그인 실패", error)
            } else if (token != null) {
                Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
                loginSuccess()
                // TODO 메인 화면으로 이동
            }
        }
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            loginWithKakaoTalkApp(callback)
        } else {
            loginWithKakaoAccount(callback)
        }
    }

    private fun skipLogin() {
        Log.d("LoginActivity", "로그인 건너 뛰기")
    }

    private fun loginWithKakaoTalkApp(callback: (OAuthToken?, Throwable?) -> Unit) {
        UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
            if (error != null) {
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    return@loginWithKakaoTalk
                }
                Log.e(TAG, "loginWithKakaoTalkApp: error", error)
                loginWithKakaoAccount(callback)
            } else if (token != null) {
                // TODO 메인 화면으로 이동
                loginSuccess()
                Log.i(TAG, "로그인 성공 ${token.accessToken}")
            }
        }
    }

    private fun loginWithKakaoAccount(callback: (OAuthToken?, Throwable?) -> Unit) {
        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
    }

    private fun loginSuccess() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                toast("로그인 실패: $error")
            } else if (user != null) {
                toast("${user.kakaoAccount?.profile?.nickname}님 환영합니다!")
            }
        }
    }

    companion object {
        const val TAG = "LoginActivity"
    }
}

