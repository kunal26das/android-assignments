package io.github.kunal26das.epifi.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.kunal26das.epifi.R
import io.github.kunal26das.epifi.model.Element

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailInfo(
    element: Element,
    loading: Boolean,
    onDismiss: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor = EpifiColors.Cream,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
        ) {
            if (loading) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                )
            }
            Text(
                text = element.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                textAlign = TextAlign.Center,
                color = EpifiColors.CreamText,
                fontFamily = Gilroy,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
            )
            if (element.runtime.isNotBlank() && element.rated.isNotBlank()) {
                CreamText("${element.runtime} | ${element.rated}")
            }
            if (element.genre.isNotBlank()) {
                CreamText(element.genre)
            }
            element.randomRating?.value?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    textAlign = TextAlign.Center,
                    color = EpifiColors.CreamText,
                    fontFamily = Inter,
                    fontSize = 30.sp,
                )
            }
            if (element.plot.isNotBlank()) {
                SectionTitle(stringResource(R.string.synopsis))
                CreamText(element.plot, center = false)
            }
            if (element.cast.isNotBlank()) {
                SectionTitle(stringResource(R.string.cast_and_crew))
                CreamText(element.cast, center = false)
            }
        }
    }
}

@Composable
private fun CreamText(text: String, center: Boolean = true) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        textAlign = if (center) TextAlign.Center else TextAlign.Start,
        color = EpifiColors.CreamText,
        fontFamily = Inter,
        fontSize = 14.sp,
    )
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(vertical = 16.dp),
        color = EpifiColors.CreamText,
        fontFamily = Gilroy,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
    )
}
