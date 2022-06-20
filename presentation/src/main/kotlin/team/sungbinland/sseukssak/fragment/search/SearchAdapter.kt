/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.fragment.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import team.sungbinland.sseukssak.R
import team.sungbinland.sseukssak.data.search.db.SearchEntity
import team.sungbinland.sseukssak.databinding.ItemSearchSseukssakBinding

class SearchAdapter(
    val viewModel: SearchViewModel
) : ListAdapter<SearchEntity, SearchAdapter.SearchViewHolder>(SearchDiffCallback()) {

    class SearchViewHolder(private val binding: ItemSearchSseukssakBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(entity: SearchEntity, viewModel: SearchViewModel) {

            binding.entity = entity
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {

        return SearchViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_search_sseukssak,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position), viewModel)
    }


}

private class SearchDiffCallback : DiffUtil.ItemCallback<SearchEntity>() {
    override fun areItemsTheSame(oldItem: SearchEntity, newItem: SearchEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SearchEntity, newItem: SearchEntity): Boolean {
        return oldItem == newItem
    }
}