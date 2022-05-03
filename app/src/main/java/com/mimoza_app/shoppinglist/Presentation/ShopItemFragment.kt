package com.mimoza_app.shoppinglist.Presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mimoza_app.shoppinglist.R

class ShopItemFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceStat: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop_item,container,false)
    }
}