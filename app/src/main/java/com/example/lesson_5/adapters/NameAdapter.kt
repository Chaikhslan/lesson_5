package com.example.lesson_5.adapters

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Color.green
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.PopupWindow
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuPopupHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_5.R
import com.example.lesson_5.model.MessageKir
import kotlinx.android.synthetic.main.item_message.view.*

class NameAdapter(private val clickListener: (name: String) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = mutableListOf<MessageKir>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NameViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NameViewHolder).bind(data[position], clickListener)
    }

//    override fun getItemId(position: Int): Long = data[position].toLong()

    fun setItems(list: MutableList<MessageKir>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    private class NameViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_message, parent, false)) {
        private val messageTextView = itemView.chat_user_message
        private val messageResultView = itemView.chat_user_message_two
        private val recyclerView = itemView.recycler_view_message

        init {
            recyclerView.setOnClickListener {

                val pop = PopupMenu(recyclerView.context,it)
                val popupWindow = PopupWindow(recyclerView.context)
//                popupWindow.contentView = recyclerView
                popupWindow.showAsDropDown(recyclerView)

                pop.inflate(R.menu.alert_menu)

                pop.setOnMenuItemClickListener { item ->
                    when(item.itemId){
                        R.id.menu_copy->{ }
                        R.id.menu_share->{ }
                        R.id.menu_delete->{ }
                        R.id.menu_favorite->{ }
                    }
                    true
                }
                pop.show()
                true
            }
        }


        fun bind(item: MessageKir, clickListener: (name: String) -> Unit) {
            messageTextView.text = item.toString()
            messageResultView.text = item.toString()
            messageTextView.setOnClickListener {
                clickListener(item.toString())
            }
        }
    }
}