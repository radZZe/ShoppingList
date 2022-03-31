package com.mimoza_app.shoppinglist.Presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.mimoza_app.shoppinglist.Domain.ShopItem
import com.mimoza_app.shoppinglist.R
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {

    lateinit var viewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this){
            Log.d("MyLog",it.toString())
        }
        viewModel.getShopList()
        var element:ShopItem = viewModel.shopList.value?.get(0)
            ?: throw RuntimeException("Ошибка")
        viewModel.deleteShopItem(element)
    }
}