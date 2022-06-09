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
) : ListAdapter<SearchEntity, SearchAdapter.SearchViewHolder>(
    diffUtil
) {

    class SearchViewHolder(private val binding: ItemSearchSseukssakBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(entity: SearchEntity, viewModel: SearchViewModel) {

            binding.entity = entity
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding: ItemSearchSseukssakBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_search_sseukssak,
            parent,
            false
        )

        return SearchViewHolder(binding)

    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position), viewModel)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<SearchEntity>() {
            override fun areContentsTheSame(oldItem: SearchEntity, newItem: SearchEntity) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: SearchEntity, newItem: SearchEntity) =
                oldItem.id == newItem.id
        }
    }
}