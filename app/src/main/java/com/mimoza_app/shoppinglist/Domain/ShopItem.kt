package com.mimoza_app.shoppinglist.Domain

data class ShopItem(
    var name: String,
    var count: Int,
    val active: Boolean,
    var id: Int = UNDEFINED_ID
){
    companion object{
        const val UNDEFINED_ID = -1
    }
}