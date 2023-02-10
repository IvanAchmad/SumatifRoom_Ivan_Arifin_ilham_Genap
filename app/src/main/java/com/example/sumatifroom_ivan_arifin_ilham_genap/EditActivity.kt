package com.example.sumatifroom_ivan_arifin_ilham_genap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.sumatifroom_ivan_arifin_ilham_genap.Room.Constant
import com.example.sumatifroom_ivan_arifin_ilham_genap.Room.codepelita
import com.example.sumatifroom_ivan_arifin_ilham_genap.Room.tb_barang
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity: AppCompatActivity() {

    private val db by lazy { codepelita(this) }
    private var barangid: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setuplistener()
        setupView()
        barangid = intent.getIntExtra("intent_id",0)

    }

    fun setupView(){
        val intentType = intent.getIntExtra("intent_type",0)
        when (intentType){
            Constant.TYPE_CREATE -> {
                BtnUpdate.visibility = View.GONE
            }
            Constant.TYPE_READ-> {
                BtnSimpan.visibility = View.GONE
                BtnUpdate.visibility = View.GONE
                ETid.visibility = View.GONE
                getbarang()
            }
            Constant.TYPE_UPDATE->{
                BtnSimpan.visibility = View.GONE
                ETid.visibility = View.GONE
                getbarang()
            }

        }
    }

    //2
    fun setuplistener () {
        BtnSimpan.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
               db.tbbarDao().addtb_barang(
                    tb_barang(
                        ETid.text.toString().toInt(),
                        ETnamabarang.text.toString(),
                        ETharga.text.toString().toInt(),
                        ETQTY.text.toString().toInt()

                    )
                )
                finish()
            }
        }
        //menambah perintah tombol update3
        BtnUpdate.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
                db.tbbarDao().updatebarang(
                    tb_barang(barangid,
                        ETnamabarang.text.toString(),
                        ETharga.text.toString().toInt(),
                        ETQTY.text.toString().toInt()
                    )
                )
                finish()
            }
        }
    }

    fun getbarang(){
        barangid = intent.getIntExtra("intent_id",0)
        CoroutineScope(Dispatchers.IO).launch{
            val barangs = db.tbbarDao().tampilsemua(barangid).get(0)
            val id : String = barangs.id.toString()
            val harga : String = barangs.harga.toString()
            val QTY : String = barangs.QTY.toString()
            ETid.setText(id)
          ETnamabarang.setText(barangs.namabarang)
            ETharga.setText(harga)
            ETQTY.setText(QTY)

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}