package com.shivdkh.dictionary101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.shivdkh.dictionary101.adapter.DefAdapter
import com.shivdkh.dictionary101.adapter.SenAdapter
import kotlinx.android.synthetic.main.activity_seccond.*

class SeccondActivity : AppCompatActivity() {

    lateinit var defList: ArrayList<String>
    lateinit var senList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seccond)



        defList= intent.getStringArrayListExtra("ARRAY_DEFINITION") as ArrayList<String>
        senList= intent.getStringArrayListExtra("ARRAY_SENTENCES") as ArrayList<String>

        tv_word.text= intent.getStringExtra("WORD")
        val adapter= DefAdapter(defList)
        def_listview.adapter=adapter
        if (senList.size==0){
            tv_sentences.visibility= View.INVISIBLE
        }
        val adapter2=SenAdapter(senList)
        sentence_listview.adapter=adapter2
    }
}