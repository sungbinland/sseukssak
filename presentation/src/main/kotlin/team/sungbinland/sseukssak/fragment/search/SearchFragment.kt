/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.fragment.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import io.github.jisungbin.logeukes.logeukes
import kotlinx.coroutines.launch
import team.sungbinland.sseukssak.R
import team.sungbinland.sseukssak.base.BaseFragment
import team.sungbinland.sseukssak.data.search.db.SearchEntity
import team.sungbinland.sseukssak.databinding.FragmentSearchBinding
import team.sungbinland.sseukssak.util.UiState

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentSearchBinding>(R.layout.fragment_search),
    ItemClickListener<SearchEntity> {

    private val searchViewModel: SearchViewModel by viewModels()
    private val searchAdapter: SearchAdapter by lazy {
        SearchAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewModel = searchViewModel
        }
        searchSetting()
        getAllSearch()
        setAdapter()
        uiStateEvent()
    }

    private fun searchSetting() {
        binding.svEdit.queryHint = getString(R.string.search_for_anything)
        binding.svEdit.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                viewLifecycleOwner.lifecycleScope.launch {
                    searchViewModel.insert(SearchEntity(searchText = query.orEmpty()))
                }
                // todo list fragment로 이동
                return false
            }

            override fun onQueryTextChange(newText: String?) = true
        })
    }

    private fun setAdapter() {
        binding.rvSearch.apply {
            adapter = searchAdapter
        }
    }

    private fun getAllSearch() {
        viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.getAll()
        }
    }

    // todo 함수 이름 변경
    private fun uiStateEvent() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            searchViewModel.uiState.flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect { uiState ->
                    when (uiState) {
                        is UiState.Loading -> {
                            logeukes { "로딩중.." }
                        }
                        is UiState.Success -> {
                            logeukes { "성공 : ${uiState.data}" }
                            searchAdapter.submitList(uiState.data)
                        }
                        is UiState.Error -> {
                            logeukes { "에러 : ${uiState.error}" }
                        }
                    }
                }
        }
    }

    override fun onDeleteClick(data: SearchEntity) = searchViewModel.delete(data)
}
