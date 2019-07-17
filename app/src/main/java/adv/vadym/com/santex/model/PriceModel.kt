package adv.vadym.com.santex.model

data class PriceModel(val textServicePrice: String) {
    val moreItem = ArrayList<MorePriceModel>()

    class MorePriceModel(val nameService: String, val metrics: String, val value: String)
}