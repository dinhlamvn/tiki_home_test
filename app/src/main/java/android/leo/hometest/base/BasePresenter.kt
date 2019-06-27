package android.leo.hometest.base

import android.leo.hometest.network.APIClient
import android.leo.hometest.network.APIManager
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter {

    protected var mApiManager : APIManager = APIManager(APIClient.getClient())

    protected var mBaseView : BaseView? = null

    protected lateinit var mCompositeDisposable : CompositeDisposable

    public fun onCreate(baseView: BaseView) {
        mCompositeDisposable = CompositeDisposable()
        this.mBaseView = baseView
    }

    public fun onDestroy() {
        this.mBaseView = null
        mCompositeDisposable.clear()
    }

    protected abstract fun getBaseView() : BaseView
}