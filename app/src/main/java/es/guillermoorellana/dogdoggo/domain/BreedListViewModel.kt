package es.guillermoorellana.dogdoggo.domain

import androidx.lifecycle.viewModelScope
import es.guillermoorellana.dogdoggo.NotDagger
import es.guillermoorellana.dogdoggo.data.DogRepository
import es.guillermoorellana.dogdoggo.infra.StateViewModel
import kotlinx.coroutines.launch

class BreedListViewModel(
    private val repository: DogRepository = NotDagger.repository()
) : StateViewModel<BreedListViewState>(BreedListViewState.Loading) {

    init {
        fetchBreeds()
    }

    fun fetchBreeds() {
        setState { BreedListViewState.Loading }
        viewModelScope.launch {
            val result = runCatching { repository.allBreeds() }
            setState {
                result.fold(
                    onSuccess = { breeds ->
                        BreedListViewState.Loaded(
                            breeds = breeds.map { breed ->
                                DisplayBreed(
                                    text = breed.asBreedDisplayText(),
                                    breed = breed.first,
                                    subBreed = breed.second
                                )
                            }
                        )
                    },
                    onFailure = {
                        BreedListViewState.Error
                    }
                )
            }
        }
    }
}

fun Pair<String, String?>.asBreedDisplayText() = "${first.capitalize()} ${second?.capitalize() ?: ""}".trim()

sealed class BreedListViewState {
    object Loading : BreedListViewState()
    object Error : BreedListViewState()
    data class Loaded(val breeds: List<DisplayBreed> = emptyList()) : BreedListViewState()
}

data class DisplayBreed(val text: String, val breed: String, val subBreed: String?)
