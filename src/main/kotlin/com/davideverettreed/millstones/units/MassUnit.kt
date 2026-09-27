package com.davideverettreed.millstones.units

import kotlin.math.roundToLong

/**
 * Units of mass. Every conversion is derived from [milligramsPerUnit], so all
 * unit pairs stay mutually consistent.
 *
 * Java: `MassUnit.POUNDS.toKilograms(185.0)`, `MassUnit.GRAMS.toMilligrams(12.5)`
 */
enum class MassUnit(
    val displayName: String,
    val abbreviation: String,
    val milligramsPerUnit: Double,
) {
    MILLIGRAMS("milligrams", "mg", 1.0),
    GRAMS("grams", "g", 1_000.0),
    KILOGRAMS("kilograms", "kg", 1_000_000.0),
    METRIC_TONS("metric tons", "t", 1_000_000_000.0),

    /** International avoirdupois ounce: exactly 28.349523125 g. */
    OUNCES("ounces", "oz", 28_349.523125),

    /** International avoirdupois pound: exactly 453.59237 g. */
    POUNDS("pounds", "lb", 453_592.37),

    /** US short ton: 2,000 lb. */
    TONS("tons", "tn", 907_184_740.0),

    /** Adult African bush elephant, taken as 6,000 kg. */
    ELEPHANTS("elephants", "elephants", 6_000_000_000.0),

    /** USS Gerald R. Ford (CVN-78) full-load displacement: ~100,000 long tons. */
    USS_GERALD_R_FORD_AIRCRAFT_CARRIERS("USS Gerald R. Ford aircraft carriers", "CVN-78", 101_604_690_880_000.0);

    /** Converts [value] in this unit to [target] units. */
    fun convert(value: Double, target: MassUnit): Double =
        if (target == this) value else value * milligramsPerUnit / target.milligramsPerUnit

    /**
     * Converts [value] in this unit to whole milligrams, rounded to nearest
     * (for integer storage). Saturates at [Long.MAX_VALUE] / [Long.MIN_VALUE].
     */
    fun toMilligrams(value: Double): Long = (value * milligramsPerUnit).roundToLong()

    /** Converts stored whole [milligrams] to this unit. */
    fun fromMilligrams(milligrams: Long): Double = milligrams / milligramsPerUnit

    fun toGrams(value: Double): Double = convert(value, GRAMS)
    fun toKilograms(value: Double): Double = convert(value, KILOGRAMS)
    fun toMetricTons(value: Double): Double = convert(value, METRIC_TONS)
    fun toOunces(value: Double): Double = convert(value, OUNCES)
    fun toPounds(value: Double): Double = convert(value, POUNDS)
    fun toTons(value: Double): Double = convert(value, TONS)
    fun toElephants(value: Double): Double = convert(value, ELEPHANTS)
    fun toAircraftCarriers(value: Double): Double = convert(value, USS_GERALD_R_FORD_AIRCRAFT_CARRIERS)

    companion object {
        /** Matches enum name, display name, or abbreviation, ignoring case; null if none. */
        @JvmStatic
        fun fromString(text: String): MassUnit? {
            val s = text.trim()
            return entries.firstOrNull {
                it.name.equals(s, true) || it.displayName.equals(s, true) || it.abbreviation.equals(s, true)
            }
        }
    }
}
