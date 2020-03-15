package com.zotiko.spacelaunchnow.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.InvocationTargetException

abstract class RecyclerAdapter<T, VH : RecyclerView.ViewHolder>
/**
 * @param modelLayout This is the layout used to represent a single item in the list. You
 * *                        will be responsible for populating an instance of the corresponding
 * *                        view with the data from an instance of modelClass.
 * *
 * @param viewHolderClass The class that hold references to all sub-views in an instance
 * *                        modelLayout.
 */(
    private var snapshots: List<T>,
    @LayoutRes private val modelLayout: Int,
    private val viewHolderClass: Class<VH>
) :
    RecyclerView.Adapter<VH>() {

    fun getItem(position: Int): T {

        return snapshots[position]
    }

    fun replaceData(snapshots: List<T>) {
        this.snapshots = snapshots
        notifyDataSetChanged()
    }

    /**
     * To handle smoother animation, instead of passing the whole data, send the difference
     */
    fun replaceData(diffResult: DiffUtil.DiffResult, snapshots: List<T>) {
        this.snapshots = snapshots
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return snapshots.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        try {
            val constructor = viewHolderClass.getConstructor(View::class.java)
            return constructor.newInstance(view)
        } catch (e: InvocationTargetException) {
            throw RuntimeException(e)
        } catch (e: InstantiationException) {
            throw RuntimeException(e)
        } catch (e: IllegalAccessException) {
            throw RuntimeException(e)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return modelLayout
    }

    override fun onBindViewHolder(viewHolder: VH, position: Int) {
        val model = getItem(position)
        populateViewHolder(viewHolder, model, position)
    }

    /**
     * Each time the data at the given Firebase location changes,
     * this method will be called for each item that needs to be displayed.
     * The first two arguments correspond to the mLayout and mModelClass given to the constructor of
     * this class. The third argument is the item's position in the list.
     *
     *
     * Your implementation should populate the view using the data contained in the model.

     * @param viewHolder The view to populate
     * *
     * @param model The object containing the data used to populate the view
     * *
     * @param position The position in the list of the view being populated
     */
    protected abstract fun populateViewHolder(viewHolder: VH, model: T, position: Int)

    companion object {
        private const val TAG = "RecyclerAdapter"
    }
}