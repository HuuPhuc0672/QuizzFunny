package com.example.dovui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.billingclient.api.*
import com.example.dovui.Mode.DataCauHoi1
import com.example.dovui.Mode.Traloi1
import com.google.common.collect.ImmutableList
import kotlin.random.Random


class GameActivity : AppCompatActivity(),View.OnClickListener {

    private val inapp_type_1 = "free_image_animal_15_day"
    private val inapp_type_2 = "free_image_animal_1_day"
    private val inapp_type_3 = "free_image_animal_30_day"
    private val inapp_type_4 = "free_image_animal_3_day"
    private val inapp_type_5 = "free_image_animal_7_day"

    private var listProductDetails :MutableList<ProductDetails> = mutableListOf();
    private lateinit var billingClient: BillingClient

    private val txtCauhoi: TextView by lazy { findViewById<TextView>(R.id.txt_cauhoi) }
    private val txtCauA: TextView by lazy { findViewById<TextView>(R.id.txt_cauA) }
    private val txtCauB: TextView by lazy { findViewById<TextView>(R.id.txt_cauB) }
    private val txtCauC: TextView by lazy { findViewById<TextView>(R.id.txt_cauC) }
    private val txtCauD: TextView by lazy { findViewById<TextView>(R.id.txt_cauD) }
    private  val  btnmuakc: ImageView by lazy { findViewById<ImageView>(R.id.btn_billing) }

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



        ///// biling
        val purchasesUpdatedListener =
            PurchasesUpdatedListener { billingResult, purchases ->

            }

