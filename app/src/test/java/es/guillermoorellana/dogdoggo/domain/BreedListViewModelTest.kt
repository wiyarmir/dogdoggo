package es.guillermoorellana.dogdoggo.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import es.guillermoorellana.dogdoggo.CoroutinesTestRule
import es.guillermoorellana.dogdoggo.data.DogBreed
import es.guillermoorellana.dogdoggo.data.DogRepository
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class BreedListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = CoroutinesTestRule()

    private val repository: DogRepository = mock()

    @Test
    fun `starts loading`() {
        val vm = viewModel()

        assert(vm.state.value == BreedListViewState.Loading)
    }

    @Test
    fun `state is error when repo is bad`() {
        runBlocking { whenever(repository.allBreeds()).thenThrow(RuntimeException()) }

        val vm = viewModel()

        vm.fetchBreeds()

        assert(vm.state.value == BreedListViewState.Error)
    }

    @Test
    fun `state is loaded when repo returns stuff`() {
        runBlocking { whenever(repository.allBreeds()).thenReturn(listOf(DogBreed("doggo", "dog"))) }

        val vm = viewModel()

        vm.fetchBreeds()

        assert((vm.state.value as BreedListViewState.Loaded).breeds.size == 1)
    }

    private fun viewModel() = BreedListViewModel(repository = repository)
}

