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
import team.sungbinland.sseukssak.R
import team.sungbinland.sseukssak.base.BaseFragment
import team.sungbinland.sseukssak.databinding.FragmentSearchBinding
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import team.sungbinland.sseukssak.BR
import team.sungbinland.sseukssak.data.search.db.SearchEntity
import team.sungbinland.sseukssak.util.UiState

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val searchViewModel: SearchViewModel by viewModels()
    private val searchAdapter: SearchAdapter by lazy {
        SearchAdapter(searchViewModel)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // onCreateView 에 쓸 경우 binding에 초기화가 안되 오류 생김
        binding.apply {
            binding.view = this@SearchFragment
            viewModel = searchViewModel
            setVariable(BR.viewModel, viewModel)

        }
        setAdapter()
        searchSetting()
        getAllSearch()
    }

    private fun searchSetting() {
        binding.searchView.queryHint = getString(R.string.search_for_anything)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                lifecycleScope.launch {
                    searchViewModel.insertSearch(SearchEntity(searchText = query ?: ""))

                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }

        })

    }


    private fun setAdapter() {
        binding.searchRecyclerView.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


    private fun getAllSearch() {

        lifecycleScope.launch {
            searchViewModel.getAllSearch().collect {
                when (it) {
                    is UiState.Loading -> {

                        Log.d("TAG", "getAllSearch: 로딩중..")
                    }
                    is UiState.Success -> {
                        Log.d("TAG", "getAllSearch: 성공 : ${it.data}")

                        searchAdapter.submitList(it.data)

                    }
                    is UiState.Error -> {
                        Log.d("TAG", "getAllSearch: error message : ${it.error}")

                    }
                    UiState.Uninitialized -> {}
                }
            }

        }

    }
}