package com.example.lesson_5.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ImageSwitcher
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_5.MainActivity
import com.example.lesson_5.R
import com.example.lesson_5.ThemePreferences
import com.example.lesson_5.adapters.NameAdapter
import com.google.gson.reflect.TypeToken
import com.example.lesson_5.model.MessageKir
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_third.*

private const val MESSAGE_LIST = "message_list"


class ThirdFragment : Fragment(R.layout.fragment_third) {

    private var adapter: NameAdapter? = null
    private val list = mutableListOf<MessageKir>()
    private var mImageSwitcher: ImageSwitcher? = null
    private var prefs: SharedPreferences? = null
    private var layoutManager: LinearLayoutManager? = null
    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (dy > 0) {
                btn_scroll.visibility = View.GONE
            } else if (dy < 0) {
                btn_scroll.visibility = View.VISIBLE
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        changeStyleSun(view)
        lovely()
    }

    @SuppressLint("ResourceType")
    private fun lovely() {
        recycler_view.setOnClickListener {

//            val dialog = AlertFragment()
//            dialog.show(supportFragmentManager, "alertDialog")

            val popupMenu = PopupMenu(context, it)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId){
                    R.layout.fragment_alert -> {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://resoder.com"))
                        startActivity(intent)
                        true
                    }
                    R.layout.fragment_alert -> {
                        Toast.makeText(context,"Show Toast",Toast.LENGTH_LONG).show()
                        true
                    }
                    else -> false
                }
            }
            popupMenu.inflate(R.layout.fragment_alert)
            popupMenu.show()
        }
    }


    private fun changeStyleSun(view: View) {
        moon_style.setOnClickListener {
            val themePreferences = context?.let { it -> ThemePreferences(it) }
            if ( AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO ) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                themePreferences?.setThemeState("dark")
                restartApp()
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                restartApp()
                themePreferences?.setThemeState("white")
            }
        }
    }


    private fun setupView() {
        adapter = NameAdapter {
            Log.d("test", it)
        }
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(requireContext())
        image_view.setOnClickListener {
            val text = edit_text_translator.text.toString()
            if (text.isEmpty()) {
                Toast.makeText(context, "Введите сообщения", Toast.LENGTH_LONG).show()
            } else messageLatin(text)
        }
        btn_scroll.setOnClickListener(View.OnClickListener {
            scrollToBot()
        })

//        adapter = Adapter(
//                onDeleteClickListener = { id: Long ->
//                    val position = list.indexOfFirst { it == id }
//                    if (position > -1) {
//                        adapter?.setItems(list)
//                    }
//                }
//        )

//        adapter?.setHasStableIds(true)

        recycler_view.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycler_view.layoutManager = layoutManager

        adapter?.setItems(list)
    }

    private fun scrollToBot() {
        val smoothScroller = object : LinearSmoothScroller(context) {
            override fun getVerticalSnapPreference(): Int =
                    SNAP_TO_END
        }
        smoothScroller.targetPosition = list.size
        layoutManager?.startSmoothScroll(smoothScroller)
    }


    private fun messageLatin(text: String): String {
        val message = StringBuilder()

        val cyrillic: Array<Char> = arrayOf(' ', 'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к',
                'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю',
                'я', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У',
                'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
                'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z')

        val latin: Array<String> = arrayOf(" ", "a", "b", "v", "g", "d", "e", "e", "zh", "z", "i", "y", "k", "l",
                "m", "n", "o", "p", "r", "s", "t", "u", "f", "h", "ts", "ch", "sh", "sch", "", "i", "", "e", "ju", "ja",
                "A", "B", "V", "G", "D", "E", "E", "Zh", "Z", "I", "Y", "K", "L", "M", "N", "O", "P", "R", "S", "T", "U", "F",
                "H", "Ts", "Ch", "Sh", "Sch", "", "I", "", "E", "Ju", "Ja", "a", "b", "c", "d", "e", "f", "g", "h", "i",
                "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E",
                "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")

        for (i in text.indices) {
            for (j in cyrillic.indices) {
                if (text[i] == (cyrillic[j])) {
                    message.append(latin[j])
                }
            }
        }
        val itemMessage = MessageKir(message_kir = "", message_lat = "")
        itemMessage.message_kir = edit_text_translator.text.toString()
        itemMessage.message_lat = message.toString()
        list.add(itemMessage)
        adapter?.setItems(list)
        return message.toString()
    }

    override fun onResume() {
        super.onResume()
        recycler_view.addOnScrollListener(scrollListener)
    }

    override fun onPause() {
        recycler_view.removeOnScrollListener(scrollListener)
        super.onPause()
    }

    private fun restartApp() {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
    }
}