        val billingClient = BillingClient.newBuilder(this)
            .setListener(purchasesUpdatedListener)
            .enablePendingPurchases()
            .build()

        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode ==  BillingClient.BillingResponseCode.OK) {
                    val queryProductDetailsParams =
                        QueryProductDetailsParams.newBuilder()
                            .setProductList(
                                ImmutableList.of(
                                    QueryProductDetailsParams.Product.newBuilder()
                                        .setProductId(inapp_type_3)
                                        .setProductType(BillingClient.ProductType.INAPP)
                                        .build()))
                            .build()

                    billingClient.queryProductDetailsAsync(queryProductDetailsParams) {
                            billingResult, productDetailsList ->
                        listProductDetails=productDetailsList

                    }

                }
            }
            override fun onBillingServiceDisconnected() {
            }
        })


        btnmuakc.setOnClickListener {
            val productDetailsParamsList = listOf(
                BillingFlowParams.ProductDetailsParams.newBuilder()
                    .setProductDetails(listProductDetails[0])
                    .build()
            )
            val billingFlowParams = BillingFlowParams.newBuilder()
                .setProductDetailsParamsList(productDetailsParamsList)
                .build()
            val billingResult = billingClient.launchBillingFlow(this, billingFlowParams)
        }






















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
            Toast.makeText(this,"Ph????ng ??n b???n ch???n chinh s??c",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Ph????ng ??n b???n ch???n kh??ng chinh s??c",Toast.LENGTH_SHORT).show()
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
        builder.setPositiveButton("Ch??i L???i") { dialop, i ->
            soluongc =0
            setDataCau(cauhoilist!![soluongc])
            dialop.dismiss()
        }
        val alertDialog = builder.create()
        alertDialog.show()




    }
    private fun getDuLieu(): MutableList<DataCauHoi1> {

        val cauhoi1:MutableList<Traloi1> = mutableListOf();
        cauhoi1.add(Traloi1("Con G??",false))
        cauhoi1.add(Traloi1("Con Ng???ng",false))
        cauhoi1.add(Traloi1("Con V???t",true))
        cauhoi1.add(Traloi1("Con Ngan",false))
        list.add(DataCauHoi1(1,"Con g?? ch??n ng???n\n" + "M?? l???i c?? m??ng\n" + "M??? b???t m??u v??ng\n"  + "Hay k??u c???p c???p?",cauhoi1))

        val cauhoi2:MutableList<Traloi1> = mutableListOf();
        cauhoi2.add(Traloi1("G?? Con",false))
        cauhoi2.add(Traloi1("G?? Tr???ng",true))
        cauhoi2.add(Traloi1("G?? M??i",false))
        cauhoi2.add(Traloi1("G?? Tre",false))
        list.add(DataCauHoi1(2,"Con g?? m??o ?????\n" + "G??y ?? ?? o???\n" + "T??? s??ng tinh m??\n"+ "G???i ng?????i th???c gi???c?",cauhoi2))

        val cauhoi4:MutableList<Traloi1> = mutableListOf();
        cauhoi4.add(Traloi1("Con M??o",true))
        cauhoi4.add(Traloi1("Con L???n",false))
        cauhoi4.add(Traloi1("Con V???t",false))
        cauhoi4.add(Traloi1("Con Ch??",true))
        list.add(DataCauHoi1(4,"Th?????ng n???m ?????u h??\n" + "Gi??? cho nh?? ch???\n" + "Ng?????i l??? n?? s???a\n" + "Ng?????i quen n?? m???ng",cauhoi4))

        val cauhoi5:MutableList<Traloi1> = mutableListOf();
        cauhoi5.add(Traloi1("Con M??o",false))
        cauhoi5.add(Traloi1("Con H???",false))
        cauhoi5.add(Traloi1("Con Th???",true))
        cauhoi5.add(Traloi1("Con S??c",false))
        list.add(DataCauHoi1(5,"Con g?? ??u??i ng???n tai d??i\n" + "M???t h???ng l??ng m?????t\n" + "C?? t??i ch???y nhanh",cauhoi5))

        val cauhoi6:MutableList<Traloi1> = mutableListOf();
        cauhoi6.add(Traloi1("Con B??",false))
        cauhoi6.add(Traloi1("Con Tr??u",true))
        cauhoi6.add(Traloi1("Con Ngh??",false))
        cauhoi6.add(Traloi1("Con B??",false))
        list.add(DataCauHoi1(6,"Con g?? ??n c???\n" + "?????u c?? 2 s???ng\n" + "L??? m??i bu???c th???ng\n" + "K??o c??y r???t gi???i",cauhoi6))

        val cauhoi7:MutableList<Traloi1> = mutableListOf();
        cauhoi7.add(Traloi1("Con M??o",false))
        cauhoi7.add(Traloi1("Con L???n",true))
        cauhoi7.add(Traloi1("Con Tr??u",false))
        cauhoi7.add(Traloi1("Con Ch??",false))
        list.add(DataCauHoi1(7,"Con g?? ??n no\n" + "B???ng to m???t h??p\n" + "M???m k??u ???t ???t\n" + "N???m th??? ph?? ph??",cauhoi7))

        val cauhoi8:MutableList<Traloi1> = mutableListOf();
        cauhoi8.add(Traloi1("Con Tr??u",false))
        cauhoi8.add(Traloi1("Con H???",false))
        cauhoi8.add(Traloi1("Con Voi",false))
        cauhoi8.add(Traloi1("Con Ng???a",true))
        list.add(DataCauHoi1(8,"Con g?? b???n v??\n" + "Ng???c n??? b???ng thon\n" + "Rung rinh chi???c b???m\n" + "Phi nhanh nh?? gi???",cauhoi8))

        val cauhoi9:MutableList<Traloi1> = mutableListOf();
        cauhoi8.add(Traloi1("Con Cua",false))
        cauhoi8.add(Traloi1("Con R??a",false))
        cauhoi8.add(Traloi1("Con C?? ",false))
        cauhoi8.add(Traloi1("Con T??m",true))
        list.add(DataCauHoi1(9,"B???n c??y c???t d???a hai c??y ??inh s???c\n" + "M???t c??i ??ong ????a m???t c??i ng??c ngo???c" ,cauhoi9))

        val cauhoi10:MutableList<Traloi1> = mutableListOf();
        cauhoi10.add(Traloi1("Con Ki???n",true))
        cauhoi10.add(Traloi1("Con S???c",false))
        cauhoi10.add(Traloi1("Con Chu???t",false))
        cauhoi10.add(Traloi1("Con S??u",false))
        list.add(DataCauHoi1(10,"Con g?? b?? t??\n" +  "L???i ??i t???ng ????n\n" + "Ki???m ???????c m???i ngon\n" + "C??ng tha v??? t???",cauhoi10))

        val cauhoi11:MutableList<Traloi1> = mutableListOf();
        cauhoi11.add(Traloi1("Con M??o",false))
        cauhoi11.add(Traloi1("Con H???",true))
        cauhoi11.add(Traloi1("Con Th???",false))
        cauhoi11.add(Traloi1("Con S??c",false))
        list.add(DataCauHoi1(11,"Con g?? l??ng v???n m???t xanh\n" + "D??ng di uy???n chuy???n, nhe nanh t??m m???i\n"
                + "Th???, nai g???p ph???i h???i ??i\n" + "Mu??ng th?? khi???p s??? t??n ng??i ch??a r???ng?",cauhoi11))

        val cauhoi12:MutableList<Traloi1> = mutableListOf();
        cauhoi12.add(Traloi1("Con M??o",false))
        cauhoi12.add(Traloi1("Con H???",false))
        cauhoi12.add(Traloi1("Con Th???",false))
        cauhoi12.add(Traloi1("Con S??c",true))
        list.add(DataCauHoi1(12,"Chuy???n c??nh mau l???\n" +
                "C?? c??i ??u??i b??ng\n" + "H???t d??? th??ch ??n",cauhoi12))


        return list;
    }



}