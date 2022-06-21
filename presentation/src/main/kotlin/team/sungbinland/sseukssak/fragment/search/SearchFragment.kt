/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.fragment.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import io.github.jisungbin.logeukes.logeukes
import kotlinx.coroutines.launch
import team.sungbinland.sseukssak.BR
import team.sungbinland.sseukssak.R
import team.sungbinland.sseukssak.base.BaseFragment
import team.sungbinland.sseukssak.data.search.db.SearchEntity
import team.sungbinland.sseukssak.databinding.FragmentSearchBinding
import team.sungbinland.sseukssak.util.UiState

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search),
    DeleteItemClickListener<SearchEntity> {

    private val searchViewModel: SearchViewModel by viewModels()
    private val searchAdapter: SearchAdapter by lazy {
        SearchAdapter(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // onCreateView 에 쓸 경우 binding에 초기화가 안되 오류 생김
        binding.apply {
            binding.view = this@SearchFragment
            viewModel = searchViewModel
            adapter = searchAdapter

        }
        searchSetting()
        getAllSearch()
    }

    private fun searchSetting() {
        binding.searchView.queryHint = getString(R.string.search_for_anything)
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewLifecycleOwner.lifecycleScope.launch {
                    searchViewModel.insertSearch(SearchEntity(searchText = query.orEmpty(), 0))
                }
                // todo list fragment로 이동
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }

        })

    }


    private fun getAllSearch() {

        viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.getAllSearch().flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    when (it) {
                        is UiState.Loading -> {

                            logeukes { "로딩중.." }
                        }
                        is UiState.Success -> {
                            logeukes { "성공 : ${it.data}" }

                            searchAdapter.submitList(it.data)

                        }
                        is UiState.Error -> {
                            logeukes { "에러 : ${it.error}" }
                        }
                        UiState.Uninitialized -> {}
                    }
                }

        }

    }

    override fun onclick(data: SearchEntity) = searchViewModel.deleteSearch(data)

}