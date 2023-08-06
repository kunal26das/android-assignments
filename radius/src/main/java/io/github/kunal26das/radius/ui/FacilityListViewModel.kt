package io.github.kunal26das.radius.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.kunal26das.radius.domain.entity.Facility
import io.github.kunal26das.radius.domain.entity.FacilityOption
import io.github.kunal26das.radius.domain.usecase.ExclusionMapUseCase
import io.github.kunal26das.radius.domain.usecase.FacilityListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FacilityListViewModel @Inject constructor(
    private val facilityListUseCase: FacilityListUseCase,
    private val exclusionMapUseCase: ExclusionMapUseCase,
) : ViewModel() {

    private val _facilities = MutableStateFlow(emptyList<Facility>())
    val facilities: StateFlow<List<Facility>> get() = _facilities

    private val _exclusions = MutableStateFlow(emptyMap<FacilityOption, List<FacilityOption>>())
    val exclusions: StateFlow<Map<FacilityOption, List<FacilityOption>>> get() = _exclusions

    fun getFacilities() {
        viewModelScope.launch(Dispatchers.IO) {
            _facilities.value = facilityListUseCase.getFacilities()
        }
    }

    fun getExclusions() {
        viewModelScope.launch(Dispatchers.IO) {
            _exclusions.value = exclusionMapUseCase.getExclusions()
        }
    }

    suspend fun findOption(
        facilityId: Int,
        optionId: Int,
    ) = withContext(Dispatchers.IO) {
        facilities.value.find {
            it.id == facilityId
        }?.options?.find {
            it.id == optionId
        }
    }
}