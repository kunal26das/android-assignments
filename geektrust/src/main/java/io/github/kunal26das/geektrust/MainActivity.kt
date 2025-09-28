package io.github.kunal26das.geektrust

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dagger.hilt.android.AndroidEntryPoint
import io.github.kunal26das.geektrust.compose.PlanetVehiclePicker
import io.github.kunal26das.geektrust.domain.entity.Planet
import io.github.kunal26das.geektrust.domain.entity.Result
import io.github.kunal26das.geektrust.domain.entity.Vehicle

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setTitle(R.string.find_falcone)
        supportActionBar?.setSubtitle(R.string.select_planets_you_want_to_search_in)
        setContent {
            MaterialTheme(
                colorScheme = when {
                    isSystemInDarkTheme() -> dynamicDarkColorScheme(this)
                    else -> dynamicLightColorScheme(this)
                },
                content = {
                    Surface {
                        Content()
                    }
                },
            )
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun Content() {
        val planets by viewModel.planets.collectAsState()
        val vehicles by viewModel.vehicles.collectAsState()
        val refreshing by viewModel.refreshing.collectAsState()
        val pullRefreshState = rememberPullRefreshState(refreshing, { viewModel.refresh() })
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
        ) {
            Form(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp),
                planets = planets,
                vehicles = vehicles,
            )
            PullRefreshIndicator(
                modifier = Modifier.align(Alignment.TopCenter),
                refreshing = refreshing,
                state = pullRefreshState,
            )
        }
    }

    @Composable
    private fun Form(
        modifier: Modifier = Modifier,
        planets: List<Planet>,
        vehicles: List<Vehicle>,
    ) {
        val result by viewModel.result.collectAsState()
        val selection = remember { viewModel.selection }
        val searchLimit by viewModel.searchLimit.collectAsState()
        LazyColumn(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                if (result == Result.Unknown) {
                    items(searchLimit) { index ->
                        PlanetVehiclePicker(
                            index = index,
                            planet = selection[index]?.first,
                            vehicle = selection[index]?.second,
                            planets = planets.filter { it !in selection.values.map { it.first } },
                            vehicles = vehicles.filter { it !in selection.values.map { it.second } },
                            onPlanetClickListener = { viewModel[index] = it },
                            onVehicleClickListener = { viewModel[index] = it },
                        )
                    }
                    item {
                        Row {
                            ResetButton(modifier = Modifier.padding(8.dp))
                            FindFalconeButton(modifier = Modifier.padding(8.dp))
                        }
                    }
                }
                item {
                    ResultText(
                        modifier = Modifier.padding(8.dp),
                        result = result,
                    )
                }
                if (result != Result.Unknown) item {
                    ResetButton()
                }
            },
        )
    }

    @Composable
    private fun ResultText(
        modifier: Modifier = Modifier,
        result: Result,
    ) {
        Text(
            modifier = modifier,
            text = when (result) {
                is Result.Success -> stringResource(
                    R.string.success,
                    result.planet.name,
                    result.timeTaken,
                )

                is Result.Failure -> stringResource(R.string.failure)
                is Result.Error -> result.message
                else -> stringResource(R.string.time_taken, viewModel.timeTaken)
            },
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
        )
    }

    @Composable
    private fun FindFalconeButton(
        modifier: Modifier = Modifier,
    ) {
        Button(
            modifier = modifier,
            enabled = viewModel.isSelectionValid,
            content = { Text(text = stringResource(R.string.find_falcone)) },
            onClick = { viewModel.findFalcone() }
        )
    }

    @Composable
    private fun ResetButton(
        modifier: Modifier = Modifier,
    ) {
        val result by viewModel.result.collectAsState()
        Button(
            modifier = modifier,
            enabled = viewModel.isSelectionEmpty.not(),
            content = {
                when (result) {
                    Result.Unknown -> Text(text = stringResource(R.string.reset))
                    else -> Text(text = stringResource(R.string.start_again))
                }
            },
            onClick = { viewModel.reset() }
        )
    }
}