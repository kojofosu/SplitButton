package com.mcdev.splitbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mcdev.splitbuttonlibrary.OnButtonClickListener
import com.mcdev.splitbuttonlibrary.SplitButton

class MainActivity : AppCompatActivity() {
    private lateinit var splitBtn: SplitButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        splitBtn = findViewById(R.id.split_btn)
        splitBtn.apply {
            text = "January"
            setTextColor(R.color.black)
            setIconColor(android.R.color.white)
            setBgColor(android.R.color.holo_orange_light)
            setMenuItems(R.menu.split_menu, R.style.PopMen)
            setOnButtonClickListener(object : OnButtonClickListener {
                override fun onClick(itemId: Int, itemTitle: String?) {
                    Log.d("TAG", "onClick: id :$itemId")
                    Log.d("TAG", "onClick: title :$itemTitle")
                    if (itemId == R.id.nat) {

                        Log.d("TAG", "onClick: nat  ")
                    }else if (itemId == R.id.stay) {

                        Log.d("TAG", "onClick: stay ")
                    } else {

                        Log.d("TAG", "onClick: beans :$itemTitle")
                    }
                }
            })
        }


    }
}