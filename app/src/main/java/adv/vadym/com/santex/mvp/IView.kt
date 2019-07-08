package adv.vadym.com.santex.mvp

interface IView {
    fun showLoading()
    fun hideLoading()
    fun loadError(e: Throwable)
    fun loadError(msg: String)
}