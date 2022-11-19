package com.example.food.dialog

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Toast
import com.example.food.R
import com.example.food.databinding.DialogMainBinding
import com.example.food.data.DataVO
import com.example.food.view.activity.MainActivity

class DrinkDialog(private val context: Context) {

    private val dialog = Dialog(context)

    fun setDialog() {
        val binding = DialogMainBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(false)
        dialog.show()

        binding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        binding.btnConfirm.setOnClickListener {
            val data: DataVO = when (val name = binding.etName.text.toString()) {
                COFFEE -> DataVO(R.drawable.coffee_100_100, name)
                JUICE -> DataVO(R.drawable.juice_100_100, name)
                else -> {
                    Toast.makeText(context, "Please enter Coffee or Juice", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
            }
            (context as MainActivity).drinkFragment.insertData(data)
            dialog.dismiss()
        }
    }

    companion object {
        const val COFFEE = "Coffee"
        const val JUICE = "Juice"
    }

}