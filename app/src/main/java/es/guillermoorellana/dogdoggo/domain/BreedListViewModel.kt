package es.guillermoorellana.dogdoggo.domain

import android.util.Log
import androidx.lifecycle.viewModelScope
import es.guillermoorellana.dogdoggo.NotDagger
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
                                    text = breed.asBreedDisplayText(),
                                    breed = breed.first,
                                    subBreed = breed.second
                                )
                            }
                        )
                    },
                    onFailure = {
                        Log.e(javaClass.name, "Failure fetching breeds: ${it.localizedMessage}")
                        BreedsViewState.Error
                    }
                )
            }
        }
    }
}

fun Pair<String, String?>.asBreedDisplayText() = "${first.capitalize()} ${second?.capitalize() ?: ""}".trim()

sealed class BreedsViewState {
    object Loading : BreedsViewState()
    object Error : BreedsViewState()
    data class Loaded(val breeds: List<DisplayBreed> = emptyList()) : BreedsViewState()
}

data class DisplayBreed(val text: String, val breed: String, val subBreed: String?)
