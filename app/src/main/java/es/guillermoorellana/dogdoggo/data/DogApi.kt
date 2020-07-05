package es.guillermoorellana.dogdoggo.data

import retrofit2.http.GET

interface DogApi {
    @GET("breeds/list/all")
    suspend fun listAll(): ApiDogBreeds
}

data class ApiDogBreeds(
    val message: Map<String, List<String>>
)
