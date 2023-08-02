package com.epifi.assignment.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epifi.assignment.model.Element
import com.epifi.assignment.repository.OmdbRepository
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