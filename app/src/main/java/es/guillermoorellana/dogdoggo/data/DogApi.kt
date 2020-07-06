package es.guillermoorellana.dogdoggo.data

import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {
    @GET("breeds/list/all")
    suspend fun listAll(): ApiDogBreeds

    @GET("breed/{breed}/images")
    suspend fun listBreedImages(@Path("breed") breed: String): ApiDogPictures

    @GET("breed/{breed}/{subBreed}/images")
    suspend fun listSubBreedImages(
        @Path("breed") breed: String,
        @Path("subBreed") subBreed: String
    ): ApiDogPictures
}

data class ApiDogBreeds(
    val message: Map<String, List<String>>
)

data class ApiDogPictures(
    val message: List<String>
)
