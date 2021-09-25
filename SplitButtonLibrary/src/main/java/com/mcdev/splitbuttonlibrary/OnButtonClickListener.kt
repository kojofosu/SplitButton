package com.mcdev.splitbuttonlibrary

interface OnButtonClickListener {
    fun onClick(itemId: Int, itemTitle: String? = null)
}