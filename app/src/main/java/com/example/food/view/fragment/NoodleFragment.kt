package com.example.food.view.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.food.R
import com.example.food.data.DataVO
import com.example.food.databinding.FragmentNoodleBinding
import com.example.food.dialog.NoodleDialog
import com.example.food.util.Decoration
import com.example.food.view.adapter.NoodleAdapter

class NoodleFragment : Fragment() {

    private var _binding: FragmentNoodleBinding? = null

    private val binding get() = _binding!!

    private val dataList = mutableListOf<DataVO>()

    private lateinit var noodleAdapter: NoodleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoodleBinding.inflate(inflater, container, false)

        binding.apply {
            setRecyclerView()
            refreshRecyclerView()
            getDialog()
        }
        return binding.root
    }

    fun insertData(data: DataVO) {
        dataList.add(data)
        Toast.makeText(context, "Data Inserted", Toast.LENGTH_SHORT).show()
    }

    private fun getDialog() {
        binding.ivAdd.setOnClickListener {
            val dialog = NoodleDialog(requireContext())
            dialog.setDialog()
        }
    }

    private fun refreshRecyclerView() {
        noodleAdapter.setOnItemClickListener(object : NoodleAdapter.OnItemClickListener {
            override fun onClickItem(view: View, position: Int) {
                val data = dataList[position]
                Toast.makeText(view.context, data.name, Toast.LENGTH_SHORT).show()
            }

            override fun onClickDeleteItem(view: View, position: Int) {
                val eventHandler = DialogInterface.OnClickListener { dialog, which ->
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            dataList.removeAt(position)
                            noodleAdapter.notifyItemRemoved(position)
                            Toast.makeText(view.context, "Data Deleted", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                        }
                        DialogInterface.BUTTON_NEGATIVE -> dialog.dismiss()
                    }
                }
                AlertDialog.Builder(context).run {
                    setIcon(android.R.drawable.ic_dialog_alert)
                    setTitle("Deleting Data")
                    setMessage("Are you sure you want to delete the data?")
                    setPositiveButton("Confirm", eventHandler)
                    setNegativeButton("Cancel", eventHandler)
                    setCancelable(false)
                    show()
                }
            }
        })
    }

    private fun setRecyclerView() {
        setDataList()
        noodleAdapter = NoodleAdapter(dataList)

        binding.recyclerView.apply {
            adapter = noodleAdapter
            addItemDecoration(Decoration())
            setHasFixedSize(true)
        }
    }

    private fun setDataList() {
        for (i in 1..30) {
            if (i % 2 == 0) {
                dataList.add(DataVO(R.drawable.ramen_100_100, "Ramen"))
            } else {
                dataList.add(DataVO(R.drawable.padthai_100_100, "Padthai"))
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}