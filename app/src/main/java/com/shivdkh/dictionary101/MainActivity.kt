package com.shivdkh.dictionary101

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val queue = Volley.newRequestQueue(this)
        val apikey = "871ebb91-9d76-4f74-af33-0c3e4916d34a"

        //onclicklistener to the serach button

        btn_search.setOnClickListener {
            val word = et_search.text.toString()

            et_search.setText("")

            //checking if word is entered
            if (word == "") {
                Toast.makeText(this, "Please Enter a Word", Toast.LENGTH_SHORT).show()
            } else {

                //Making get request to meriam webster api

                val url =
                    "https://www.dictionaryapi.com/api/v3/references/learners/json/$word?key=$apikey"
                val stringRequest = StringRequest(Request.Method.GET, url,
                    { response ->
                        val definations = extractDefinition(response)
                        val sentences:JSONArray? = extractSentences(response)

                        //creating array list of the jsonArray
                        val listDefination = ArrayList<String>()
                        for (i in 0 until definations.length()) {
                            listDefination.add(definations.get(i).toString())
                            Log.d("defination", listDefination.get(i))
                        }
                        val listSentences = ArrayList<String>()
                        if (sentences != null){
                            for (i in 0 until sentences.length()) {
                                val ob= sentences.getJSONObject(i)
                                var sentence= ob.get("t").toString()
                                sentence=sentence.replace("{it}","")
                                sentence=sentence.replace("{/it}","")
                                sentence=sentence.replace("{rdquo}","")
                                sentence=sentence.replace("{ldquo}","")
                                sentence=sentence.replace("{phrase}","")
                                sentence=sentence.replace("{/phrase}","")
                                listSentences.add(sentence)
                                Log.d("sentences", listSentences[i])
                            }
                        }


                        //intent to Seccond Activity

                        val intent= Intent(this, SeccondActivity::class.java)
                        intent.putExtra("ARRAY_DEFINITION", listDefination)
                        intent.putExtra("ARRAY_SENTENCES", listSentences)
                        intent.putExtra("WORD", word)
                        startActivity(intent)


                    }, { error ->
                        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()

                    })
                queue.add(stringRequest)

            }
        }
    }


    private fun extractDefinition(response: String): JSONArray {
        val jsonArray = JSONArray(response)
        val firstIndex = jsonArray.getJSONObject(0)
        return firstIndex.getJSONArray("shortdef")
    }

    private fun extractSentences(response: String): JSONArray? {
        try {
            val jsonArray = JSONArray(response)
            val firstIndex = jsonArray.getJSONObject(0)
            val def = firstIndex.getJSONArray("def")
            val zeroth = def.getJSONObject(0)
            val sseq = zeroth.getJSONArray("sseq")
            val zero2 = sseq.getJSONArray(0)
            val zero3 = zero2.getJSONArray(0)
            val one = zero3.getJSONObject(1)
            val dt = one.getJSONArray("dt")
            val one2 = dt.getJSONArray(1)
            return one2.getJSONArray(1)
        }
        catch (e:Exception){
            return null
        }


    }
}