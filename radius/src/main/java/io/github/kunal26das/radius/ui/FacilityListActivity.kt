package io.github.kunal26das.radius.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.viewModels
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import io.github.kunal26das.common.Activity
import io.github.kunal26das.radius.compose.Facility
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FacilityListActivity : Activity() {

    private val viewModel by viewModels<FacilityListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getFacilities()
        viewModel.getExclusions()
    }

    @Composable
    override fun Content() {
        val scrollState = rememberScrollState()
        val exclusions by viewModel.exclusions.collectAsState()
        val facilities by viewModel.facilities.collectAsState()
        val selections = remember { mutableStateMapOf<Int, Int>() }
        Column(
            Modifier
                .fillMaxSize()
                .scrollable(
                    state = scrollState,
                    orientation = Orientation.Vertical
                )
        ) {
            facilities.forEach {
                Facility(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    ),
                    facility = it,
                    selections = selections,
                    exclusions = exclusions,
                    onAdd = { facilityId, optionId ->
                        selections[facilityId] = optionId
                    },
                    onRemove = { facilityId ->
                        selections.remove(facilityId)?.let { optionId ->
                            lifecycleScope.launch {
                                viewModel.findOption(facilityId, optionId)?.let { option ->
                                    toast("Unselected ${option.name}")
                                }
                            }
                        }
                    }
                )
            }
            Divider()
        }
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    class Contract : ActivityResultContract<Any?, Boolean>() {
        override fun createIntent(context: Context, input: Any?): Intent {
            return Intent(context, FacilityListActivity::class.java)
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Boolean {
            return resultCode == RESULT_OK
        }
    }
}