package adv.vadym.com.santex.view.impl

import adv.vadym.com.santex.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.item_more_price.view.*
import kotlinx.android.synthetic.main.item_price.view.*
import java.util.*

class PriceAdapter(private val context: Context) : ExpandableRecyclerAdapter<PriceAdapter.PriceListItem>() {

    companion object {
        val TYPE_CHILDREN = 1001
    }

    class PriceListItem : ListItem {
        var serviceNameTitle: String = ""
        var serviceName: String = ""
        var metrics: String = ""
        var value: String = ""

        constructor(serviceNameTitle: String) : super(TYPE_HEADER) {
            this.serviceNameTitle = serviceNameTitle
        }

        constructor(serviceName: String, metrics: String, value: String) : super(TYPE_CHILDREN) {
            this.serviceName = serviceName
            this.metrics = metrics
            this.value = value
        }
    }

    inner class HeaderViewHolder(view: View) :
        ExpandableRecyclerAdapter<PriceListItem>.HeaderViewHolder(view, view.findViewById(R.id.arrow_expand_item) as ImageView) {
        override fun bind(position: Int) {
            super.bind(position)
            itemView.tv_name_service_title.text = visibleItems!![position].serviceNameTitle
        }
    }

    inner class PersonViewHolder(view: View) : ViewHolder(view) {
        fun bind(position: Int) {
            val singleItem = visibleItems!![position]
            itemView.tv_name_service.text = singleItem.serviceName
            itemView.tv_metrics.text = singleItem.metrics
            itemView.tv_value.text = singleItem.value
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> HeaderViewHolder(LayoutInflater.from(context).inflate(R.layout.item_price, parent, false))
            TYPE_CHILDREN -> PersonViewHolder(LayoutInflater.from(context).inflate(R.layout.item_more_price, parent, false))
            else -> PersonViewHolder(LayoutInflater.from(context).inflate(R.layout.item_price, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_HEADER -> (holder as HeaderViewHolder).bind(position)
            TYPE_CHILDREN -> (holder as PersonViewHolder).bind(position)
            else -> (holder as HeaderViewHolder).bind(position)
        }
    }

    private val sampleItems: List<PriceListItem>
        get() {
            val items = ArrayList<PriceListItem>()
            val res = context.resources

            items.add(PriceListItem(res.getString(R.string.pr_service_title1)))
            items.add(PriceListItem(res.getString(R.string.pr_service_name), res.getString(R.string.pr_metrics), res.getString(R.string.pr_value)))
            items.add(PriceListItem(res.getString(R.string.pr_service_name1), res.getString(R.string.pr_metrics_shtuk), "от 400"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name2), res.getString(R.string.pr_metrics_shtuk), "от 600"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name3), res.getString(R.string.pr_metrics_shtuk), "от 600"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name4), res.getString(R.string.pr_metrics_shtuk), "от 600"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name5), res.getString(R.string.pr_metrics_shtuk), "от 600"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name6), res.getString(R.string.pr_metrics_shtuk), "от 600"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name7), res.getString(R.string.pr_metrics_shtuk), "от 600"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name8), res.getString(R.string.pr_metrics_shtuk), "от 600"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name9), res.getString(R.string.pr_metrics_shtuk), "от 600"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name10), res.getString(R.string.pr_metrics_shtuk), "от 600"))

            items.add(PriceListItem(res.getString(R.string.pr_service_title2)))
            items.add(PriceListItem(res.getString(R.string.pr_service_name), res.getString(R.string.pr_metrics), res.getString(R.string.pr_value)))
            items.add(PriceListItem(res.getString(R.string.pr_service_name30), res.getString(R.string.pr_metrics_shtuk), "300"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name31), res.getString(R.string.pr_metrics_shtuk), "от 350"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name32), res.getString(R.string.pr_metrics_shtuk), "от 250"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name33), res.getString(R.string.pr_metrics_shtuk), "300"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name34), res.getString(R.string.pr_metrics_shtuk), "400"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name35), res.getString(R.string.pr_metrics_shtuk), "500"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name36), res.getString(R.string.pr_metrics_shtuk), "100"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name37), res.getString(R.string.pr_metrics_shtuk), "150"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name38), res.getString(R.string.pr_metrics_shtuk), "100"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name39), res.getString(R.string.pr_metrics_shtuk), "100"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name40), res.getString(R.string.pr_metrics_shtuk), "200"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name41), res.getString(R.string.pr_metrics_shtuk), "от 200"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name42), res.getString(R.string.pr_metrics_shtuk), "100"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name43), res.getString(R.string.pr_metrics_shtuk), "от 100"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name44), res.getString(R.string.pr_metrics_shtuk), "150"))

            items.add(PriceListItem(res.getString(R.string.pr_service_title3)))
            items.add(PriceListItem(res.getString(R.string.pr_service_name), res.getString(R.string.pr_metrics), res.getString(R.string.pr_value)))
            items.add(PriceListItem(res.getString(R.string.pr_service_name45), res.getString(R.string.pr_metrics_tochka), "350"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name46), res.getString(R.string.pr_metrics_tochka), "350"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name47), res.getString(R.string.pr_metrics_tochka), "300"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name48), res.getString(R.string.pr_metrics_tochka), "450"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name49), res.getString(R.string.pr_metrics_tochka), "350"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name50), res.getString(R.string.pr_metrics_mp), "10"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name51), res.getString(R.string.pr_metrics_mp), "400"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name52), res.getString(R.string.pr_metrics_shtuk), "от 600"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name53), res.getString(R.string.pr_metrics_shtuk), "600"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name54), res.getString(R.string.pr_metrics_shtuk), "250"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name55), res.getString(R.string.pr_metrics_tochka), "100"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name56), res.getString(R.string.pr_metrics_tochka), "100"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name57), res.getString(R.string.pr_metrics_tochka), "100"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name58), res.getString(R.string.pr_metrics_shtuk), "250"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name59), res.getString(R.string.pr_metrics_shtuk), "200"))

            items.add(PriceListItem(res.getString(R.string.pr_service_title4)))
            items.add(PriceListItem(res.getString(R.string.pr_service_name), res.getString(R.string.pr_metrics), res.getString(R.string.pr_value)))
            items.add(PriceListItem(res.getString(R.string.pr_service_name60), res.getString(R.string.pr_metrics_tochka), "100"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name61), res.getString(R.string.pr_metrics_tochka), "100"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name62), res.getString(R.string.pr_metrics_shtuk), "600"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name63), res.getString(R.string.pr_metrics_shtuk), "800"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name64), res.getString(R.string.pr_metrics_shtuk), "1000"))

            items.add(PriceListItem(res.getString(R.string.pr_service_title5)))
            items.add(PriceListItem(res.getString(R.string.pr_service_name), res.getString(R.string.pr_metrics), res.getString(R.string.pr_value)))
            items.add(PriceListItem(res.getString(R.string.pr_service_name65), res.getString(R.string.pr_metrics_m), "10"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name66), res.getString(R.string.pr_metrics_shtuk), "400"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name67), res.getString(R.string.pr_metrics_shtuk), "250"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name68), res.getString(R.string.pr_metrics_shtuk), "450"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name69), res.getString(R.string.pr_metrics_shtuk), "600"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name70), res.getString(R.string.pr_metrics_shtuk), "от 900"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name71), res.getString(R.string.pr_metrics_shtuk), "от 1100"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name72), res.getString(R.string.pr_metrics_shtuk), "300"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name73), res.getString(R.string.pr_metrics_m2), "10"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name74), res.getString(R.string.pr_metrics_m2), "80"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name75), res.getString(R.string.pr_metrics_null), "от 5000"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name76), res.getString(R.string.pr_metrics_null), "от 4000"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name77), res.getString(R.string.pr_metrics_shtuk), "100"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name78), res.getString(R.string.pr_metrics_shtuk), "400"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name79), res.getString(R.string.pr_metrics_shtuk), "250"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name70), res.getString(R.string.pr_metrics_shtuk), "250"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name81), res.getString(R.string.pr_metrics_shtuk), "400, 750"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name82), res.getString(R.string.pr_metrics_shtuk), "300"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name83), res.getString(R.string.pr_metrics_shtuk), "70"))

            items.add(PriceListItem(res.getString(R.string.pr_service_title6)))
            items.add(PriceListItem(res.getString(R.string.pr_service_name), res.getString(R.string.pr_metrics), res.getString(R.string.pr_value)))
            items.add(PriceListItem(res.getString(R.string.pr_service_name84), res.getString(R.string.pr_metrics_m2), "20"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name85), res.getString(R.string.pr_metrics_m2), "30"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name86), res.getString(R.string.pr_metrics_m2), "35"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name87), res.getString(R.string.pr_metrics_m2), "40"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name88), res.getString(R.string.pr_metrics_m2), "20"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name89), res.getString(R.string.pr_metrics_m2), "25"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name90), res.getString(R.string.pr_metrics_m2), "30"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name91), res.getString(R.string.pr_metrics_m2), "35"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name92), res.getString(R.string.pr_metrics_m2), "20"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name93), res.getString(R.string.pr_metrics_m2), "30"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name94), res.getString(R.string.pr_metrics_m2), "35"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name95), res.getString(R.string.pr_metrics_m2), "40"))

            items.add(PriceListItem(res.getString(R.string.pr_service_title7)))
            items.add(PriceListItem(res.getString(R.string.pr_service_name), res.getString(R.string.pr_metrics), res.getString(R.string.pr_value)))
            items.add(PriceListItem(res.getString(R.string.pr_service_name96), res.getString(R.string.pr_metrics_tochka), "300"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name97), res.getString(R.string.pr_metrics_tochka), "1000"))

            items.add(PriceListItem(res.getString(R.string.pr_service_title8)))
            items.add(PriceListItem(res.getString(R.string.pr_service_name), res.getString(R.string.pr_metrics), res.getString(R.string.pr_value)))
            items.add(PriceListItem(res.getString(R.string.pr_service_name98), res.getString(R.string.pr_metrics_styk), "200"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name99), res.getString(R.string.pr_metrics_styk), "400"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name100), res.getString(R.string.pr_metrics_null), "от 100"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name101), res.getString(R.string.pr_metrics_null), "50"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name102), res.getString(R.string.pr_metrics_null), "100"))
            items.add(PriceListItem(res.getString(R.string.pr_service_name103), res.getString(R.string.pr_metrics_null), "100"))

            return items
        }

    init {
        setItems(sampleItems)
    }

}