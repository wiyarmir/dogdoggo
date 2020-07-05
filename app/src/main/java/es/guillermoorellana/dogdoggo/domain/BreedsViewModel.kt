package es.guillermoorellana.dogdoggo.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.guillermoorellana.dogdoggo.NotDagger
import es.guillermoorellana.dogdoggo.data.DogBreed
import es.guillermoorellana.dogdoggo.data.DogRepository
import kotlinx.coroutines.launch

class BreedsViewModel(
    private val repository: DogRepository = NotDagger.repository()
) : ViewModel() {

    private val _breeds: MutableLiveData<BreedsViewState> = MutableLiveData(BreedsViewState.Loading)

    val state: LiveData<BreedsViewState>
        get() = _breeds

    init {
        fetchBreeds()
    }

    fun fetchBreeds() {
        viewModelScope.launch {
            val breeds = repository.allBreeds()
            _breeds.value = BreedsViewState.Loaded(
                breeds = breeds.map { breed ->
                    DisplayBreed(
                        text = breed.displayText(),
                        breed = breed
                    )
                }
            )
        }
    }
}

private fun DogBreed.displayText() = "${apiBreed.capitalize()} ${apiSubBreed?.capitalize() ?: ""}".trim()

sealed class BreedsViewState {
    object Loading : BreedsViewState()
    object Error : BreedsViewState()
    data class Loaded(val breeds: List<DisplayBreed> = emptyList()) : BreedsViewState()
}


data class DisplayBreed(val text: String, val breed: DogBreed)
