package com.mcdev.splitbuttonlibrary

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.mcdev.splitbuttonlibrary.databinding.SplitButtonBinding
import android.widget.*
import androidx.appcompat.widget.PopupMenu

import androidx.annotation.MenuRes
import java.lang.Exception

import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat

import android.os.Build
import android.view.*
import androidx.core.view.forEachIndexed
import androidx.core.view.iterator


class SplitButton @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) :
    ConstraintLayout(context, attributeSet, defStyle) {

    /*view*/
    private val binding = SplitButtonBinding.inflate(LayoutInflater.from(context), this, true)

    /*global variables*/
    private var _popupMenu: PopupMenu? = null
    private var selectedItemId: Int = 0
    private var selectedItemTitle: String? = null
    private var btnBgColor: Int = Color.parseColor("#000000")

    /*listeners*/
    private var buttonListener: OnButtonClickListener? = null

    private var arrayAdapter: ArrayAdapter<Any>? = null
    private var list : List<SplitMenu>? = null

    var text: String? = null
        private set(value) {
            binding.textBtn.text = value
            field = value
        }

    var itemColor: Int? = null
        get() = field
        set(value) {
            field = value
        }

    init {
        binding.imgBtn.setOnClickListener {
            _popupMenu?.setOnMenuItemClickListener {
                selectedItemId = it.itemId
                selectedItemTitle = it.title.toString()
                binding.textBtn.text = selectedItemTitle
                true
            }

            try {
                val popup = PopupMenu::class.java.getDeclaredField("mPopup")
                popup.isAccessible = true
                val menu = popup.get(_popupMenu)
                menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                    .invoke(menu, true)
            } catch (e: Exception) {
                Log.d("TAG", "error occurred : $e")
            }finally {
                _popupMenu?.show()
            }
        }


        binding.textBtn.setOnClickListener {
            buttonListener?.onClick(selectedItemId, selectedItemTitle)
        }
    }


    /*Text color*/
    fun setTextColor(colorString: String) {
        binding.textBtn.setTextColor(ColorStateList.valueOf(Color.parseColor(colorString)))
    }

    fun setTextColor(colorStateList: ColorStateList) {
        binding.textBtn.setTextColor(colorStateList)
    }

    fun setTextColorInt(@ColorInt colorInt: Int) {
        binding.textBtn.setTextColor(ColorStateList.valueOf(colorInt))
    }

    fun setTextColor(@ColorRes colorRes: Int) {
        binding.textBtn.setTextColor(resources.getColorStateList(colorRes, context.theme))
    }

    /*icon colors*/
    fun setIconColor(@ColorRes colorRes: Int) {
        binding.imgBtn.imageTintList = resources.getColorStateList(colorRes, context.theme)
    }

    fun setIconColor(colorString: String) {
        binding.imgBtn.imageTintList = ColorStateList.valueOf(Color.parseColor(colorString))
    }

    fun setIconColor(colorStateList: ColorStateList) {
        binding.imgBtn.imageTintList = colorStateList
    }

    fun setIconColorInt(@ColorInt colorInt: Int) {
        binding.imgBtn.imageTintList = ColorStateList.valueOf(colorInt)
    }

    /*button background colors*/
    fun setBgColor(@ColorRes colorRes: Int) {
        btnBgColor = resources.getColor(colorRes, context.theme)
        binding.sbRoot.setBackgroundColor(resources.getColor(colorRes, context.theme))
    }

    fun setBgColor(colorString: String) {
        btnBgColor = Color.parseColor(colorString)
        binding.sbRoot.setBackgroundColor(Color.parseColor(colorString))
    }

    fun setBgColorInt(@ColorInt colorInt: Int) {
        btnBgColor = colorInt
        binding.sbRoot.setBackgroundColor(colorInt)
    }


    /*menu items
    * todo update*/
    fun setMenuItems(@MenuRes menu: Int, style: Int = R.style.PopupMenuStyle) {
        itemColor = if (itemColor == null) {
            btnBgColor
        } else {
            resources.getColor(itemColor!!, context.theme)
        }
        val wrapper = ContextThemeWrapper(context, style)
        val popupMenu = PopupMenu(wrapper, binding.imgBtn)
        popupMenu.inflate(menu)
        _popupMenu = popupMenu

        popupMenu.menu.forEachIndexed { index, item ->
            if (item.icon!=null){
                popupMenu.menu.getItem(index).icon = item.icon!! // sets item icon if it is not null
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    popupMenu.menu.getItem(index).iconTintList = ColorStateList.valueOf(itemColor!!)// sets item icon color for SDK 26+
                }
            }
            val spanString = item.title.toString()
                .parseSpannable(itemColor!!) // sets spannable
            item.title =
                spanString // sets spannable to item title to be able to change item text color
        }

        text = popupMenu.menu.getItem(0).title.toString()
    }

    fun setMenuItems(splitMenuList: List<SplitMenu>,style: Int = R.style.PopupMenuStyle) {

        itemColor = if (itemColor == null) {
            btnBgColor
        } else {
            resources.getColor(itemColor!!, context.theme)
        }

        // Setting Menu Items from List
        val wrapper = ContextThemeWrapper(context, style)
        val popupMenu = PopupMenu(wrapper,binding.imgBtn)
        for (item in splitMenuList){
            // Item title set to SplitMenu tag
            popupMenu.menu.apply {
                add(Menu.NONE,item.position,Menu.NONE,item.title) //adds menu items
            }

            if (item.icon!=null){
                popupMenu.menu.getItem(item.position).setIcon(item.icon!!) // sets item icon if it is not null
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    popupMenu.menu.getItem(item.position).iconTintList = ColorStateList.valueOf(itemColor!!)// sets item icon color for SDK 26+
                }
            }
            val spanString = popupMenu.menu.getItem(item.position).title.toString().parseSpannable(itemColor!!) // sets spannable

            popupMenu.menu.getItem(item.position).title = spanString // sets spannable to item title to be able to change item text color
        }

        _popupMenu = popupMenu// assign popup menu
        text = popupMenu.menu.getItem(0).title.toString()// sets default button text to first item in list
    }

    /*listeners*/
    fun setOnButtonClickListener(listener: OnButtonClickListener) {
        buttonListener = listener
    }

    /*unused*/
    private fun setupListPopupWindow() {
        val listItems = listOf("Monday", "Saturday")
        val mPopupAdapter: ArrayAdapter<String> = ArrayAdapter(context, R.layout.layout_popup, R.id.details, listItems)

        val albumPopup = ListPopupWindow(context)

        albumPopup.width = LinearLayout.LayoutParams.MATCH_PARENT
//        albumPopup.setContentWidth(Utils.measureContentWidth(mPopupAdapter, activity))

        albumPopup.setAdapter(mPopupAdapter)
        albumPopup.height = ListPopupWindow.WRAP_CONTENT
        albumPopup.anchorView = binding.imgBtn
        albumPopup.isModal = true
        albumPopup.setDropDownGravity(Gravity.END)
        val background = ContextCompat.getDrawable(context, R.drawable.popup_bg_rounded)
        albumPopup.setBackgroundDrawable(background)
        albumPopup.show()
    }
}
