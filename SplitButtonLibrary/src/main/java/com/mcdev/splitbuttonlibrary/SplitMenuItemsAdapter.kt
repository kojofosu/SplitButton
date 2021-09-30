package com.mcdev.splitbuttonlibrary

import android.content.Context
import android.icu.text.CaseMap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class SplitMenuItemsAdapter(private val context: Context, private val arrayList: ArrayList<SplitMenu>): BaseAdapter() {
    private lateinit var itemTitle: TextView
    private lateinit var itemIcon: ImageView

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        view = LayoutInflater.from(context).inflate(R.layout.popup_item, parent, false)

        itemIcon = view!!.findViewById(R.id.pp_iv)
        itemTitle = view.findViewById(R.id.pp_tv)

        itemTitle.text = arrayList[position].item.toString()

        arrayList[position].icon?.let {
            itemIcon.setImageResource(it)
        }

        return view
    }
}