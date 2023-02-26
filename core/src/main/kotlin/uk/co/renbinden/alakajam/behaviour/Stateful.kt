package uk.co.renbinden.alakajam.behaviour

interface Stateful {

    val id: Int
    var state: Map<String, Any>

}