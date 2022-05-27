/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.TYPE_WIFI
import android.net.NetworkCapabilities
import android.os.Build

object NetworkUtil {
    /**
     * 네트워크 상태 체크
     *
     * @return [Boolean] 네트워크 이용 가능 여부
     */
    @Suppress("MissingPermission", "DEPRECATION")
    fun isNetworkAvailable(context: Context) =
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getNetworkCapabilities(activeNetwork)?.run {
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                            hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                } ?: false
            } else {
                val activeNetworkInfo = activeNetworkInfo
                if (activeNetworkInfo != null) {
                    val type = activeNetworkInfo.type
                    if (type == ConnectivityManager.TYPE_MOBILE) {
                        return true
                    } else if (type == TYPE_WIFI) {
                        return true
                    }
                }
                false
            }
        }
}
