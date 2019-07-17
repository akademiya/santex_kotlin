package adv.vadym.com.santex.view.impl

import adv.vadym.com.santex.R
import adv.vadym.com.santex.mvp.BaseActivity
import adv.vadym.com.santex.presenter.PricePresenter
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.price_activity.*

class PriceActivity : BaseActivity() {

    private lateinit var presenter: PricePresenter
    private lateinit var adapter: PriceAdapter
//    private val itemList = mutableListOf<PriceExpandableAdapter.Item>()

    override fun init(savedInstanceState: Bundle?) {
        setContentView(R.layout.price_activity)
        presenter = PricePresenter(this, application)

//        itemList.add(PriceExpandableAdapter.Item(PriceExpandableAdapter.PARENT, "1", "&&&", "&", "&"))
//        itemList.add(PriceExpandableAdapter.Item(PriceExpandableAdapter.CHILD, "2", "rrrr", "q", "10"))
//        itemList.add(PriceExpandableAdapter.Item(PriceExpandableAdapter.CHILD, "3", "tttt", "q", "47"))
//        itemList.add(PriceExpandableAdapter.Item(PriceExpandableAdapter.CHILD, "4", "hhhh", "q", "58"))
//        itemList.add(PriceExpandableAdapter.Item(PriceExpandableAdapter.CHILD, "5", "oooo", "q", "52"))

//        val item = PriceExpandableAdapter.Item(PriceExpandableAdapter.PARENT, "Android", "777", "q", "85")
//        item.children = listOf(
//            PriceExpandableAdapter.Item(PriceExpandableAdapter.CHILD, "Jelly Bean", "rrrr", "q", "85"),
//            PriceExpandableAdapter.Item(PriceExpandableAdapter.CHILD, "KitKat", "iiiii", "q", "74"),
//            PriceExpandableAdapter.Item(PriceExpandableAdapter.CHILD, "Lollipop", "kkkk", "q", "63")
//        )
//        itemList.add(item)


        adapter = PriceAdapter(this)
        adapter.setMode(ExpandableRecyclerAdapter.MODE_NORMAL)

        rv_price.setHasFixedSize(false)
        rv_price.layoutManager = LinearLayoutManager(this)
        rv_price.adapter = adapter
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        presenter.bindView(this)
    }

    override fun onDetachedFromWindow() {
        presenter.unbindView(this)
        super.onDetachedFromWindow()
    }
}