package com.example.buynow.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buynow.presentation.adapter.CarDItemClickAdapter
import com.example.buynow.presentation.adapter.CardAdapter
import com.example.buynow.R
import com.example.buynow.data.local.room.Card.CardEntity
import com.example.buynow.data.local.room.Card.CardViewModel
import com.example.buynow.databinding.CardAddBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PaymentMethodActivity : AppCompatActivity(), CarDItemClickAdapter {

    lateinit var cardRec: RecyclerView
    lateinit var cardAdapter: CardAdapter


    lateinit var bottomSheetDialod: BottomSheetDialog
    lateinit var bottomSheetView: View
    private lateinit var cardViewModel: CardViewModel

    lateinit var Item: ArrayList<CardEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_method)

        cardRec = findViewById(R.id.cardRecView_paymentMethodPage)
        val backIv_PaymentMethodsPage = findViewById<ImageView>(R.id.backIv_PaymentMethodsPage)
        val addCard_PaymentMethodPage =
            findViewById<FloatingActionButton>(R.id.addCard_PaymentMethodPage)

        Item = arrayListOf()
        cardViewModel = ViewModelProviders.of(this).get(CardViewModel::class.java)

        //getRecData()
        cardRec.layoutManager = LinearLayoutManager(this)
        cardAdapter = CardAdapter(this, this)
        cardRec.adapter = cardAdapter



        backIv_PaymentMethodsPage.setOnClickListener {
            onBackPressed()
        }

        bottomSheetDialod = BottomSheetDialog(
            this, R.style.BottomSheetDialogTheme
        )

        val cardAddBinding: CardAddBottomSheetBinding =
            CardAddBottomSheetBinding.inflate(LayoutInflater.from(this))

        bottomSheetView = cardAddBinding.root



    }




    override fun onItemDeleteClick(cardEntity: CardEntity) {
        cardViewModel.deleteCart(cardEntity)
        Toast.makeText(this, "Card Removed", Toast.LENGTH_SHORT).show()
    }

    override fun onItemUpdateClick(cardEntity: CardEntity) {
        cardViewModel.updateCart(cardEntity)
    }
}

