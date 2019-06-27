package android.leo.hometest.view.home

import android.leo.hometest.R
import android.leo.hometest.base.BaseActivity
import android.leo.hometest.model.KeywordModel
import android.leo.hometest.network.response.home.KeywordResponse
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity(), HomeContract.View, OnHotKeywordClickListener {

    private var mListKeyword : MutableList<KeywordModel> = mutableListOf()

    private var mPresenter: HomePresenter = HomePresenter()

    private var mAdapter : ListKeywordAdapter = ListKeywordAdapter(this, mListKeyword)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter.onCreate(this)

        showProgress(resources.getString(R.string.loading))
        mPresenter.fetchKeywordList()
    }

    override fun getLayoutResource(): Int = R.layout.activity_home

    override fun getLogTag() : String = "HomeActivity"

    override fun onInitUI() {
        with(recyclerListHotKeyWord) {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = true
            showProgress(resources.getString(R.string.loading))
            mPresenter.fetchKeywordList()
        }
    }

    override fun onFetchKeywordListSuccess(keywordResponse: KeywordResponse) {
        swipeRefreshLayout.isRefreshing = false
        hideProgress()
        mListKeyword.clear()
        mListKeyword.addAll(keywordResponse.listKeyword)
        runOnUiThread {
            mAdapter.notifyDataSetChanged()
        }
    }

    override fun onFetchKeywordListFail(error : String) {
        hideProgress()
        Log.d(getLogTag(), "Error when load keyword list from server $error")
    }

    override fun onDestroy() {
        mPresenter.onDestroy()
        super.onDestroy()
    }

    override fun onHotKeywordClick(keyword : String) {
        toast("You was clicked on \"$keyword\"")
    }
}
