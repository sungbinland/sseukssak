/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.github.jisungbin.logeukes.Logeukes

@HiltAndroidApp
class SseukSsak : Application() {
    override fun onCreate() {
        super.onCreate()

        // TODO: Excpetion Activity
        /*Erratum.setup(
            application = this,
            registerExceptionActivityIntent = { _, throwable, lastActivity ->
                Intent(lastActivity, ErratumExceptionActivity::class.java).apply {
                    putExtra(ErratumExceptionActivity.EXTRA_EXCEPTION_STRING, throwable.toString())
                    putExtra(
                        ErratumExceptionActivity.EXTRA_LAST_ACTIVITY_INTENT,
                        lastActivity.intent
                    )
                    putExtra(Key.Intent.Error, Key.Intent.Exception)
                }
            }
        )*/
        // 디버그 모드일 때만 로그켓 출력 활성화
        if (BuildConfig.DEBUG) {
            Logeukes.setup()
        }
    }
}
