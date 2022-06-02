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

package team.sungbinland.sseukssak.fragment.sseukssaklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import team.sungbinland.sseukssak.databinding.ItemSseukssakBinding
import team.sungbinland.sseukssak.dto.Sseukssak

class SseukssakAdapter(private val viewModel: SseukssakViewModel) :
    ListAdapter<Sseukssak, SseukssakAdapter.SseukssakViewHolder>(SseukssakDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SseukssakViewHolder {
        val binding =
            ItemSseukssakBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SseukssakViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SseukssakViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SseukssakViewHolder(private val binding: ItemSseukssakBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(sseukssak: Sseukssak) {
            binding.vm = viewModel
            binding.sseukssak = sseukssak
            binding.executePendingBindings()
        }
    }
}

class SseukssakDiffCallback : DiffUtil.ItemCallback<Sseukssak>() {
    override fun areItemsTheSame(oldItem: Sseukssak, newItem: Sseukssak): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Sseukssak, newItem: Sseukssak): Boolean {
        return oldItem == newItem
    }
}