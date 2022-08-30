package com.example.dovui

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.dovui.Mode.DataCauHoi1
import com.example.dovui.Mode.Traloi1
import kotlin.random.Random

class GameActivity : AppCompatActivity(),View.OnClickListener {


    private val txtCauhoi: TextView by lazy { findViewById<TextView>(R.id.txt_cauhoi) }
    private val txtCauA: TextView by lazy { findViewById<TextView>(R.id.txt_cauA) }
    private val txtCauB: TextView by lazy { findViewById<TextView>(R.id.txt_cauB) }
    private val txtCauC: TextView by lazy { findViewById<TextView>(R.id.txt_cauC) }
    private val txtCauD: TextView by lazy { findViewById<TextView>(R.id.txt_cauD) }

    //private val txtTime: TextView by lazy { findViewById<TextView>(R.id.txt_time) }




    private lateinit var  nlist:DataCauHoi1
     private var cauhoilist : MutableList<DataCauHoi1> = mutableListOf()
    var soluongc : Int =0
    val  list :MutableList<DataCauHoi1> = mutableListOf();




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        cauhoilist= getDuLieu()
        setDataCau(cauhoilist.get(soluongc))
        //txtTime()
        time()


    }
    fun time() {
        Handler().postDelayed({ gameOver("Game Over") },60000)
    }


    private fun setDataCau(cauHoi: DataCauHoi1) {
        nlist=cauHoi
        txtCauA.setOnClickListener(this)
        txtCauB.setOnClickListener(this)
        txtCauC.setOnClickListener(this)
        txtCauD.setOnClickListener(this)

        txtCauA.setBackgroundResource(R.drawable.ic_radiu)
        txtCauB.setBackgroundResource(R.drawable.ic_radiu)
        txtCauC.setBackgroundResource(R.drawable.ic_radiu)
        txtCauD.setBackgroundResource(R.drawable.ic_radiu)


        for (i in list.indices){
            val random = Random.nextInt(list.size)
            //val  randomelem = list[random]
            if(list.get(i).soCau ==cauHoi.soCau){
                txtCauhoi.text=cauHoi.cauHoi
                txtCauA.text =cauHoi.listcauTraloi.get(0).cau
                txtCauB.text =cauHoi.listcauTraloi.get(1).cau
                txtCauC.text =cauHoi.listcauTraloi.get(2).cau
                txtCauD.text =cauHoi.listcauTraloi.get(3).cau
            }

        }


    }


    @SuppressLint("ResourceAsColor")
    override fun onClick(v: View) {
        when(v.id){
            R.id.txt_cauA ->{
                txtCauA.setBackgroundResource(R.drawable.ic_buttonsai)
                checkDung(txtCauA,nlist,nlist.listcauTraloi[0])
            }

            R.id.txt_cauB ->{
                txtCauB.setBackgroundResource(R.drawable.ic_buttonsai)
                checkDung(txtCauB,nlist,nlist.listcauTraloi[1])
            }

            R.id.txt_cauC ->{
                txtCauC.setBackgroundResource(R.drawable.ic_buttonsai)
                checkDung(txtCauC,nlist,nlist.listcauTraloi[2])
            }

            R.id.txt_cauD ->{
                txtCauD.setBackgroundResource(R.drawable.ic_buttonsai)
                checkDung(txtCauD,nlist,nlist.listcauTraloi[3])
            }


        }
    }
    fun next(){
        if (soluongc== cauhoilist.size -1 ){
            val  intent = Intent( this,WinActivity::class.java)
            startActivity(intent)

        }else{
            soluongc ++
            setDataCau(cauhoilist!![soluongc])

        }
    }


    private  fun checkDung( textView: TextView,dataCauHoi1: DataCauHoi1,traloi1: Traloi1){
        if (traloi1.dungs){
            next()
            Toast.makeText(this,"Phương án bạn chọn chinh sác",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Phương án bạn chọn không chinh sác",Toast.LENGTH_SHORT).show()
            gameOver("Game Over")

        }


    }

/*    private fun txtTime() {
        object : CountDownTimer(60000,1000){
            override fun onTick(p0: Long) {
                txtTime.text=""+ p0/1000
            }

            override fun onFinish() {

            }
        }.start()

    }*/

    private  fun gameOver(mes: String){
        val builder = AlertDialog.Builder(this)
        builder.setMessage(mes)
        builder.setCancelable(false)
        builder.setPositiveButton("Chơi Lại") { dialop, i ->
            soluongc =0
            setDataCau(cauhoilist!![soluongc])
            dialop.dismiss()
        }
        val alertDialog = builder.create()
        alertDialog.show()




    }
    private fun getDuLieu(): MutableList<DataCauHoi1> {

        val cauhoi1:MutableList<Traloi1> = mutableListOf();
        cauhoi1.add(Traloi1("Con Gà",false))
        cauhoi1.add(Traloi1("Con Ngỗng",false))
        cauhoi1.add(Traloi1("Con Vịt",true))
        cauhoi1.add(Traloi1("Con Ngan",false))
        list.add(DataCauHoi1(1,"Con gì chân ngắn\n" + "Mà lại có màng\n" + "Mỏ bẹt màu vàng\n"  + "Hay kêu cạp cạp?",cauhoi1))

        val cauhoi2:MutableList<Traloi1> = mutableListOf();
        cauhoi2.add(Traloi1("Gà Con",false))
        cauhoi2.add(Traloi1("Gà Trống",true))
        cauhoi2.add(Traloi1("Gà Mái",false))
        cauhoi2.add(Traloi1("Gà Tre",false))
        list.add(DataCauHoi1(2,"Con gì mào đỏ\n" + "Gáy ò ó o…\n" + "Từ sáng tinh mơ\n"+ "Gọi người thức giấc?",cauhoi2))

        val cauhoi4:MutableList<Traloi1> = mutableListOf();
        cauhoi4.add(Traloi1("Con Mèo",true))
        cauhoi4.add(Traloi1("Con Lợn",false))
        cauhoi4.add(Traloi1("Con Vịt",false))
        cauhoi4.add(Traloi1("Con Chó",true))
        list.add(DataCauHoi1(4,"Thường nằm đầu hè\n" + "Giữ cho nhà chủ\n" + "Người lạ nó sủa\n" + "Người quen nó mừng",cauhoi4))

        val cauhoi5:MutableList<Traloi1> = mutableListOf();
        cauhoi5.add(Traloi1("Con Mèo",false))
        cauhoi5.add(Traloi1("Con Hổ",false))
        cauhoi5.add(Traloi1("Con Thỏ",true))
        cauhoi5.add(Traloi1("Con Sóc",false))
        list.add(DataCauHoi1(5,"Con gì đuôi ngắn tai dài\n" + "Mắt hồng lông mượt\n" + "Có tài chạy nhanh",cauhoi5))

        val cauhoi6:MutableList<Traloi1> = mutableListOf();
        cauhoi6.add(Traloi1("Con Bò",false))
        cauhoi6.add(Traloi1("Con Trâu",true))
        cauhoi6.add(Traloi1("Con Nghé",false))
        cauhoi6.add(Traloi1("Con Bê",false))
        list.add(DataCauHoi1(6,"Con gì ăn cỏ\n" + "Đầu có 2 sừng\n" + "Lỗ mũi buộc thừng\n" + "Kéo cày rất giỏi",cauhoi6))

        val cauhoi7:MutableList<Traloi1> = mutableListOf();
        cauhoi7.add(Traloi1("Con Mèo",false))
        cauhoi7.add(Traloi1("Con Lợn",true))
        cauhoi7.add(Traloi1("Con Trâu",false))
        cauhoi7.add(Traloi1("Con Chó",false))
        list.add(DataCauHoi1(7,"Con gì ăn no\n" + "Bụng to mắt híp\n" + "Mồm kêu ụt ịt\n" + "Nằm thở phì phò",cauhoi7))

        val cauhoi8:MutableList<Traloi1> = mutableListOf();
        cauhoi8.add(Traloi1("Con Trâu",false))
        cauhoi8.add(Traloi1("Con Hổ",false))
        cauhoi8.add(Traloi1("Con Voi",false))
        cauhoi8.add(Traloi1("Con Ngựa",true))
        list.add(DataCauHoi1(8,"Con gì bốn vó\n" + "Ngực nở bụng thon\n" + "Rung rinh chiếc bờm\n" + "Phi nhanh như gió?",cauhoi8))

        val cauhoi9:MutableList<Traloi1> = mutableListOf();
        cauhoi8.add(Traloi1("Con Cua",false))
        cauhoi8.add(Traloi1("Con Rùa",false))
        cauhoi8.add(Traloi1("Con Cá ",false))
        cauhoi8.add(Traloi1("Con Tôm",true))
        list.add(DataCauHoi1(9,"Bốn cây cột dừa hai cây đinh sắc\n" + "Một cái đong đưa một cái ngúc ngoắc" ,cauhoi9))

        val cauhoi10:MutableList<Traloi1> = mutableListOf();
        cauhoi10.add(Traloi1("Con Kiến",true))
        cauhoi10.add(Traloi1("Con Sốc",false))
        cauhoi10.add(Traloi1("Con Chuột",false))
        cauhoi10.add(Traloi1("Con Sâu",false))
        list.add(DataCauHoi1(10,"Con gì bé tí\n" +  "Lại đi từng đàn\n" + "Kiếm được mồi ngon\n" + "Cùng tha về tổ",cauhoi10))

        val cauhoi11:MutableList<Traloi1> = mutableListOf();
        cauhoi11.add(Traloi1("Con Mèo",false))
        cauhoi11.add(Traloi1("Con Hổ",true))
        cauhoi11.add(Traloi1("Con Thỏ",false))
        cauhoi11.add(Traloi1("Con Sóc",false))
        list.add(DataCauHoi1(11,"Con gì lông vằn mắt xanh\n" + "Dáng di uyển chuyển, nhe nanh tìm mồi\n"
                + "Thỏ, nai gặp phải hỡi ôi\n" + "Muông thú khiếp sợ tôn ngôi chúa rừng?",cauhoi11))

        val cauhoi12:MutableList<Traloi1> = mutableListOf();
        cauhoi12.add(Traloi1("Con Mèo",false))
        cauhoi12.add(Traloi1("Con Hổ",false))
        cauhoi12.add(Traloi1("Con Thỏ",false))
        cauhoi12.add(Traloi1("Con Sóc",true))
        list.add(DataCauHoi1(12,"Chuyền cành mau lẹ\n" +
                "Có cái đuôi bông\n" + "Hạt dẻ thích ăn",cauhoi12))


        return list;
    }



}