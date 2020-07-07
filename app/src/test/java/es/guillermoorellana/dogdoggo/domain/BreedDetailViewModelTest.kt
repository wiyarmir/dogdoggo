package es.guillermoorellana.dogdoggo.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import es.guillermoorellana.dogdoggo.CoroutinesTestRule
import es.guillermoorellana.dogdoggo.data.DogRepository
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class BreedDetailViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = CoroutinesTestRule()

    private val repository: DogRepository = mock()

    @Test
    fun `starts loading`() {
        val vm = viewModel()

        assert(vm.state.value == BreedDetailViewState.Loading)
    }

    @Test
    fun `state is error when repo is bad`() {
        runBlocking { whenever(repository.breedPhotos(any(), anyOrNull())).thenThrow(RuntimeException()) }

        val vm = viewModel()

        vm.fetchPhotos()

        assert(vm.state.value == BreedDetailViewState.Error)
    }

    @Test
    fun `state is loaded when repo returns stuff for breed`() {
        runBlocking { whenever(repository.breedPhotos(any(), anyOrNull())).thenReturn(listOf("photo")) }

        val vm = viewModel()

        vm.fetchPhotos()

        assert((vm.state.value as BreedDetailViewState.Loaded).photos.size == 1)
    }

    @Test
    fun `state is loaded when repo returns stuff for subbreed`() {
        runBlocking { whenever(repository.breedPhotos(any(), anyOrNull())).thenReturn(listOf("photo")) }

        val vm = viewModel(subBreed = "subbreed")

        vm.fetchPhotos()

        assert((vm.state.value as BreedDetailViewState.Loaded).photos.size == 1)
    }

    private fun viewModel(subBreed: String? = null) = BreedDetailViewModel(
        breed = "breed",
        subBreed = subBreed,
        repository = repository
    )
}
