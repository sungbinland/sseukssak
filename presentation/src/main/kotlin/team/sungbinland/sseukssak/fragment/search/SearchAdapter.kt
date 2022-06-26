/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.fragment.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import team.sungbinland.sseukssak.data.search.db.SearchEntity
import team.sungbinland.sseukssak.databinding.ItemSearchSseukssakBinding

class SearchAdapter(
    private val deleteItem: ItemClickListener<SearchEntity>
) : ListAdapter<SearchEntity, SearchAdapter.SearchViewHolder>(SearchDiffCallback()) {

    class SearchViewHolder(val binding: ItemSearchSseukssakBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(entity: SearchEntity) {
            binding.entity = entity
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding =
            ItemSearchSseukssakBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val getItem = getItem(position)
        holder.bind(getItem)
        holder.binding.deleteSearchImg.setOnClickListener {
            deleteItem.onDeleteClick(getItem)
        }
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
