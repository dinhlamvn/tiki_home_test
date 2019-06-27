package android.leo.hometest.network

import com.google.gson.JsonArray
import io.reactivex.Observable
import retrofit2.http.GET

interface APIServices {

    @GET("tikivn/android-home-test/v2/keywords.json")
    fun getKeywordList() : Observable<JsonArray>
}