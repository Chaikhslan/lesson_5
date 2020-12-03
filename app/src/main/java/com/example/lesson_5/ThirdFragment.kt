package com.example.lesson_5

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_third.*


class ThirdFragment : Fragment(R.layout.fragment_third) {

    private var adapter: NameAdapter? = null
    private val list = mutableListOf<String>()
    private var layoutManager: LinearLayoutManager? = null
    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (dx == 0) {
                btn_scroll.isGone
            } else btn_scroll.visibility
        }
    }

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
//        btn_scroll.setOnClickListener {
//            Toast.makeText(context, "сообщения", Toast.LENGTH_LONG).show()
//            recycler_view.scrollToPosition(list.size)
//        }
        btn_scroll.setOnClickListener(View.OnClickListener {
//            v -> v.visibility = View.GONE
            scrollToTop()
        })


        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycler_view.layoutManager = layoutManager

        adapter?.setItems(list)
    }

    private fun scrollToTop() {
        val smoothScroller = object : LinearSmoothScroller(context){
            override fun getVerticalSnapPreference(): Int =
                    SNAP_TO_END
        }
        smoothScroller.targetPosition = list.size
        layoutManager?.startSmoothScroll(smoothScroller)
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
        list.add(edit_text_translator.text.toString())
        list.add(message.toString())
        adapter?.setItems(list)
        return message.toString()
    }

    override fun onResume() {
        super.onResume()
        // add scroll listener
        recycler_view.addOnScrollListener(scrollListener)
    }

    override fun onPause() {
        // remove scroll listener
        recycler_view.removeOnScrollListener(scrollListener)
        super.onPause()
    }

}