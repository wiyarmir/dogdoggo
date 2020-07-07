package es.guillermoorellana.dogdoggo.domain

import androidx.lifecycle.viewModelScope
import es.guillermoorellana.dogdoggo.NotDagger
import es.guillermoorellana.dogdoggo.data.DogBreed
import es.guillermoorellana.dogdoggo.data.DogPhoto
import es.guillermoorellana.dogdoggo.data.DogRepository
import es.guillermoorellana.dogdoggo.infra.StateViewModel
import kotlinx.coroutines.launch

class BreedDetailViewModel(
    val breed: String,
    val subBreed: String?,
    private val repository: DogRepository
) : StateViewModel<BreedDetailViewState>(BreedDetailViewState.Loading) {

    constructor(breed: String, subBreed: String?) : this(
        breed = breed,
        subBreed = subBreed,
        repository = NotDagger.repository()
    )

    init {
        fetchPhotos()
    }

    fun fetchPhotos() {
        setState { BreedDetailViewState.Loading }
        viewModelScope.launch {
            val result = runCatching { repository.breedPhotos(breed, subBreed) }
            setState {
                result.fold(
                    onSuccess = { photos ->
                        BreedDetailViewState.Loaded(
                            photos = photos
                        )
                    },
                    onFailure = {
                        BreedDetailViewState.Error
                    }
                )
            }
        }
    }
}

val BreedDetailViewModel.dogBreed: DogBreed
    get() = DogBreed(breed, subBreed)

sealed class BreedDetailViewState {
    object Loading : BreedDetailViewState()
    object Error : BreedDetailViewState()
    data class Loaded(val photos: List<DogPhoto>) : BreedDetailViewState()
}
