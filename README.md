# SplitButton
![Jit](https://img.shields.io/jitpack/v/github/kojofosu/SplitButton?style=for-the-badge&color=2F9319) 

 A dual-function menu button that offers a default action as well as the possibility of choosing a different action by selecting from a set of alternatives.


## Setup

Add it in your root `build.gradle` at the end of repositories:

```groovy
allprojects {
    repositories {
        //...omitted for brevity
        maven { url 'https://jitpack.io' }
    }
}
```



Add the dependency

```groovy
dependencies {
   implementation "com.github.kojofosu:SplitButton:$latest_release"
}
```

## :bulb: Tech Used

<img src="https://marvel-b1-cdn.bc0a.com/f00000000156946/www.jrebel.com/sites/rebel/files/image/2021-01/what%20is%20kotlin%20banner%20image.png" height="70px" width="100px"> 

## Demo

<img src="https://user-images.githubusercontent.com/20203694/135448734-082722f9-f6a2-43eb-a96c-294d7b7afcbf.gif" alt="demo"  width="300" height="300"/>
    
## Usage
Sample implementation [here](app/)

### Split Button
- Add `SplitButton` to your xml layout.

```xml
    <com.mcdev.splitbuttonlibrary.SplitButton
        android:id="@+id/split_btn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
```

### Initialize and customise split button

```kotlin
    var splitBtn: SplitButton = findViewById(R.id.split_btn)
    
    splitBtn.setTextColor(R.color.black)
    splitBtn.setIconColor(android.R.color.white)
    splitBtn.setBgColor(android.R.color.holo_orange_light)
    splitBtn.setMenuItems(R.menu.split_menu, R.style.PopMen)
```

### Button listener
```kotlin
            splitBtn.setOnButtonClickListener(object : OnButtonClickListener {
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
```

### Add menu items. Create menu list and place it in `src/main/res/menu/` directory.
```xml
    <menu xmlns:android="http://schemas.android.com/apk/res/android">
        <item
            android:id="@+id/send"
            android:icon="@drawable/ic_paper_plane"
            android:title="Send"/>
    
        <item
            android:id="@+id/sfl"
            android:icon="@drawable/ic_calendar"
            android:title="Schedule"/>
    
        <item
            android:id="@+id/draft"
            android:icon="@drawable/ic_bookmark"
            android:title="Draft"/>
    </menu>
```

### Style menu items
- To style menu items to change background color etc
```xml
    <style name="PopMen" parent="Widget.AppCompat.PopupMenu.Overflow">
        <!-- change menu text color-->
        <item name="android:textColor">@android:color/black</item> 
        <!--change popup menu background color-->
        <item name="popupMenuBackground">@android:color/holo_orange_light</item>

        <item name="android:radius">30dp</item>
        <item name="cardCornerRadius">30dp</item>
        <item name="cornerRadius">30dp</item>
    </style>
```

### Licensed under the [Apache-2.0 License](LICENSE)
