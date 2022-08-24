package com.example.dovui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.dovui.Mode.DataCauHoi
import com.example.dovui.Mode.TraLoi

class GameActivity : AppCompatActivity() {

    lateinit var txtCauhoi :TextView
    lateinit var txtCauA : TextView
    lateinit var txtCauB : TextView
    lateinit var  txtCauC : TextView
    lateinit var txtCauD : TextView

     val  cauhoilit : List<DataCauHoi> = listOf()
     val  soluongCau : Int=0;







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

         txtCauhoi =findViewById(R.id.txt_cauhoi)
         txtCauA =findViewById(R.id.txt_cauA)
         txtCauB  =findViewById(R.id.txt_cauB)
         txtCauC =findViewById(R.id.txt_cauC)
         txtCauD =findViewById(R.id.txt_cauD)




        setDataCau(cauhoilit.get(soluongCau))

    }

    private fun setDataCau(get: DataCauHoi) {


    }
    private fun getDuLieu(): List<DataCauHoi> {
        val  list :MutableList<DataCauHoi> = mutableListOf();
        val cauhoi1:MutableList<TraLoi> = mutableListOf();
        cauhoi1.add(TraLoi("",false))
        cauhoi1.add(TraLoi("",true))
        cauhoi1.add(TraLoi("",false))
        cauhoi1.add(TraLoi("",false))
        list.add(DataCauHoi(1,"",cauhoi1))





        return list;


    }


}