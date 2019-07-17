package adv.vadym.com.santex.view.impl

import adv.vadym.com.santex.R
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_more_price.view.*
import kotlinx.android.synthetic.main.item_price.view.*

class PriceExpandableAdapter(var items: MutableList<Item>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        val PARENT = 0
        val CHILD = 1
        val OPEN = 0.0F
        val CLOSE = 180.0F
    }

    inner class ItemHolder(v: View) : RecyclerView.ViewHolder(v) {
        var serviceTitle = v.tv_name_service_title
        val toggleImageView = v.arrow_expand_item
        var serviceName = v.tv_name_service
        var metrics = v.tv_metrics
        var value = v.tv_value
    }

    data class Item(val type: Int = 0,
                    var serviceTitle: String = "",
                    var serviceName: String = "",
                    var metrics: String = "",
                    var value: String = "",
                    var children: List<Item>? = null)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var view: View? = null
        when(viewType) {
            PARENT -> view = inflater.inflate(R.layout.item_price, parent, false)
            CHILD -> view = inflater.inflate(R.layout.item_more_price, parent, false)
        }
        return ItemHolder(view!!)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = items[position].type

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemHolder = holder as? ItemHolder
        val item = items[position]

        itemHolder?.let { iHolder ->
            iHolder.toggleImageView?.apply {
                rotation = if (item.children == null) OPEN else CLOSE
                setOnClickListener { view ->
                    val start = items.indexOf(item) + 1
                    if (item.children == null) {
                        var count = 0
                        var nextHeader = items.indexOf(items.find {
                            (count++ >= start) && (it.type == item.type)
                        })

                        if (nextHeader == -1) {
                            nextHeader = items.size
                        }
                        item.children = items.slice(start until nextHeader)

                        val end = item.children!!.size
                        if (end > 0) {
                            items.removeAll(item.children!!)
                        }

                        view.animate()
                            .rotation(CLOSE)
                            .start()

                        notifyItemRangeRemoved(start, end)
                    } else {
                        item.children?.let { listChildren ->
                            items.addAll(start, listChildren)
                            view.animate()
                                .rotation(OPEN)
                                .start()
                            notifyItemRangeInserted(start, listChildren.size)
                            item.children = null
                        }
                    }
                }
            }

            iHolder.serviceTitle.text = item.serviceTitle
            iHolder.serviceName.text = item.serviceName
            iHolder.metrics.text = item.metrics
            iHolder.value.text = item.value
        }
    }


}