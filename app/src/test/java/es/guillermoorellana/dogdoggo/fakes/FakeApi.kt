package es.guillermoorellana.dogdoggo.fakes

import es.guillermoorellana.dogdoggo.data.ApiDogBreeds
import es.guillermoorellana.dogdoggo.data.ApiDogPictures
import es.guillermoorellana.dogdoggo.data.DogApi

class FakeApi(
    private val listAllFixture: ApiDogBreeds = ApiDogBreeds(emptyMap()),
    private val listBreedFixture: ApiDogPictures = ApiDogPictures(emptyList()),
    private val listSubBreedFixture: ApiDogPictures = ApiDogPictures(emptyList())
) : DogApi {
    override suspend fun listAll(): ApiDogBreeds = listAllFixture
    override suspend fun listBreedImages(breed: String): ApiDogPictures = listBreedFixture
    override suspend fun listSubBreedImages(breed: String, subBreed: String): ApiDogPictures = listSubBreedFixture
}
