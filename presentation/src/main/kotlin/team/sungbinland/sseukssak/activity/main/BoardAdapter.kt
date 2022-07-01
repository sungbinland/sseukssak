/*
 * SseukSsak © 2022 Team Sungbinland. all rights reserved.
 * SseukSsak license is under the MIT.
 *
 * Please see: https://github.com/sungbinland/sseukssak/blob/main/LICENSE.
 */

package team.sungbinland.sseukssak.activity.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import team.sungbinland.sseukssak.databinding.ItemBoardBinding
import team.sungbinland.sseukssak.dto.Board

class BoardAdapter(private val clickListener: BoardClickListener) :
    ListAdapter<Board, BoardAdapter.BoardItemViewHolder>(BoardDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardItemViewHolder {
        val binding = ItemBoardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BoardItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BoardItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BoardItemViewHolder(private val binding: ItemBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(board: Board) {
            binding.clicklistener = clickListener
            binding.board = board
            binding.executePendingBindings()
        }
    }
}

private class BoardDiffCallback : DiffUtil.ItemCallback<Board>() {
    override fun areItemsTheSame(oldItem: Board, newItem: Board): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Board, newItem: Board): Boolean {
        return oldItem == newItem
    }
}
