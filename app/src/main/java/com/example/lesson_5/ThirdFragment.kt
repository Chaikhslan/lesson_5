package com.example.lesson_5

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_third.*

class ThirdFragment : Fragment(R.layout.fragment_third) {

    private var adapter: NameAdapter? = null
    private val list = mutableListOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView(){
        adapter = NameAdapter {
            Log.d("test", it)
        }
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(requireContext())
        image_view.setOnClickListener {
            list.add(edit_text_translator.text.toString())
            adapter?.setItems(list)
        }
    }

}