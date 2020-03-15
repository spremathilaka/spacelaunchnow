package com.zotiko.spacelaunchnow.ui.upcominglaunches.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.zotiko.spacelaunchnow.dto.LaunchEventDTO

class LaunchEventDiffUtilCallback : DiffUtil.ItemCallback<LaunchEventDTO>() {
    override fun areItemsTheSame(oldItem: LaunchEventDTO, newItem: LaunchEventDTO): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LaunchEventDTO, newItem: LaunchEventDTO): Boolean {
        return oldItem.id == newItem.id
    }
}