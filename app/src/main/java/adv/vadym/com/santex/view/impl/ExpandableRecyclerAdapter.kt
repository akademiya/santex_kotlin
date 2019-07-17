package adv.vadym.com.santex.view.impl

import android.support.v7.widget.RecyclerView
import android.util.SparseIntArray
import android.view.View
import android.widget.ImageView
import java.util.*

abstract class ExpandableRecyclerAdapter<T : ExpandableRecyclerAdapter.ListItem> :
    RecyclerView.Adapter<ExpandableRecyclerAdapter<T>.ViewHolder>() {

    open class ListItem(var itemType: Int)
    open inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private var allItems: MutableList<T> = ArrayList()
    protected var visibleItems: MutableList<T>? = ArrayList()
    private var indexList: MutableList<Int> = ArrayList()
    private var expandMap = SparseIntArray()
    private var mode: Int = 0

    companion object {
        val TYPE_HEADER = 1000
        val MODE_NORMAL = 0
        val MODE_ACCORDION = 1
    }

    override fun getItemCount(): Int = if (visibleItems == null) 0 else visibleItems!!.size

    override fun getItemViewType(position: Int): Int = visibleItems?.get(position)!!.itemType

    open inner class HeaderViewHolder(view: View, var arrow: ImageView) : ViewHolder(view) {

        init {
            view.setOnClickListener { handleClick() }
        }

        private fun handleClick() {
            if (toggleExpandedItems(layoutPosition, false)) {
                openArrow(arrow)
            } else {
                closeArrow(arrow)
            }
        }

        open fun bind(position: Int) {
            arrow.rotation = (if (isExpanded(position)) 90 else 0).toFloat()
        }
    }

    fun toggleExpandedItems(position: Int, notify: Boolean): Boolean {
        return if (isExpanded(position)) {
            collapseItems(position, notify)
            false
        } else {
            expandItems(position, notify)
            if (mode == MODE_ACCORDION) {
                collapseAllExcept(position)
            }
            true
        }
    }

    private fun expandItems(position: Int, notify: Boolean) {
        var count = 0
        val index = indexList[position]
        var insert = position

        var i = index + 1
        while (i < allItems.size && allItems[i].itemType != TYPE_HEADER) {
            insert++
            count++
            visibleItems?.add(insert, allItems[i])
            indexList.add(insert, i)
            i++
        }

        notifyItemRangeInserted(position + 1, count)

        val allItemsPosition = indexList[position]
        expandMap.put(allItemsPosition, 1)

        if (notify) {
            notifyItemChanged(position)
        }
    }

    private fun collapseItems(position: Int, notify: Boolean) {
        var count = 0
        val index = indexList[position]

        var i = index + 1
        while (i < allItems.size && allItems[i].itemType != TYPE_HEADER) {
            count++
            visibleItems?.removeAt(position + 1)
            indexList.removeAt(position + 1)
            i++
        }

        notifyItemRangeRemoved(position + 1, count)

        val allItemsPosition = indexList[position]
        expandMap.delete(allItemsPosition)

        if (notify) {
            notifyItemChanged(position)
        }
    }

    protected fun isExpanded(position: Int): Boolean {
        val allItemsPosition = indexList[position]
        return expandMap.get(allItemsPosition, -1) >= 0
    }

    private fun collapseAllExcept(position: Int) {
        for (i in visibleItems?.indices!!.reversed()) {
            if (i != position && getItemViewType(i) == TYPE_HEADER) {
                if (isExpanded(i)) {
                    collapseItems(i, true)
                }
            }
        }
    }

    private fun openArrow(view: View) {
        view.animate().rotation(180F).start()
    }

    private fun closeArrow(view: View) {
        view.animate().rotation(0F).start()
    }

    fun setItems(items: List<T>) {
        allItems = items as MutableList<T>
        val visibleItems = ArrayList<T>()
        expandMap.clear()
        indexList.clear()

        for (i in items.indices) {
            if (items[i].itemType == TYPE_HEADER) {
                indexList.add(i)
                visibleItems.add(items[i])
            }
        }

        this.visibleItems = visibleItems
        notifyDataSetChanged()
    }

    protected fun notifyItemInserted(allItemsPosition: Int, visiblePosition: Int) {
        incrementIndexList(allItemsPosition, visiblePosition, 1)
        incrementExpandMapAfter(allItemsPosition, 1)

        if (visiblePosition >= 0) {
            notifyItemInserted(visiblePosition)
        }
    }

    protected fun removeItemAt(visiblePosition: Int) {
        val allItemsPosition = indexList[visiblePosition]

        allItems.removeAt(allItemsPosition)
        visibleItems?.removeAt(visiblePosition)

        incrementIndexList(allItemsPosition, visiblePosition, -1)
        incrementExpandMapAfter(allItemsPosition, -1)

        notifyItemRemoved(visiblePosition)
    }

    private fun incrementExpandMapAfter(position: Int, direction: Int) {
        val newExpandMap = SparseIntArray()

        for (i in 0 until expandMap.size()) {
            val index = expandMap.keyAt(i)
            newExpandMap.put(if (index < position) index else index + direction, 1)
        }

        expandMap = newExpandMap
    }

    private fun incrementIndexList(allItemsPosition: Int, visiblePosition: Int, direction: Int) {
        val newIndexList = ArrayList<Int>()

        for (i in indexList.indices) {
            if (i == visiblePosition) {
                if (direction > 0) {
                    newIndexList.add(allItemsPosition)
                }
            }

            val `val` = indexList[i]
            newIndexList.add(if (`val` < allItemsPosition) `val` else `val` + direction)
        }

        indexList = newIndexList
    }

    fun getMode(): Int {
        return mode
    }

    fun setMode(mode: Int) {
        this.mode = mode
    }
}