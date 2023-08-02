package com.agentdesks.android.crm.assignment.feature.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.agentdesks.android.crm.assignment.feature.domain.entity.Facility
import com.agentdesks.android.crm.assignment.feature.domain.entity.Option
import com.agentdesks.android.crm.assignment.feature.entity.Icon

@Composable
fun Option(
    modifier: Modifier = Modifier,
    option: Option,
    selected: Boolean,
    onClick: (Option) -> Unit
) {
    Surface(
        modifier = modifier.clickable {
            onClick.invoke(option)
        },
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selected,
                onClick = {
                    onClick.invoke(option)
                }
            )
            Text(
                modifier = Modifier.weight(1f),
                text = option.name,
            )
            Icon(
                modifier = Modifier
                    .padding(horizontal = 10.dp),
                painter = painterResource(id = Icon[option.icon].drawableRes),
                contentDescription = option.name
            )
        }
    }
}