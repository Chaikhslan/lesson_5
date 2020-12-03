package com.example.lesson_5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_message.view.*

class NameAdapter(private val clickListener: (name: String) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NameViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NameViewHolder).bind(data[position], clickListener)
    }

//    override fun getItemId(position: Int): Long = data[position].toLong()

    fun setItems(list: List<String>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    private class NameViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_message, parent, false)) {
        private val messageTextView = itemView.chat_user_message
        private val messageResultView = itemView.chat_user_message_two

        fun bind(item: String, clickListener: (name: String) -> Unit) {
            messageTextView.text = item
            messageResultView.text = item
            messageTextView.setOnClickListener {
                clickListener(item)
            }
        }
    }
}