package uk.co.renbinden.alakajam.direction

enum class Direction(val dx: Int, val dy: Int) {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0)
}