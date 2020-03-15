package com.zotiko.spacelaunchnow.ui.upcominglaunches.list.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.zotiko.spacelaunchnow.R
import com.zotiko.spacelaunchnow.databinding.ItemLaunchEventBinding
import com.zotiko.spacelaunchnow.dto.LaunchEventDTO

class LaunchEventViewHolder(private val viewBinding: ItemLaunchEventBinding) :
    RecyclerView.ViewHolder(viewBinding.rootView) {

    fun bind(event: LaunchEventDTO, callback: ((LaunchEventDTO) -> Unit)) {
        viewBinding.eventData = event
        viewBinding.executePendingBindings()

        viewBinding.root.setOnClickListener {
            viewBinding.eventData?.let {
                callback.invoke(it)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): LaunchEventViewHolder {
            return LaunchEventViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_launch_event, parent, false
                )
            )
        }
    }
}
