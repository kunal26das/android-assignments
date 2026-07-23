package io.github.kunal26das.bmi

import android.os.Bundle
import android.widget.Toast
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.kunal26das.common.Activity
import kotlin.math.roundToInt

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // The UI is always light, so force dark system bar icons.
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT,
            ),
            navigationBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT,
            ),
        )
        // Redraw without the base class' safe drawing padding,
        // so backgrounds extend edge-to-edge behind the system bars.
        setContent {
            Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Content()
                }
            }
        }
    }

    @Composable
    override fun Content() {
        var weightValue by rememberSaveable { mutableStateOf("60") }
        var heightValue by rememberSaveable { mutableStateOf("1.7") }
        var weightUnit by rememberSaveable { mutableStateOf(WeightUnit.Kilogram) }
        var heightUnit by rememberSaveable { mutableStateOf(HeightUnit.Meter) }
        var focusWeight by rememberSaveable { mutableStateOf(true) }
        var showResult by rememberSaveable { mutableStateOf(false) }
        var bmi by rememberSaveable { mutableStateOf(0f) }

        fun focusedValue() = if (focusWeight) weightValue else heightValue

        fun setFocusedValue(value: String) {
            if (focusWeight) weightValue = value else heightValue = value
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorPrimary),
        ) {
            TopBar()
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .then(if (showResult) Modifier.navigationBarsPadding() else Modifier)
                    .verticalScroll(rememberScrollState()),
            ) {
                Parameter(
                    label = stringResource(R.string.weight),
                    value = weightValue,
                    unit = weightUnit,
                    units = WeightUnit.entries,
                    focused = focusWeight,
                    onFocus = {
                        focusWeight = true
                        weightValue = STRING_ZERO
                        showResult = false
                    },
                    onUnitChange = { unit ->
                        if (unit != weightUnit) {
                            weightValue = roundFloat(
                                (weightValue.toFloatOrNull() ?: 0f) *
                                        weightUnit.toKilogram / unit.toKilogram
                            ).toString()
                            weightUnit = unit
                        }
                        focusWeight = true
                        showResult = false
                    },
                )
                Divider()
                Parameter(
                    label = stringResource(R.string.height),
                    value = heightValue,
                    unit = heightUnit,
                    units = HeightUnit.entries,
                    focused = !focusWeight,
                    onFocus = {
                        focusWeight = false
                        heightValue = STRING_ZERO
                        showResult = false
                    },
                    onUnitChange = { unit ->
                        if (unit != heightUnit) {
                            heightValue = roundFloat(
                                (heightValue.toFloatOrNull() ?: 0f) *
                                        heightUnit.toMeter / unit.toMeter
                            ).toString()
                            heightUnit = unit
                        }
                        focusWeight = false
                        showResult = false
                    },
                )
                Divider()
                if (showResult) {
                    ResultCard(
                        bmi = bmi,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 28.dp),
                    )
                }
            }
            if (!showResult) {
                NumPad(
                    onNumber = { number ->
                        val input = focusedValue()
                        when {
                            input == STRING_ZERO -> setFocusedValue(number)
                            input.contains(STRING_DECIMAL) && input.length < 6 ->
                                setFocusedValue(input + number)

                            !input.contains(STRING_DECIMAL) && input.length < 3 ->
                                setFocusedValue(input + number)
                        }
                    },
                    onDecimal = {
                        val input = focusedValue()
                        if (!input.contains(STRING_DECIMAL)) {
                            setFocusedValue(input + STRING_DECIMAL)
                        }
                    },
                    onDelete = {
                        val input = focusedValue()
                        if (input.length <= 1) {
                            setFocusedValue(STRING_ZERO)
                        } else {
                            setFocusedValue(input.dropLast(1))
                        }
                    },
                    onAllClear = { setFocusedValue(STRING_ZERO) },
                    onGo = {
                        val result = Bmi.calculate(
                            weight = weightValue.toFloatOrNull(),
                            weightUnit = weightUnit,
                            height = heightValue.toFloatOrNull(),
                            heightUnit = heightUnit,
                        )
                        if (BmiCategory.of(result) == BmiCategory.Invalid) {
                            Toast.makeText(
                                this@MainActivity,
                                getString(R.string.invalid_bmi),
                                Toast.LENGTH_SHORT,
                            ).show()
                        } else {
                            bmi = result
                            showResult = true
                        }
                    },
                )
            }
        }
    }

    @Composable
    private fun TopBar() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(ColorPrimary)
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back_arrow),
                modifier = Modifier
                    .size(32.dp)
                    .clickable { finish() }
                    .padding(2.dp),
                tint = Color.Black,
            )
            Text(
                text = stringResource(R.string.bmi),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 32.dp),
                color = Color.Black,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
            )
        }
        Divider()
    }

    @Composable
    private fun Divider() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(ColorDivider),
        )
    }

    @Composable
    private fun <T> Parameter(
        label: String,
        value: String,
        unit: T,
        units: List<T>,
        focused: Boolean,
        onFocus: () -> Unit,
        onUnitChange: (T) -> Unit,
    ) {
        var showUnitDialog by remember { mutableStateOf(false) }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clickable { showUnitDialog = true }
                    .padding(start = 28.dp, top = 22.dp, bottom = 28.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = label,
                    color = Color.Black,
                    fontSize = 18.sp,
                )
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    tint = ColorLine,
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onFocus() }
                    .padding(top = 22.dp, end = 28.dp, bottom = 28.dp),
            ) {
                Text(
                    text = value,
                    modifier = Modifier.fillMaxWidth(),
                    color = if (focused) ColorAccent else Color.Black,
                    fontSize = 28.sp,
                    textAlign = TextAlign.End,
                )
                Text(
                    text = unit.toString(),
                    modifier = Modifier.fillMaxWidth(),
                    color = ColorLight,
                    fontSize = 14.sp,
                    textAlign = TextAlign.End,
                )
            }
        }
        if (showUnitDialog) {
            AlertDialog(
                onDismissRequest = { showUnitDialog = false },
                confirmButton = {},
                containerColor = Color.White,
                text = {
                    Column {
                        units.forEach { item ->
                            Text(
                                text = item.toString(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        showUnitDialog = false
                                        onUnitChange(item)
                                    }
                                    .padding(16.dp),
                                color = Color.Black,
                                fontSize = 16.sp,
                            )
                        }
                    }
                },
            )
        }
    }

    @Composable
    private fun NumPad(
        onNumber: (String) -> Unit,
        onDecimal: () -> Unit,
        onDelete: () -> Unit,
        onAllClear: () -> Unit,
        onGo: () -> Unit,
    ) {
        BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
            val cell = maxWidth / 4
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .navigationBarsPadding(),
            ) {
                Row(modifier = Modifier.height(cell)) {
                    NumPadKey(text = "7", onClick = { onNumber("7") })
                    NumPadKey(text = "8", onClick = { onNumber("8") })
                    NumPadKey(text = "9", onClick = { onNumber("9") })
                    NumPadKey(
                        text = stringResource(R.string.all_clear),
                        textColor = ColorAccent,
                        onClick = onAllClear,
                    )
                }
                Row(modifier = Modifier.height(cell)) {
                    NumPadKey(text = "4", onClick = { onNumber("4") })
                    NumPadKey(text = "5", onClick = { onNumber("5") })
                    NumPadKey(text = "6", onClick = { onNumber("6") })
                    NumPadKey(onClick = onDelete) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Backspace,
                            contentDescription = stringResource(R.string.delete),
                            modifier = Modifier.size(32.dp),
                            tint = Color.Black,
                        )
                    }
                }
                Row(modifier = Modifier.height(cell * 2)) {
                    Column(modifier = Modifier.weight(3f)) {
                        Row(modifier = Modifier.height(cell)) {
                            NumPadKey(text = "1", onClick = { onNumber("1") })
                            NumPadKey(text = "2", onClick = { onNumber("2") })
                            NumPadKey(text = "3", onClick = { onNumber("3") })
                        }
                        Row(modifier = Modifier.height(cell)) {
                            NumPadKey(
                                text = "0",
                                weight = 2f,
                                onClick = { onNumber("0") },
                            )
                            NumPadKey(
                                text = stringResource(R.string.decimal),
                                onClick = onDecimal,
                            )
                        }
                    }
                    GoKey(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        onClick = onGo,
                    )
                }
            }
        }
    }

    @Composable
    private fun RowScope.NumPadKey(
        text: String? = null,
        weight: Float = 1f,
        textColor: Color = Color.Black,
        onClick: () -> Unit,
        content: @Composable (() -> Unit)? = null,
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        val pressed by interactionSource.collectIsPressedAsState()
        Box(
            modifier = Modifier
                .weight(weight)
                .fillMaxHeight()
                .background(if (pressed) ColorPrimary else Color.White)
                .border(0.5.dp, ColorDivider)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClick,
                ),
            contentAlignment = Alignment.Center,
        ) {
            when {
                content != null -> content()
                text != null -> Text(
                    text = text,
                    color = textColor,
                    fontSize = 22.sp,
                )
            }
        }
    }

    @Composable
    private fun GoKey(modifier: Modifier, onClick: () -> Unit) {
        val interactionSource = remember { MutableInteractionSource() }
        val pressed by interactionSource.collectIsPressedAsState()
        Box(
            modifier = modifier
                .background(if (pressed) ColorAccent.copy(alpha = 0.8f) else ColorAccent)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClick,
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(R.string.go),
                color = Color.White,
                fontSize = 22.sp,
            )
        }
    }

    @Composable
    private fun ResultCard(bmi: Float, modifier: Modifier = Modifier) {
        val category = BmiCategory.of(bmi)
        Card(
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(2.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        ) {
            Column(modifier = Modifier.padding(32.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = bmi.toString(),
                        modifier = Modifier.padding(start = 28.dp),
                        color = ColorAccent,
                        fontSize = 76.sp,
                    )
                    Column(
                        modifier = Modifier.padding(start = 8.dp, end = 28.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = stringResource(R.string.bmi),
                            color = Color.Black,
                            fontSize = 40.sp,
                        )
                        Text(
                            text = stringResource(
                                when (category) {
                                    BmiCategory.Underweight -> R.string.underweight
                                    BmiCategory.Overweight -> R.string.overweight
                                    else -> R.string.normal
                                }
                            ),
                            color = when (category) {
                                BmiCategory.Underweight -> ColorUnder
                                BmiCategory.Overweight -> ColorOver
                                else -> ColorNormal
                            },
                            fontSize = 14.sp,
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp)
                        .height(4.dp)
                        .background(ColorLine),
                )
                Text(
                    text = stringResource(R.string.information),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    color = Color.Black,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    ScaleLabel(stringResource(R.string.underweight), ColorUnder)
                    ScaleLabel(stringResource(R.string.normal), ColorNormal)
                    ScaleLabel(stringResource(R.string.overweight), ColorOver)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                        .height(4.5.dp)
                        .clip(RoundedCornerShape(8.dp)),
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(ColorUnder),
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(ColorNormal),
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(ColorOver),
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                ) {
                    RangeLabel(stringResource(R.string.bmi_underweight), TextAlign.Start)
                    RangeLabel(stringResource(R.string.bmi_normal_start), TextAlign.Center)
                    RangeLabel(stringResource(R.string.bmi_normal_end), TextAlign.Center)
                    RangeLabel(stringResource(R.string.bmi_overweight), TextAlign.End)
                }
            }
        }
    }

    @Composable
    private fun RowScope.ScaleLabel(text: String, color: Color) {
        Text(
            text = text,
            modifier = Modifier.weight(1f),
            color = color,
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
        )
    }

    @Composable
    private fun RowScope.RangeLabel(text: String, textAlign: TextAlign) {
        Text(
            text = text,
            modifier = Modifier.weight(1f),
            color = ColorLight,
            fontSize = 13.sp,
            textAlign = textAlign,
        )
    }

    private fun roundFloat(value: Float): Float {
        return (value * 10).roundToInt() / 10f
    }

    companion object {
        private const val STRING_ZERO = "0"
        private const val STRING_DECIMAL = "."
    }
}
