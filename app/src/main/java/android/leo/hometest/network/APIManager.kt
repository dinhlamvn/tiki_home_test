package android.leo.hometest.network

import com.google.gson.JsonArray
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class APIManager(retrofit: Retrofit) {

    private var mApiService : APIServices = retrofit.create(APIServices::class.java)

    // Get the keyword list from server
    fun getListKeywordFromServer(callback : APICallback<JsonArray>) : Disposable {
        return mApiService.getKeywordList()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe({
                    callback.onSuccess(it)
                }, {
                    callback.onFail(it.message.orEmpty())
                })
    }
}