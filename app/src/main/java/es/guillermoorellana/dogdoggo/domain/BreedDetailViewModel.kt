package es.guillermoorellana.dogdoggo.domain

import es.guillermoorellana.dogdoggo.infra.StateViewModel

class BreedDetailViewModel(

) : StateViewModel<BreedDetailViewState>(BreedDetailViewState.Loading) {

}

sealed class BreedDetailViewState {
    object Loading : BreedDetailViewState()
}
