package com.davideverettreed.millstones.units

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class MassUnitTest {
    private val eps = 1e-9

    @Test fun poundsToKilograms() = assertEquals(0.45359237, MassUnit.POUNDS.toKilograms(1.0), eps)
    @Test fun kilogramsToOunces() = assertEquals(35.27396194958, MassUnit.KILOGRAMS.toOunces(1.0), 1e-9)
    @Test fun gramsToOunces() = assertEquals(1.0, MassUnit.GRAMS.toOunces(28.349523125), eps)
    @Test fun milligramsToPounds() = assertEquals(1.0, MassUnit.MILLIGRAMS.toPounds(453_592.37), eps)
    @Test fun poundsToOunces() = assertEquals(16.0, MassUnit.POUNDS.toOunces(1.0), eps)
    @Test fun tonsToPounds() = assertEquals(2_000.0, MassUnit.TONS.toPounds(1.0), eps)
    @Test fun metricTonsToKilograms() = assertEquals(1_000.0, MassUnit.METRIC_TONS.toKilograms(1.0), eps)
    @Test fun elephantsToKilograms() = assertEquals(6_000.0, MassUnit.ELEPHANTS.toKilograms(1.0), eps)
    @Test fun carrierToKilograms() =
        assertEquals(101_604_690.88, MassUnit.USS_GERALD_R_FORD_AIRCRAFT_CARRIERS.toKilograms(1.0), 1e-3)

    @Test fun toMilligramsRoundsInsteadOfTruncating() = assertEquals(290L, MassUnit.GRAMS.toMilligrams(0.29))
    @Test fun milligramsRoundTrip() = assertEquals(185.0, MassUnit.POUNDS.fromMilligrams(MassUnit.POUNDS.toMilligrams(185.0)), 1e-5)
    @Test fun sameUnitIsIdentity() = assertEquals(3.3, MassUnit.OUNCES.convert(3.3, MassUnit.OUNCES), 0.0)

    @Test fun everyUnitConvertsToEveryOther() {
        for (from in MassUnit.entries) for (to in MassUnit.entries) {
            val back = to.convert(from.convert(1.0, to), from)
            assertEquals(1.0, back, 1e-9, "$from -> $to -> $from")
        }
    }

    @Test fun fromString() {
        assertEquals(MassUnit.POUNDS, MassUnit.fromString("lb"))
        assertEquals(MassUnit.KILOGRAMS, MassUnit.fromString(" Kilograms "))
        assertEquals(MassUnit.METRIC_TONS, MassUnit.fromString("metric_tons"))
        assertNull(MassUnit.fromString("stone"))
    }
}
