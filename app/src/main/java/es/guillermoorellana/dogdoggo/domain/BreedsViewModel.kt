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

    private val _breeds = MutableLiveData(BreedsViewState())

    val breeds: LiveData<BreedsViewState>
        get() = _breeds

    init {
        viewModelScope.launch {
            val breeds = repository.allBreeds()
            _breeds.value = BreedsViewState(
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

data class BreedsViewState(val breeds: List<DisplayBreed> = emptyList())

data class DisplayBreed(val text: String, val breed: DogBreed)
