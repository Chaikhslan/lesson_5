package com.example.lesson_5

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_third.*
import java.lang.StringBuilder

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
            val text = edit_text_translator.text.toString()
            if (text.isEmpty()) {
                Toast.makeText(context, "Введите сообщения", Toast.LENGTH_LONG).show()
            } else messageLatin(text)
        }
    }

    private fun messageLatin(text: String): String {
        val message = StringBuilder()

        val cyrillic: Array<Char> = arrayOf(' ','а','б','в','г','д','е','ё', 'ж','з','и','й','к',
                'л','м','н','о', 'п','р','с','т','у','ф','х', 'ц','ч', 'ш','щ','ъ','ы','ь','э', 'ю',
                'я','А','Б','В','Г','Д','Е','Ё','Ж','З','И','Й','К','Л','М','Н','О','П','Р','С','Т','У',
                'Ф','Х', 'Ц', 'Ч','Ш', 'Щ','Ъ','Ы','Ь','Э','Ю','Я','a','b','c','d','e','f','g','h','i',
                'j','k','l','m','n','o','p','q','r','s','t','u','v','w', 'x','y','z','A','B','C','D',
                'E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V', 'W','X','Y','Z')

        val latin: Array<String> = arrayOf(" ","a","b","v","g","d","e","e","zh","z","i","y","k","l",
                "m","n","o","p","r","s","t","u","f","h","ts","ch","sh","sch", "","i", "","e","ju","ja",
                "A","B","V","G","D","E","E","Zh","Z","I","Y","K","L","M","N","O","P","R","S","T","U","F",
                "H","Ts","Ch","Sh","Sch", "","I", "","E","Ju","Ja","a","b","c","d","e","f","g","h","i",
                "j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E",
                "F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z")

        for (i in text.indices) {
            for (j in cyrillic.indices) {
                if (text[i] == (cyrillic[j])) {
                    message.append(latin[j])
                }
            }
        }
//        list.add(edit_text_translator.text.toString())
        list.add(message.toString())
        adapter?.setItems(list)
        return message.toString()
    }

}