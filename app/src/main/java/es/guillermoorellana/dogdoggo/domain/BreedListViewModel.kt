package es.guillermoorellana.dogdoggo.domain

import androidx.lifecycle.viewModelScope
import es.guillermoorellana.dogdoggo.NotDagger
import es.guillermoorellana.dogdoggo.data.DogBreed
import es.guillermoorellana.dogdoggo.data.DogRepository
import es.guillermoorellana.dogdoggo.infra.StateViewModel
import kotlinx.coroutines.launch

class BreedListViewModel(
    private val repository: DogRepository = NotDagger.repository()
) : StateViewModel<BreedsViewState>(BreedsViewState.Loading) {

    init {
        fetchBreeds()
    }

    fun fetchBreeds() {
        setState { BreedsViewState.Loading }
        viewModelScope.launch {
            val result = runCatching { repository.allBreeds() }
            setState {
                result.fold(
                    onSuccess = { breeds ->
                        BreedsViewState.Loaded(
                            breeds = breeds.map { breed ->
                                DisplayBreed(
                                    text = breed.displayText(),
                                    breed = breed
                                )
                            }
                        )
                    },
                    onFailure = {
                        BreedsViewState.Error
                    }
                )
            }
        }
    }
}

fun DogBreed.displayText() = "${apiBreed.capitalize()} ${apiSubBreed?.capitalize() ?: ""}".trim()

sealed class BreedsViewState {
    object Loading : BreedsViewState()
    object Error : BreedsViewState()
    data class Loaded(val breeds: List<DisplayBreed> = emptyList()) : BreedsViewState()
}

data class DisplayBreed(val text: String, val breed: DogBreed)
