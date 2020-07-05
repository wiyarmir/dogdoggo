package es.guillermoorellana.dogdoggo

class DogRepository(
    private val api: DogApi
) {
    suspend fun allBreeds(): List<DogBreed> = api.listAll().message
        .flatMap { (breed, subBreeds) ->
            subBreeds.ifEmpty { listOf(null) }
                .map { subBreed -> DogBreed(apiBreed = breed, apiSubBreed = subBreed) }
        }
}

data class DogBreed(val apiBreed: String, val apiSubBreed: String?)
