/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.fragment.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import team.sungbinland.sseukssak.R
import team.sungbinland.sseukssak.base.BaseFragment
import team.sungbinland.sseukssak.databinding.FragmentSearchBinding
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import team.sungbinland.sseukssak.data.search.db.SearchEntity
import team.sungbinland.sseukssak.util.UiState

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val viewModel: SearchViewModel by viewModels()
    private val searchAdapter: SearchAdapter by lazy {
        SearchAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.apply {
            view = this@SearchFragment
            viewModel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                    viewModel.insertSearch(SearchEntity(searchText = query ?: ""))

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

        when (val value = viewModel.getAllSearch.value) {
            is UiState.Loading -> {

                Log.d("TAG", "getAllSearch: 로딩중..")
            }
            is UiState.Success -> {

                searchAdapter.submitList(value.data)
            }
            is UiState.Error -> {
                Log.d("TAG", "getAllSearch: error message : ${value.error}")

            }
            UiState.Uninitialized -> TODO()
        }
    }
}