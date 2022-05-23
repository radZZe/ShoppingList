package com.mimoza_app.shoppinglist.Presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.mimoza_app.shoppinglist.Domain.ShopItem
import com.mimoza_app.shoppinglist.R
import com.mimoza_app.shoppinglist.databinding.ActivityShopItemBinding
import org.w3c.dom.Text
import java.lang.RuntimeException


class ShopItemActivity : AppCompatActivity(),ShopItemFragment.OnEditingFinishedListener {
    lateinit var binding: ActivityShopItemBinding
    private var screenMode = MODE_UNKNOWN
    private var idShopItem = ShopItem.UNDEFINED_ID
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        parseIntent()
        if(savedInstanceState == null){
            launchRightMode()
        }
    }

    fun launchRightMode(){
        val fragment = when(screenMode){
            MODE_EDIT -> ShopItemFragment.newInstanceEditItem(idShopItem)
            MODE_ADD -> ShopItemFragment.newInstanceAddItem()
            else -> {throw RuntimeException("Unknown param screen mode")}
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView,fragment)
            .commit()

    }


    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_MODE)
        if ((mode != MODE_ADD) && (mode != MODE_EDIT)) {
            throw RuntimeException("Unknown param screen mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTA_SHOP_ITEM_ID)) {
                throw RuntimeException("Param id shopItem is absent")
            }
            idShopItem = intent.getIntExtra(EXTA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
        }

        Log.d("MyLog", "$screenMode  $idShopItem")

    }

    companion object {
        private const val EXTRA_MODE = "EXTRA_MODE"
        private const val EXTA_SHOP_ITEM_ID = "EXTA_SHOP_ITEM_ID"
        const val MODE_ADD = "MODE_ADD"
        const val MODE_EDIT = "MODE_EDIT"
        private const val MODE_UNKNOWN = ""


        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_MODE, MODE_EDIT)
            intent.putExtra(EXTA_SHOP_ITEM_ID, shopItemId)
            return intent
        }
    }

    override fun onEditingFinished() {
        finish()
    }
}