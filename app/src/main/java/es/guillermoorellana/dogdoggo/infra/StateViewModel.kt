package es.guillermoorellana.dogdoggo.infra

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class StateViewModel<ViewState>(initialState: ViewState) : ViewModel() {

    private val _state: MutableLiveData<ViewState> = MutableLiveData(initialState)

    val state: LiveData<ViewState>
        get() = _state

    protected fun setState(block: ViewState.() -> ViewState) {
        val current = state.value!!
        val new = block(current)
        _state.value = new
    }
}
