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

    override fun init(savedInstanceState: Bundle?) {
        setContentView(R.layout.price_activity)
        presenter = PricePresenter(this, application)

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