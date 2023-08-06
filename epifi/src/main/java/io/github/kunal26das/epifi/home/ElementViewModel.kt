package io.github.kunal26das.epifi.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.kunal26das.epifi.model.Element
import io.github.kunal26das.epifi.repository.OmdbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ElementViewModel @Inject constructor(
    private val omdbRepository: OmdbRepository
) : ViewModel() {

    suspend fun isElementBookmarked(element: Element) = withContext(Dispatchers.IO) {
        omdbRepository.isElementBookmarked(element)
    }

    fun manageElement(element: Element) {
        viewModelScope.launch(Dispatchers.IO) {
            when (element.isBookmarked) {
                true -> omdbRepository.insertElement(element)
                false -> omdbRepository.deleteElement(element)
            }
        }
    }
}