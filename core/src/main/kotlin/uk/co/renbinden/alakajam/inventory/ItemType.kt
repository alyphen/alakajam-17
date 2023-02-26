package uk.co.renbinden.alakajam.inventory

enum class ItemType(
    val displayName: String,
    val description: String
) {

    HUNGY("Hungy?", "A red teddy bear. He's reaaaaally cuddly and a bit damp."),
    OTHER_TEDDY_1("Hungy?", "A green teddy bear. He's a beautiful lime colour and a bit damp."),
    OTHER_TEDDY_2("Hungy?", "A yellow teddy bear. He's super soft and a bit damp."),
    OTHER_TEDDY_3("Hungy?", "A brown teddy bear. He's seen better days, and looks a bit sad, and is a bit damp."),
    BRICK("Brick", "A brick from a famous building toy brand."),
    CHALICE("Chalice", "The chalice from the palace is the brew that is true."),
    CANDLE_STAND("Candle stand", "A fancy, medieval-style candle stand."),
    JIGSAW_PUZZLE_PIECE("Jigsaw puzzle piece", "A piece of a jigsaw puzzle. Is that a bit of cloud on it, or a sheep?"),
    RING("Ring", "A beautiful ring."),
    GOOD_ORB("Orb", "An orb filled with an opalescent smoke. What could it mean?"),
    BAD_ORB("Orb", "An orb filled with brightly-coloured lightning. What could it mean?"),
    STATIC_ORB("Orb", "An orb filled with static. What could it mean?"),
    GEM("Gem", "A beautiful gem. Might fetch a high price."),
    TREASURE_CHEST("Treasure chest", "A chest of doubloons, gems, and various other displays of opulence."),
    SMARTPHONE("Smartphone", "This might need to be left in a bag of rice for a while..."),
    CROWN("Crown", "A crown fit for a king.")

}