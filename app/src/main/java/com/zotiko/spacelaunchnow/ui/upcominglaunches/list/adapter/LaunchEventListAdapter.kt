package com.zotiko.spacelaunchnow.ui.upcominglaunches.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zotiko.spacelaunchnow.R
import com.zotiko.spacelaunchnow.dto.LaunchEventDTO
import com.zotiko.spacelaunchnow.ui.base.RecyclerAdapter
import com.zotiko.spacelaunchnow.ui.upcominglaunches.list.viewholder.LaunchEventViewHolder

class LaunchEventListAdapter(
    events: List<LaunchEventDTO> = listOf(),
    private val itemClickHandler: ((LaunchEventDTO) -> Unit)
) :
    RecyclerAdapter<LaunchEventDTO, RecyclerView.ViewHolder>(
        events,
        R.layout.item_launch_event,
        RecyclerView.ViewHolder::class.java
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_launch_event -> {
                LaunchEventViewHolder.create(parent)
            }
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun populateViewHolder(
        viewHolder: RecyclerView.ViewHolder,
        model: LaunchEventDTO,
        position: Int
    ) {
        when (viewHolder) {
            is LaunchEventViewHolder -> {
                viewHolder.bind(model, itemClickHandler)
            }
        }
    }
}
