package com.mcdev.splitbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.mcdev.splitbuttonlibrary.OnButtonClickListener
import com.mcdev.splitbuttonlibrary.SplitButton
import com.mcdev.splitbuttonlibrary.SplitMenu

class MainActivity : AppCompatActivity() {
    private lateinit var splitBtn: SplitButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        splitBtn = findViewById(R.id.split_btn)
        splitBtn.apply {
            setTextColor(R.color.white)
            setIconColor(android.R.color.white)
            setBgColor(android.R.color.holo_red_dark)
//            itemColor = android.R.color.holo_blue_dark
            setMenuItems(R.menu.split_menu)
//            setMenuItems(listOf(SplitMenu(0, "Merge", R.drawable.ic_calendar), SplitMenu(1, "Rebase", R.drawable.ic_bookmark)))
            setOnButtonClickListener(object : OnButtonClickListener {
                override fun onClick(itemId: Int, itemTitle: String?) {
                    Log.d("TAG", "onClick: id :$itemId")
                    Log.d("TAG", "onClick: title :$itemTitle")
                    if (itemId == R.id.send) {

                        Toast.makeText(this@MainActivity, "Send", Toast.LENGTH_SHORT).show()
                        Log.d("TAG", "onClick: send  ")
                    }else if (itemId == R.id.sfl) {

                        Toast.makeText(this@MainActivity, "Save for later", Toast.LENGTH_SHORT).show()
                        Log.d("TAG", "onClick: bookmark ")
                    } else if (itemId == R.id.draft) {
                        Toast.makeText(this@MainActivity, "Draft", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }


    }
}