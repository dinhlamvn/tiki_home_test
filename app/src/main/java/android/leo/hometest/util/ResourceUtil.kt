package android.leo.hometest.util

import android.content.Context
import android.graphics.Color
import android.leo.hometest.R

class ResourceUtil {

    companion object {
        fun getRandomMaterialColor(context: Context) : Int {
            val color : Int
            val colors = context.resources.obtainTypedArray(R.array.material_color_array)
            val index = (Math.random() * colors.length()).toInt()
            color = colors.getColor(index, Color.BLUE)
            colors.recycle()
            return color
        }
    }
}