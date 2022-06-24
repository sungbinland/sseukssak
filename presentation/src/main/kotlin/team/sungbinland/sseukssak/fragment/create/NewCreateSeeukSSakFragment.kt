/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */




package team.sungbinland.sseukssak.fragment.create

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import io.github.jisungbin.logeukes.logeukes
import kotlinx.coroutines.launch
import team.sungbinland.sseukssak.R
import team.sungbinland.sseukssak.base.BaseFragment
import team.sungbinland.sseukssak.data.create.model.NewCreateSseukssak
import team.sungbinland.sseukssak.databinding.FragmentNewCreateSseukssakBinding
import team.sungbinland.sseukssak.util.extensions.UiState
import team.sungbinland.sseukssak.util.extensions.toast

@AndroidEntryPoint
class NewCreateSeeukSSakFragment :
    BaseFragment<FragmentNewCreateSseukssakBinding>(R.layout.fragment_new_create_sseukssak) {

    private val viewModel: NewCreateSseukssakViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            binding.view = this@NewCreateSeeukSSakFragment
            vm = viewModel
        }
        observeUiState()
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { uiState ->
                    when (uiState) {
                        is UiState.Success<*> -> {
                            logeukes { "성공 : ${uiState.data}" }
                            toast("성공")
                            //todo list fragment 로 이동
                        }
                        is UiState.Error -> toast("쓱싹 생성 실패 : ${uiState.error}")
                        else -> logeukes { uiState }

                    }
                }
        }
    }


    fun newCreateSseukssak() {
        val memo = binding.memoEditText.text.toString()
        val title = binding.titleEditText.text.toString()
        val link = binding.linkEditTxt.text.toString()
        val hashTag = hashTag(binding.hashtagEditText.text.toString())

        logeukes { hashTag }
        when {
            memo.isEmpty() -> toast("메모를 입력해 주세요.")
            title.isEmpty() -> toast("제목를 입력해 주세요.")
            link.isEmpty() -> toast("링크를 입력해 주세요.")
            hashTag.isEmpty() -> toast("hashTag를 입력해 주세요.")
            else -> {
                val data = NewCreateSseukssak(memo, title, link, hashTag)
                viewModel.newCreateSseukssak(data)
            }
        }
    }

    private fun hashTag(hashTag: String): List<String> {

        return hashTag.split(",")

    }

}