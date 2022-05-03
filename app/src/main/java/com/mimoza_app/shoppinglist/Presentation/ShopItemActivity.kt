package com.mimoza_app.shoppinglist.Presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.mimoza_app.shoppinglist.Domain.ShopItem
import com.mimoza_app.shoppinglist.R
import com.mimoza_app.shoppinglist.databinding.ActivityShopItemBinding
import org.w3c.dom.Text
import java.lang.RuntimeException


class ShopItemActivity : AppCompatActivity() {
    lateinit var binding: ActivityShopItemBinding
//
//    private lateinit var tiName: TextInputEditText
//    private lateinit var tiCount: TextInputEditText
//    private lateinit var nameLayout: TextInputLayout
//    private lateinit var countLayout: TextInputLayout
//    private lateinit var saveBtn: Button
//    private lateinit var viewModel: ShopItemViewModel
//
//    private var screenMode = MODE_UNKNOWN
//    private var idShopItem = ShopItem.UNDEFINED_ID
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        parseIntent()
//        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
//        initViews()
//        viewModel.errorInputName.observe(this){
//            if(it){
//                nameLayout.error = "Error"
//            }else{
//                nameLayout.error = null
//            }
//        }
//        viewModel.errorInputCount.observe(this){
//            if(it){
//                countLayout.error = "Error"
//            }else{
//                countLayout.error = null
//            }
//        }
//        tiName.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                viewModel.resetErrorInputName()
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//
//            }
//
//        })
//        tiCount.addTextChangedListener(object :TextWatcher{
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                viewModel.resetErrorInputCount()
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//
//            }
//
//        })
//        when(screenMode){
//            MODE_EDIT -> launchEditMode()
//            MODE_ADD -> launchAddMode()
//        }
    }

//    private fun launchEditMode(){
//        viewModel.getShopItem(idShopItem)
//        viewModel.shopItem.observe(this){ item ->
//            tiName.setText(item.name)
//            tiCount.setText(item.count.toString())
//        }
//        saveBtn.setOnClickListener{
//            val inputName = tiName.text.toString()
//            val inputCount = tiCount.text.toString()
//            viewModel.editShopItem(inputName,inputCount)
//
//        }
//        viewModel.shouldCloseScreen.observe(this){
//            finish()
//        }
//
//    }
//
//    private fun launchAddMode(){
//        saveBtn.setOnClickListener{
//            val inputName = tiName.text.toString()
//            val inputCount = tiCount.text.toString()
//            viewModel.addShopItem(inputName,inputCount)
//        }
//        viewModel.shouldCloseScreen.observe(this){
//            finish()
//        }
//    }
//
//    private fun parseIntent() {
//        if (!intent.hasExtra(EXTRA_MODE)) {
//            throw RuntimeException("Param screen mode is absent")
//        }
//        val mode = intent.getStringExtra(EXTRA_MODE)
//        if ((mode != MODE_ADD) && (mode != MODE_EDIT)) {
//            throw RuntimeException("Unknown param screen mode")
//        }
//        screenMode = mode
//        if (screenMode == MODE_EDIT) {
//            if (!intent.hasExtra(EXTA_SHOP_ITEM_ID)) {
//                throw RuntimeException("Param id shopItem is absent")
//            }
//            idShopItem = intent.getIntExtra(EXTA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
//        }
//
//        Log.d("MyLog", "$screenMode  $idShopItem")
//
//    }
//
//    private fun initViews() {
//        tiName = findViewById(R.id.ti_Name)
//        tiCount = findViewById(R.id.ti_Count)
//        nameLayout = findViewById(R.id.nameLayout)
//        countLayout = findViewById(R.id.countLayout)
//        saveBtn = findViewById(R.id.saveBtn)
//
//    }
//
    companion object {
        private const val EXTRA_MODE = "EXTRA_MODE"
        private const val EXTA_SHOP_ITEM_ID = "EXTA_SHOP_ITEM_ID"
        private const val MODE_ADD = "MODE_ADD"
        private const val MODE_EDIT = "MODE_EDIT"
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
}