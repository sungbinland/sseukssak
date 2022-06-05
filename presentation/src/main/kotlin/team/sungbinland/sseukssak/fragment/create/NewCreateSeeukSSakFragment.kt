/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.fragment.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import team.sungbinland.sseukssak.R
import team.sungbinland.sseukssak.base.BaseFragment
import team.sungbinland.sseukssak.databinding.FragmentNewCreateSseukssakBinding
import team.sungbinland.sseukssak.util.extensions.UiState
import team.sungbinland.sseukssak.util.extensions.toast

class NewCreateSeeukSSakFragment :
    BaseFragment<FragmentNewCreateSseukssakBinding>(R.layout.fragment_new_create_sseukssak) {

    private val viewModel: NewCreateSseukssakViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.apply {
            view = this@NewCreateSeeukSSakFragment
            vm = viewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUiState()
    }

    private fun observeUiState(){
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { uiState ->
                when (uiState) {
                    is UiState.Success<*> -> {
                        // todo list 화면으로 이동
                    }
                    is UiState.Error -> toast("쓱싹 생성 실패")
                }
            }
        }
    }

    fun newCreateSseukssak() {
        val memo = binding.memoEditText.text.toString()
        val title = binding.titleEditText.text.toString()
        val link = binding.linkEditTxt.text.toString()
        val hashTag = hashTag(binding.hashtagEditText.text.toString())

        when {
            memo == "" -> toast("메모를 입력해 주세요.")
            title == "" -> toast("제목를 입력해 주세요.")
            link == "" -> toast("링크를 입력해 주세요.")
            hashTag == emptyArray<String>() -> toast("hashTag를 입력해 주세요.")
            else -> {
                val data =
                    NewCreateSseukssakViewModel.NewCreateSseukssak(memo, title, link, hashTag)
                viewModel.newCreateSseukssak(data)
            }
        }
    }

    private fun hashTag(hashTag: String): ArrayList<String> {

        return if (hashTag.contains(",")) {
            hashTag.split(",") as ArrayList<String>
        } else {
            arrayListOf(hashTag)
        }

    }

}