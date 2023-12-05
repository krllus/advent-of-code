package year_2023

import Day
import solve
import year_2022.Command
import year_2022.toCommand

class Day05 : Day(day = 5, year = 2023, "If You Give A Seed A Fertilizer") {

    private val groups by lazy { inputAsString.split("\n\n") }
    private fun getSeeds(): List<Seed> {
        return groups.first()
            .split(": ").last()
            .split(" ").map { it.toSeed() }
    }

    private fun getSeedToSoil(): List<Coordinate> {
        return groups[1].split("\n")
            .drop(1)
            .toCoordinates()
    }

    private fun getSoilToFertilizer(): List<Coordinate> {
        return groups[2].split("\n")
            .drop(1)
            .toCoordinates()
    }

    private fun getFertilizerToWater(): List<Coordinate> {
        return groups[3].split("\n")
            .drop(1)
            .toCoordinates()
    }
    private fun getWaterToLight(): List<Coordinate> {
        return groups[4].split("\n")
            .drop(1)
            .toCoordinates()
    }

    private fun getLightToTemperature(): List<Coordinate> {
        return groups[5].split("\n")
            .drop(1)
            .toCoordinates()
    }

    private fun getTemperatureToHumidity(): List<Coordinate> {
        return groups[6].split("\n")
            .drop(1)
            .toCoordinates()
    }

    private fun getHumidityToLocation(): List<Coordinate> {
        return groups[7].split("\n")
            .drop(1)
            .toCoordinates()
    }

    override fun part1(): Int {

        val seedToLocation = mutableMapOf<Seed, Coordinate>()

        val seeds = getSeeds()
        val seedToSoil = getSeedToSoil()
        val soilToFertilizer = getSoilToFertilizer()
        val fertilizerToWater = getFertilizerToWater()
        val waterToLight = getWaterToLight()
        val lightToTemperature = getLightToTemperature()
        val temperatureToHumidity = getTemperatureToHumidity()
        val humidityToLocation = getHumidityToLocation()

        for(seed in seeds){
//            val location = navigate()
        }

        return 0
    }

    override fun part2(): Int {
        return 0
    }
}

private typealias Seed = Long
private typealias CoordinateAxis = Long
private typealias CoordinateRange = LongRange

fun List<String>.toCoordinates(): List<Coordinate> {
    return this.map { it.toCoordinate() }
}

fun String.toCoordinate(): Coordinate {
    val axis = this.split(" ").map { it.toCoordinateAxis() }
    return Coordinate(
        destination = axis[0],
        source = axis[1],
        length = axis[2]
    )
}

data class Coordinate(
    val destinationRange: CoordinateRange,
    val sourceRange: CoordinateRange
) {
    constructor(
        destination: CoordinateAxis,
        source: CoordinateAxis,
        length: CoordinateAxis
    ) : this(
        destinationRange = CoordinateRange(start = destination, endInclusive = destination + length),
        sourceRange = CoordinateRange(start = source, endInclusive = source + length)
    )
}


fun String.toSeed(): Seed {
    return this.toLong()
}

fun String.toCoordinateAxis(): CoordinateAxis {
    return this.toLong()
}

fun main() {
    solve<Day05>(offerSubmit = true) {
        """
            seeds: 79 14 55 13

            seed-to-soil map:
            50 98 2
            52 50 48

            soil-to-fertilizer map:
            0 15 37
            37 52 2
            39 0 15

            fertilizer-to-water map:
            49 53 8
            0 11 42
            42 0 7
            57 7 4

            water-to-light map:
            88 18 7
            18 25 70

            light-to-temperature map:
            45 77 23
            81 45 19
            68 64 13

            temperature-to-humidity map:
            0 69 1
            1 0 69

            humidity-to-location map:
            60 56 37
            56 93 4
        """.trimIndent()(part1 = 35)
    }
}