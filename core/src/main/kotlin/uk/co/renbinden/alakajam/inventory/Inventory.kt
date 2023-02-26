package uk.co.renbinden.alakajam.inventory

class Inventory {

    private val items = mutableMapOf<ItemType, Int>()

    fun getAmount(item: ItemType) = items[item] ?: 0
    fun setAmount(item: ItemType, amount: Int) {
        items[item] = amount
    }

    fun add(itemType: ItemType, amount: Int) {
        setAmount(itemType, getAmount(itemType) + amount)
    }

    fun add(item: Item) = add(item.type, item.amount)

}