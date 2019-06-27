package android.leo.hometest.network

interface APICallback<in T> {

    fun onSuccess(response: T)

    fun onFail(error : String)
}