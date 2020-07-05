package es.guillermoorellana.dogdoggo

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NotDagger {

    fun moshi() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun retrofit() = Retrofit.Builder()
        .baseUrl("https://dog.ceo/api/")
        .addConverterFactory(MoshiConverterFactory.create(moshi()))
        .build()

    fun api() = retrofit().create(DogApi::class.java)

    fun repository() = DogRepository(api = api())

}
