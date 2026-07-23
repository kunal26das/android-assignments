package io.github.kunal26das.bmi

import kotlin.math.roundToInt

enum class WeightUnit(private val label: String, val toKilogram: Float) {
    Kilogram("Kilogram", 1f),
    Pound("Pound", 0.453592f);

    override fun toString() = label
}

enum class HeightUnit(private val label: String, val toMeter: Float) {
    Meter("Meter", 1f),
    Centimeter("Centimeter", 0.01f),
    Feet("Feet", 0.3048f),
    Inch("Inch", 0.0254f);

    override fun toString() = label
}

enum class BmiCategory {
    Underweight,
    Normal,
    Overweight,
    Invalid;

    companion object {
        fun of(bmi: Float) = when {
            bmi >= 16f && bmi < 18.5f -> Underweight
            bmi >= 18.5f && bmi <= 25f -> Normal
            bmi > 25f && bmi <= 40f -> Overweight
            else -> Invalid
        }
    }
}

object Bmi {

    fun calculate(
        weight: Float?,
        weightUnit: WeightUnit,
        height: Float?,
        heightUnit: HeightUnit,
    ): Float {
        if (weight == null || height == null) return 0f
        val kilograms = weight * weightUnit.toKilogram
        val meters = height * heightUnit.toMeter
        if (meters <= 0f) return 0f
        return (kilograms / (meters * meters) * 10).roundToInt() / 10f
    }
}
