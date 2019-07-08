package adv.vadym.com.santex.mvp

interface IPresenter<in V : Any> {
    fun bindView(view: V)
    fun unbindView(view: V)
}