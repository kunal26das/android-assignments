package io.github.kunal26das.alle.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.kunal26das.alle.domain.ScreenshotsRepository
import io.github.kunal26das.alle.presentation.compose.BottomNavigation
import io.github.kunal26das.alle.presentation.compose.InfoContent
import io.github.kunal26das.alle.presentation.compose.ShareContent
import io.github.kunal26das.alle.ui.theme.AssignmentTheme

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(
                    ScreenshotsRepository(this@MainActivity)
                ) as T
            }
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            AssignmentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .safeDrawingPadding()
                    ) {
                        NavHost(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            navController = navController,
                            startDestination = Destination.Share.route
                        ) {
                            composable(Destination.Share.route) {
                                ShareContent(viewModel = viewModel)
                            }
                            composable(Destination.Info.route) {
                                InfoContent(viewModel = viewModel)
                            }
                        }

                        BottomNavigation(
                            modifier = Modifier.fillMaxWidth(),
                            navController = navController,
                            viewModel = viewModel,
                        )
                    }
                }
            }
        }
    }

    class Contract : ActivityResultContract<Any?, Boolean>() {
        override fun createIntent(context: Context, input: Any?): Intent {
            return Intent(context, MainActivity::class.java)
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Boolean {
            return resultCode == RESULT_OK
        }
    }

}