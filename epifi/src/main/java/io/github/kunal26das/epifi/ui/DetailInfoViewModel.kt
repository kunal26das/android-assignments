package io.github.kunal26das.epifi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.kunal26das.epifi.model.Element
import io.github.kunal26das.epifi.repository.OmdbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailInfoViewModel @Inject constructor(
    private val omdbRepository: OmdbRepository
) : ViewModel() {

    private val _element = MutableLiveData<Element>()
    val element: LiveData<Element> get() = _element

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> get() = _error

    fun getElement(element: Element?) {
        _loading.value = true
        viewModelScope.launch(Dispatchers.Default) {
            try {
                _element.postValue(omdbRepository.getElement(element))
            } catch (e: Throwable) {
                _error.postValue(e)
            }
            _loading.postValue(false)
        }
    }

}