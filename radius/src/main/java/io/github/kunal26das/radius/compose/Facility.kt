package io.github.kunal26das.radius.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.kunal26das.radius.domain.entity.Facility
import io.github.kunal26das.radius.domain.entity.FacilityOption

@Composable
fun Facility(
    modifier: Modifier = Modifier,
    facility: Facility,
    selections: Map<Int, Int>,
    exclusions: Map<FacilityOption, List<FacilityOption>>,
    onAdd: ((Int, Int) -> Unit)? = null,
    onRemove: ((Int) -> Unit)? = null
) {
    Column(
        modifier = modifier,
    ) {
        Surface(modifier = Modifier.padding(bottom = 8.dp)) {
            Text(text = facility.name)
        }
        facility.options.forEach { option ->
            Option(
                option = option,
                selected = selections[facility.id] == option.id,
                onClick = {
                    val antiOptions = exclusions[option.ofFacility]
                    antiOptions?.forEach {
                        if (selections[it.facilityId] == it.optionId) {
                            onRemove?.invoke(it.facilityId)
                        }
                    }
                    onAdd?.invoke(option.facilityId, option.id)
                }
            )
        }
    }
}