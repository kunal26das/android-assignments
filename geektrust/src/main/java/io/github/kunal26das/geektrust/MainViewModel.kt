package io.github.kunal26das.geektrust

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.kunal26das.geektrust.domain.entity.Planet
import io.github.kunal26das.geektrust.domain.entity.Result
import io.github.kunal26das.geektrust.domain.entity.Vehicle
import io.github.kunal26das.geektrust.domain.repo.PlanetRepository
import io.github.kunal26das.geektrust.domain.repo.VehicleRepository
import io.github.kunal26das.geektrust.domain.usecase.FindFalconeUseCase
import io.github.kunal26das.geektrust.domain.utils.calculateTimeTaken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val planetRepository: PlanetRepository,
    private val vehicleRepository: VehicleRepository,
    private val findFalconeUseCase: FindFalconeUseCase,
) : ViewModel() {

    private val _refreshing = MutableStateFlow(false)
    val refreshing = _refreshing.asStateFlow()

    private val _planets = MutableStateFlow(emptyList<Planet>())
    val planets = _planets.asStateFlow()

    private val _vehicles = MutableStateFlow(emptyList<Vehicle>())
    val vehicles = _vehicles.asStateFlow()

    private val _searchLimit = MutableStateFlow(0)
    val searchLimit = _searchLimit.asStateFlow()

    private val _result = MutableStateFlow<Result>(Result.Unknown)
    val result = _result.asStateFlow()

    val selection = mutableStateMapOf<Int, Pair<Planet, Vehicle?>>()

    val isSelectionEmpty get() = selection.isEmpty()
    val timeTaken get() = calculateTimeTaken(selection.values)
    val isSelectionValid get() = selection.values.mapNotNull { it.second }.size == searchLimit.value

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _refreshing.value = true
            val planets = async { getPlanets() }
            val vehicles = async { getVehicles() }
            planets.await()
            vehicles.await()
            reset()
            _refreshing.value = false
        }
    }

    private suspend fun getPlanets() = withContext(Dispatchers.IO) {
        val planets = planetRepository.getPlanets()
        _searchLimit.value = planets.searchLimit
        _planets.value = planets.list
    }

    private suspend fun getVehicles() = withContext(Dispatchers.IO) {
        val vehicles = vehicleRepository.getVehicles()
        _vehicles.value = vehicles
    }

    operator fun set(index: Int, planet: Planet) {
        selection[index] = planet to null
    }

    operator fun set(index: Int, vehicle: Vehicle) {
        selection[index]?.first?.let { planet ->
            selection[index] = planet to vehicle
        }
    }

    fun findFalcone() {
        viewModelScope.launch(Dispatchers.IO) {
            val planets = selection.values.map { it.first }
            val vehicles = selection.values.mapNotNull { it.second }
            val result = findFalconeUseCase.findFalcone(planets, vehicles)
            _result.value = result
        }
    }

    fun reset() {
        selection.clear()
        _result.value = Result.Unknown
    }

    private fun <T> Flow<T>.stateIn(
        initialValue: T,
        started: SharingStarted = WhileSubscribed()
    ): StateFlow<T> {
        return stateIn(viewModelScope, started, initialValue)
    }
}