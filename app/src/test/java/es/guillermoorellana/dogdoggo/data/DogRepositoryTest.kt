package es.guillermoorellana.dogdoggo.data

import es.guillermoorellana.dogdoggo.fakes.FakeApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

class DogRepositoryTest {
    @Test
    fun `no breeds`() {
        val repo = DogRepository(FakeApi())

        val response = runBlocking { repo.allBreeds() }

        assert(response.isEmpty())
    }

    @Test
    fun `no subbreed`() {
        val repo = DogRepository(
            FakeApi(
                listAllFixture = ApiDogBreeds(
                    message = mapOf("doggo" to emptyList())
                )
            )
        )

        val response = runBlocking { repo.allBreeds() }

        assert(response.contains(DogBreed("doggo", null)))
    }

    @Test
    fun `subbreeds`() {
        val repo = DogRepository(
            FakeApi(
                listAllFixture = ApiDogBreeds(
                    message = mapOf(
                        "doggo" to listOf("puppy", "good")
                    )
                )
            )
        )

        val response = runBlocking { repo.allBreeds() }

        assert(response.contains(DogBreed("doggo", "puppy")))
        assert(response.contains(DogBreed("doggo", "good")))
    }

    @Test
    fun `no breed photos`() {
        val repo = DogRepository(FakeApi())

        val response = runBlocking { repo.breedPhotos("breed", null) }

        assert(response.isEmpty())
    }

    @Test
    fun `breed photos endpoint`() {
        val repo = DogRepository(
            FakeApi(
                listBreedFixture = ApiDogPictures(message = listOf("picture"))
            )
        )

        val response = runBlocking { repo.breedPhotos("breed", null) }

        assert(response.contains("picture"))
    }

    @Test
    fun `no subbreed photos`() {
        val repo = DogRepository(FakeApi())

        val response = runBlocking { repo.breedPhotos("breed", "subbreed") }

        assert(response.isEmpty())
    }

    @Test
    fun `subbreed photos endpoint`() {
        val repo = DogRepository(
            FakeApi(
                listSubBreedFixture = ApiDogPictures(message = listOf("picture"))
            )
        )

        val response = runBlocking { repo.breedPhotos("breed", "subbreed") }

        assert(response.contains("picture"))
    }
}
