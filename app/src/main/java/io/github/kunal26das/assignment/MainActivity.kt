package io.github.kunal26das.assignment

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.kunal26das.common.Activity
import io.github.kunal26das.cred.CredActivity
import io.github.kunal26das.epifi.home.HomeActivity
import io.github.kunal26das.kisan_network.home.KisanNetworkActivity
import io.github.kunal26das.kutumb.SplashActivity
import io.github.kunal26das.navi.repo.RepoListActivity
import io.github.kunal26das.radius.ui.FacilityListActivity

class MainActivity : Activity() {

    private val navi = registerForActivityResult(RepoListActivity.Contract()) {}
    private val epifi = registerForActivityResult(HomeActivity.Contract()) {}
    private val kutumb = registerForActivityResult(SplashActivity.Contract()) {}
    private val kisanNetwork = registerForActivityResult(KisanNetworkActivity.Contract()) {}
    private val radius = registerForActivityResult(FacilityListActivity.Contract()) {}
    private val cred = registerForActivityResult(CredActivity.Contract()) {}

    @Composable
    override fun Content() {
        LazyColumn(
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding()
                .fillMaxSize(),
            content = {
                Assignment("Cred") {
                    cred.launch(null)
                }
                Assignment("Epifi") {
                    epifi.launch(null)
                }
                Assignment("Kisan-Network") {
                    kisanNetwork.launch(null)
                }
                Assignment("Kutumb") {
                    kutumb.launch(null)
                }
                Assignment("Navi") {
                    navi.launch(null)
                }
                Assignment("Radius") {
                    radius.launch(null)
                }
            }
        )
    }

    @Suppress("FunctionName")
    private fun LazyListScope.Assignment(
        name: String,
        onClick: () -> Unit,
    ) = item {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick.invoke()
                }
        ) {
            Text(
                modifier = Modifier
                    .padding(16.dp),
                text = name
            )
        }
        HorizontalDivider()
    }
}