/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 *
*/

package team.sungbinland.sseukssak.fragment.sseukssaklist

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import team.sungbinland.sseukssak.R
import team.sungbinland.sseukssak.base.BaseFragment
import team.sungbinland.sseukssak.databinding.FragmentSseukssakBinding

@AndroidEntryPoint
class SseukssakFragment : BaseFragment<FragmentSseukssakBinding>(R.layout.fragment_sseukssak) {
    val vm: SseukssakViewModel by viewModels()
}
