package com.mimoza_app.shoppinglist.Presentation

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.mimoza_app.shoppinglist.Domain.ShopItem
import com.mimoza_app.shoppinglist.R
import java.lang.RuntimeException

class ShopItemFragment() : Fragment() {
    private lateinit var tiName: TextInputEditText
    private lateinit var tiCount: TextInputEditText
    private lateinit var nameLayout: TextInputLayout
    private lateinit var countLayout: TextInputLayout
    private lateinit var onEditingFinishedListener: OnEditingFinishedListener
    private lateinit var saveBtn: Button
    private lateinit var viewModel: ShopItemViewModel
    private var screenMode = MODE_UNKNOWN
    private var idShopItem = ShopItem.UNDEFINED_ID


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnEditingFinishedListener){
            onEditingFinishedListener = context
        }else{
            throw RuntimeException("Error onEditingFinishedListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceStat: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop_item, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        initViews(view)
        observeViewModel()
        tiName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        tiCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputCount()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun observeViewModel(){
        viewModel.errorInputName.observe(viewLifecycleOwner) {
            if (it) {
                nameLayout.error = "Error"
            } else {
                nameLayout.error = null
            }
        }
        viewModel.errorInputCount.observe(viewLifecycleOwner) {
            if (it) {
                countLayout.error = "Error"
            } else {
                countLayout.error = null
            }
        }
        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            onEditingFinishedListener.onEditingFinished()
        }
    }


    private fun launchEditMode() {
        viewModel.getShopItem(idShopItem)
        viewModel.shopItem.observe(viewLifecycleOwner) { item ->
            tiName.setText(item.name)
            tiCount.setText(item.count.toString())
        }
        saveBtn.setOnClickListener {
            val inputName = tiName.text.toString()
            val inputCount = tiCount.text.toString()
            viewModel.editShopItem(inputName, inputCount)

        }


    }

    private fun launchAddMode() {
        saveBtn.setOnClickListener {
            val inputName = tiName.text.toString()
            val inputCount = tiCount.text.toString()
            viewModel.addShopItem(inputName, inputCount)
        }

    }

    private fun parseParams() {
        val args = arguments
        if (args?.containsKey(SCREEN_MODE) == false) {
            throw java.lang.RuntimeException("Param screen mode is absent")
        }
        val mode = args?.getString(SCREEN_MODE)
        if ((mode != MODE_ADD) && (mode != MODE_EDIT)) {
            throw java.lang.RuntimeException("Unknown param screen mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(SHOP_ITEM_ID)) {
                throw java.lang.RuntimeException("Param id shopItem is absent")
            }
            idShopItem = args.getInt(SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
        }

        Log.d("MyLog", "$screenMode  $idShopItem")

    }

    private fun initViews(view: View) {
        tiName = view.findViewById(R.id.ti_Name)
        tiCount = view.findViewById(R.id.ti_Count)
        nameLayout = view.findViewById(R.id.nameLayout)
        countLayout = view.findViewById(R.id.countLayout)
        saveBtn = view.findViewById(R.id.saveBtn)

    }

    interface OnEditingFinishedListener {
        fun onEditingFinished()
    }

    companion object {
        const val SCREEN_MODE = "EXTRA_MODE"
        const val SHOP_ITEM_ID = "EXTA_SHOP_ITEM_ID"
        private const val MODE_ADD = "MODE_ADD"
        private const val MODE_EDIT = "MODE_EDIT"
        private const val MODE_UNKNOWN = ""

        fun newInstanceAddItem(): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditItem(shopItemId: Int): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(SHOP_ITEM_ID, shopItemId)
                }
            }
        }

    }
}