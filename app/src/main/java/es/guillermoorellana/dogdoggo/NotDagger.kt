package es.guillermoorellana.dogdoggo

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import es.guillermoorellana.dogdoggo.data.DogApi
import es.guillermoorellana.dogdoggo.data.DogRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NotDagger {

    fun moshi() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun okhttp() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
        .build()

    fun retrofit(
        moshi: Moshi = moshi(),
        okHttpClient: OkHttpClient = okhttp()
    ) = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://dog.ceo/api/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    fun api(retrofit: Retrofit = retrofit()) = retrofit.create(DogApi::class.java)

    fun repository(api: DogApi = api()) = DogRepository(api = api)
}
