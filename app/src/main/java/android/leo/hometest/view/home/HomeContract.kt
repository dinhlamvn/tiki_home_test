package android.leo.hometest.view.home

import android.leo.hometest.base.BaseView
import android.leo.hometest.network.response.home.KeywordResponse

interface HomeContract {

    interface View : BaseView {
        // Keyword fetch response status
        fun onFetchKeywordListSuccess(keywordResponse: KeywordResponse)
        fun onFetchKeywordListFail(error : String)
    }

    interface Presenter {
        fun fetchKeywordList()
    }
}