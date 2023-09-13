package io.github.kunal26das.cred

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.kunal26das.cred.compose.CircularIconButton
import io.github.kunal26das.cred.sheet.FirstSheet
import io.github.kunal26das.cred.sheet.SecondSheet
import io.github.kunal26das.cred.sheet.ThirdSheet
import io.github.kunal26das.cred.theme.AssignmentTheme
import io.github.kunal26das.stack.Stack

class CredActivity : AppCompatActivity() {

    private var onBackPressedCallback: OnBackPressedCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val backHandlingEnabled by remember { mutableStateOf(true) }
            BackHandler(backHandlingEnabled) {
                onBackPressedCallback?.handleOnBackPressed()
            }
            AssignmentTheme {
                Content()
            }
        }
    }

    @Composable
    private fun Content() {
        val coroutineScope = rememberCoroutineScope()
        Surface {
            TopBar()
            val stack = Stack(
                FirstSheet(),
                SecondSheet(),
                ThirdSheet {
                    finish()
                }
            )
            val dispatcher = stack.getOnBackPressedCallback(coroutineScope) {
                finish()
            }
            onBackPressedDispatcher.addCallback(dispatcher)
            onBackPressedCallback = dispatcher
        }
    }

    @Composable
    private fun TopBar() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
        ) {
            CircularIconButton(
                imageVector = Icons.Filled.Close,
            ) {
                onBackPressedCallback?.handleOnBackPressed()
            }
        }
    }

    class Contract : ActivityResultContract<Any?, Boolean>() {
        override fun createIntent(context: Context, input: Any?): Intent {
            return Intent(context, CredActivity::class.java)
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Boolean {
            return resultCode == RESULT_OK
        }
    }
}