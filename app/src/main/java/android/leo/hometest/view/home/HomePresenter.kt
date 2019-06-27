package android.leo.hometest.view.home

import android.leo.hometest.base.BasePresenter
import android.leo.hometest.model.KeywordModel
import android.leo.hometest.network.APICallback
import android.leo.hometest.network.response.home.KeywordResponse
import com.google.gson.JsonArray
import io.reactivex.disposables.Disposable

class HomePresenter : BasePresenter(), HomeContract.Presenter {

    override fun getBaseView(): HomeContract.View = mBaseView as HomeContract.View

    override fun fetchKeywordList() {

        val disposable : Disposable = mApiManager.getListKeywordFromServer(object : APICallback<JsonArray> {
            override fun onSuccess(response: JsonArray) {
                val listKeyword = mutableListOf<KeywordModel>()
                for (it in response) {
                    if (it.asString.trim().isEmpty()) continue
                    listKeyword.add(element = KeywordModel(it.asString))
                }
                val keywordResponse = KeywordResponse(listKeyword)
                getBaseView().onFetchKeywordListSuccess(keywordResponse)
            }

            override fun onFail(error: String) {
                getBaseView().onFetchKeywordListFail(error)
            }
        })
        mCompositeDisposable.add(disposable)
    }
}