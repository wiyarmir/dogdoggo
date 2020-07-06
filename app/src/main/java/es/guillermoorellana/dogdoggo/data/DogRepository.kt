package es.guillermoorellana.dogdoggo.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DogRepository(
    private val api: DogApi
) {
    suspend fun allBreeds(): List<DogBreed> = withContext(Dispatchers.IO) {
        api.listAll().message
            .flatMap { (breed, subBreeds) ->
                subBreeds.ifEmpty { listOf(null) }
                    .map { subBreed ->
                        DogBreed(breed, subBreed)
                    }
            }
    }

    suspend fun breedPhotos(breed: String, subBreed: String?): List<DogPhoto> = withContext(Dispatchers.IO) {
        apiForSubBreed(subBreed, breed).message
    }

    private suspend fun apiForSubBreed(subBreed: String?, breed: String) = when (subBreed) {
        null -> api.listBreedImages(breed)
        else -> api.listSubBreedImages(breed, subBreed)
    }
}

typealias DogBreed = Pair<String, String?>
typealias DogPhoto = String
