package com.example.lesson_5.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.lesson_5.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_second.*


class SecondFragment : Fragment(R.layout.fragment_second) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        deleteBtn()
    }

    private fun deleteBtn() {
        btDelete.setOnClickListener {
            Snackbar.make(container_snac, getString(R.string.snack_string), 2000)
                .setAction(getString(R.string.undo_string)) {
                }.show()
        }
    }
}