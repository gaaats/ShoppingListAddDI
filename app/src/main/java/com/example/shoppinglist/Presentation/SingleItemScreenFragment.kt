package com.example.shoppinglist.Presentation

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.Domain.model.BuyItem
import com.example.shoppinglist.Presentation.Constance.Constance
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.FragmentSingleItemScreenBinding
import com.example.shoppinglist.di.ShopLIstAPP
import javax.inject.Inject

class SingleItemScreenFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as ShopLIstAPP).component
    }

    private lateinit var viewModelSingleItem: ViewModelSingleItem
    lateinit var binding: FragmentSingleItemScreenBinding
    private var currentMode = Constance.MODE_DEFAULT
    private var currentId = BuyItem.DEFAULT_INDEX

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parcingArgAndInitLocalVariables()
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingleItemScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelSingleItem = ViewModelProvider(this)[ViewModelSingleItem::class.java]

        launchCurrentScreenMode()
        addErrorListenerForInput()
        addTextChangedListenerWatcher()
    }

    private fun parcingArgAndInitLocalVariables() {
        if (!requireArguments().containsKey(Constance.MODE)) {
            throw RuntimeException("there is no mode inside")
        }
        currentMode = arguments?.getString(Constance.MODE).toString()
        if (currentMode == Constance.MODE_EDIT){
            currentId = arguments?.get(Constance.ITEM_ID) as Int
        }
    }

    private fun addErrorListenerForInput() {
        viewModelSingleItem.errorInputNameLD.observe(viewLifecycleOwner) {
            val messageErrorName = if (it) {
                getString(R.string.Wrong_title)
            } else {
                null
            }
            binding.textInLayTitle.error = messageErrorName
        }

        viewModelSingleItem.errorInputNumberLD.observe(viewLifecycleOwner) {
            val messageErrorNumber = if (it) {
                getString(R.string.Wrong_number)
            } else {
                null
            }
            binding.textInLayCount.error = messageErrorNumber
        }
    }
    private fun addTextChangedListenerWatcher() {
        binding.textInputName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModelSingleItem.cleanErrorInputName()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        binding.textInputCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModelSingleItem.cleanErrorInputNumber()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun launchCurrentScreenMode() {
        when (currentMode) {
            Constance.MODE_EDIT -> {
//                Toast.makeText(this, "id: ${item_current_id}", Toast.LENGTH_LONG).show()
                requireActivity().title = getString(R.string.edit_item_screen_title)
                viewModelSingleItem.getItemOnActivitySingle(currentId.toInt())
                viewModelSingleItem.buyItemFromGet.observe(viewLifecycleOwner) {
                    binding.textInputName.setText(it.name)
                    binding.textInputCount.setText(it.total.toString())
                }
                binding.btnSave.setOnClickListener {
                    val name = binding.textInputName.text?.toString()
                    val count = binding.textInputCount.text?.toString()
                    viewModelSingleItem.editItemOnActivitySingle(name, count)

                }

            }
            Constance.MODE_ADD -> {
                requireActivity().title = getString(R.string.add_item_screen_title)
                binding.btnSave.setOnClickListener {
                    val name = binding.textInputName.text?.toString()
                    val count = binding.textInputCount.text?.toString()
                    viewModelSingleItem.addItemOnActivitySingle(name, count)

                }
            }
        }
        viewModelSingleItem.canAppCloseScrnSingleItem.observe(viewLifecycleOwner) {
            requireActivity().onBackPressed()
        }
    }

    companion object {

        fun newInstanceAddItem(): SingleItemScreenFragment {
            return SingleItemScreenFragment().apply {
                arguments = Bundle().apply {
                    putString(Constance.MODE, Constance.MODE_ADD)
                }
            }
        }
        fun newInstanceEditItem(itemId: Int): SingleItemScreenFragment {
            return SingleItemScreenFragment().apply {
                arguments = Bundle().apply {
                    putString(Constance.MODE, Constance.MODE_EDIT)
                    putInt(Constance.ITEM_ID, itemId)
                }
            }
        }
    }
}