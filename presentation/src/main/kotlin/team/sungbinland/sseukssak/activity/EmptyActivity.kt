/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.activity

import androidx.activity.viewModels
import team.sungbinland.sseukssak.R
import team.sungbinland.sseukssak.base.BaseActivity
import team.sungbinland.sseukssak.databinding.ActivityEmptyBinding

class EmptyActivity : BaseActivity<ActivityEmptyBinding, EmptyViewModel>(R.layout.activity_empty) {
    override val vm: EmptyViewModel by viewModels()
}