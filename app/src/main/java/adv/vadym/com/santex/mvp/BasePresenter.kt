package adv.vadym.com.santex.mvp

abstract class BasePresenter<V : Any>(@Volatile var view: V?) : IPresenter<V> {

    protected fun view() : V? { return view }

    override fun bindView(view: V) {
        if (this.view != null) return onUnbindView()
        this.view = view
        return onBindView()
    }

    override fun unbindView(view: V) {
        if (this.view === view) {
            onUnbindView()
            this.view = null
        }
    }

    protected abstract fun onBindView()
    protected abstract fun onUnbindView()
}