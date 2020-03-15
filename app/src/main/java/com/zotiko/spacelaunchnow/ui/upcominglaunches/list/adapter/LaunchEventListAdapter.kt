package com.zotiko.spacelaunchnow.ui.upcominglaunches.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.zotiko.spacelaunchnow.dto.LaunchEventDTO
import com.zotiko.spacelaunchnow.ui.upcominglaunches.list.viewholder.LaunchEventViewHolder

class LaunchEventListAdapter(private val itemClickHandler: ((LaunchEventDTO) -> Unit)) :
    ListAdapter<LaunchEventDTO, LaunchEventViewHolder>(LaunchEventDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchEventViewHolder {
        return LaunchEventViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: LaunchEventViewHolder, position: Int) {
        getItem(position).let {
            holder.bind(it, itemClickHandler)
        }
    }
}