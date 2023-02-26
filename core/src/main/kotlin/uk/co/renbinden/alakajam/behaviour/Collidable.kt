package uk.co.renbinden.alakajam.behaviour

interface Collidable {
    val isSolid: Boolean
    fun collide()
}