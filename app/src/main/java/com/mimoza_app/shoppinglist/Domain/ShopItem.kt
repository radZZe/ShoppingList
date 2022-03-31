package com.mimoza_app.shoppinglist.Domain

data class ShopItem(
    val name: String,
    val count: Int,
    val active: Boolean,
    var id: Int = UNDEFINED_ID
){
    companion object{
        const val UNDEFINED_ID = -1
    }
}