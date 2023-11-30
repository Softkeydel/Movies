package com.imdb.movies.util

import android.app.Activity
import android.content.Context
import android.content.res.AssetManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.annotation.ColorInt
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.imdb.movies.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import java.io.BufferedReader
import java.io.IOException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.TimeZone


const val ISO8601DATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"


fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}




fun ImageView.loadImage(imageUrl: String?, drawable: Int = R.drawable.img_placeholder) {
    if (imageUrl.isNullOrEmpty()) {
        this.setImageResource(drawable)
    } else {
        Glide.with(this).load(imageUrl)
            .error(drawable)
            .placeholder(drawable)
//                .skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(this)
    }
}



fun utcToLocal(date: String?): String? {
    if (date == null) return ""
    val udf = SimpleDateFormat(ISO8601DATEFORMAT)
    val ndf = SimpleDateFormat("dd MMM yy, hh:mm a")
    //        udf.setTimeZone(Calendar.getInstance().getTimeZone());
    udf.timeZone = TimeZone.getTimeZone("UTC")
    ndf.timeZone = TimeZone.getDefault()
    return try {
        val _date = udf.parse(date)
        ndf.format(_date)
    } catch (e: ParseException) {
        e.printStackTrace()
        date
    }
}

fun formatDate(date: String?): String? {
    if (date == null) return ""
    val udf = SimpleDateFormat("yyyy-mm-dd HH:mm:ss")
    val ndf = SimpleDateFormat("dd MMM yy, hh:mm a")

//    udf.timeZone = TimeZone.getTimeZone("UTC")
//    ndf.timeZone = TimeZone.getDefault()
    return try {
        val _date = udf.parse(date)
        ndf.format(_date)
    } catch (e: ParseException) {
        e.printStackTrace()
        date
    }
}



fun <T : Any> Fragment.setBackStackData(key: String, data : T, doBack : Boolean = true) {
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key, data)
    if(doBack) findNavController().popBackStack()
}

fun <T : Any> Fragment.getBackStackData(key: String, result: (T) -> (Unit)) {
    findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)?.observe(viewLifecycleOwner) {
        result(it)
    }
}


fun TabLayout.addTabIcons(icons: List<Int>) {
    if (this.tabCount != icons.size)
        throw Exception("The size of list and the tab count should be equal!")

    for (i in icons.indices) {
        this.getTabAt(i)?.setIcon(icons[i])
    }
}

fun TabLayout.setupWithViewPager(viewPager: ViewPager2, labels: List<String>) {
    if (labels.size != viewPager.adapter?.itemCount)
        throw Exception("The size of list and the tab count should be equal!")

    TabLayoutMediator(this, viewPager, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            tab.text = labels[position]
        }).attach()
}


fun Toolbar.setNavigationIconColor(@ColorInt color: Int) = navigationIcon?.mutate()?.let {
    it.setTint(color)
    this.navigationIcon = it
}






