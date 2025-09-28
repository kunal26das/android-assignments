package io.github.kunal26das.geektrust.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.kunal26das.geektrust.R
import io.github.kunal26das.geektrust.domain.entity.Planet
import io.github.kunal26das.geektrust.domain.entity.Vehicle

@Composable
fun PlanetVehiclePicker(
    modifier: Modifier = Modifier,
    index: Int,
    planets: List<Planet>,
    vehicles: List<Vehicle>,
    planet: Planet? = null,
    vehicle: Vehicle? = null,
    onPlanetClickListener: (Planet) -> Unit = {},
    onVehicleClickListener: (Vehicle) -> Unit = {},
) {
    Row(
        modifier = modifier,
    ) {
        Dropdown(
            modifier = Modifier.weight(1f),
            label = stringResource(R.string.destination, index + 1),
            selection = planet,
            items = planets,
            onSelect = onPlanetClickListener,
        )
        Dropdown(
            modifier = Modifier.weight(1f),
            label = stringResource(R.string.vehicle),
            enabled = planet != null,
            selection = vehicle,
            items = vehicles.filter {
                it.maxDistance >= (planet?.distance ?: 0)
            },
            onSelect = onVehicleClickListener,
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
    }
}