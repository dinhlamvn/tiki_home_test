package android.leo.hometest.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {

    companion object {
        private const val BASE_URL = "https://raw.githubusercontent.com/"

        private lateinit var retrofit : Retrofit

        fun getClient() : Retrofit {
            val client : OkHttpClient = OkHttpClient.Builder().build()

            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            return retrofit
        }

    }
}