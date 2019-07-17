package adv.vadym.com.santex.view

import adv.vadym.com.santex.model.PriceModel

interface IPriceItem {
    fun childItemList(list: List<PriceModel>)
}