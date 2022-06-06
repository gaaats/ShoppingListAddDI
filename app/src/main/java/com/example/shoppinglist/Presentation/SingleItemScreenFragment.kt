package com.example.shoppinglist.Presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.shoppinglist.Domain.BuyItem
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.FragmentSingleItemScreenBinding
import java.lang.RuntimeException

class SingleItemScreenFragment : Fragment() {
    private val viewModelSingleItem: ViewModelSingleItem by viewModels { faaaactory() }
    lateinit var binding: FragmentSingleItemScreenBinding
    private var currentMode = MODE_DEFAULT
    private var currentId = BuyItem.DEFAULT_INDEX

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingleItemScreenBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parcingArgAndInitLocalVariables()
        launchCurrentScreenMode()
        addErrorListenerForInput()
        addTextChangedListenerWatcher()
    }

    private fun parcingArgAndInitLocalVariables() {
        if (!requireArguments().containsKey(MODE)) {
            throw RuntimeException("there is no mode inside")
        }
        currentMode = arguments?.getString(MODE).toString()
        if (currentMode == MODE_EDIT){
            currentId = arguments?.get(ITEM_ID) as Int
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
            MODE_EDIT -> {
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
            MODE_ADD -> {
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
                    putString(MODE, MODE_ADD)
                }
            }
        }
        fun newInstanceEditItem(itemId: Int): SingleItemScreenFragment {
            return SingleItemScreenFragment().apply {
                arguments = Bundle().apply {
                    putString(MODE, MODE_EDIT)
                    putInt(ITEM_ID, itemId)
                }
            }
        }

        private const val MODE = "MODE"
        private const val MODE_ADD = "MODE_ADD"
        private const val MODE_EDIT = "MODE_EDIT"
        private const val MODE_DEFAULT = "MODE_DEFAULT"
        private const val ITEM_ID = "ITEM_ID"


//        fun createIntentForSingleItemAdd(context: Context): Intent {
//            Intent(context, SingleItenScreenActivity::class.java).putExtra(MODE, MODE_ADD)
//                .also { return it }
//        }
//
//        fun createIntentForSingleItemEdit(context: Context, id: Int): Intent {
//            Intent(context, SingleItenScreenActivity::class.java).putExtra(MODE, MODE_EDIT)
//                .putExtra(ITEM_ID, id)
//                .also { return it }
//        }
    }

}