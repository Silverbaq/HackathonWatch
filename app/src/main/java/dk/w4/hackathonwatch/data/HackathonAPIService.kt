package dk.w4.hackathonwatch.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dk.w4.hackathonwatch.data.db.entity.Hackathon
import dk.w4.hackathonwatch.data.network.ConnectivityIntercepter
import dk.w4.hackathonwatch.data.network.ConnectivityIntercepterImpl
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface HackathonAPIService {
    // Get all
    // http://www.hackathonwatch.com/api//hackathons/coming.json

    // Get single by id
    // http://www.hackathonwatch.com/api//hackathons/{id}.json

    @GET("coming.json")
    fun getAll(): Deferred<List<Hackathon>>

    @GET("{id}.json")
    fun getHackathonById(
        @Path("id") id: Int
    ): Deferred<Hackathon>

    companion object {
        operator fun invoke(
            connectivityIntercepter: ConnectivityIntercepter
        ): HackathonAPIService {
            val requestIntercepter = Interceptor{ chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestIntercepter)
                .addInterceptor(connectivityIntercepter)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://www.hackathonwatch.com/api/hackathons/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HackathonAPIService::class.java)
        }
    }
}