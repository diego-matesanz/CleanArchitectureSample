package com.diego.matesanz.cleanarchitecturesample.app.presentation

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.diego.matesanz.cleanarchitecturesample.R
import java.util.Date

object LocationBindingAdapters {

    @BindingAdapter("latitude", "longitude")
    @JvmStatic
    fun setLocationCoordinates(textView: TextView, latitude: Float, longitude: Float) {
        textView.text = textView.context.getString(R.string.location_coordinates, latitude, longitude)
    }

    @BindingAdapter("date")
    @JvmStatic
    fun setLocationDate(textView: TextView, date: Date) {
        textView.text = textView.context.getString(R.string.location_date, date.toString())
    }
}