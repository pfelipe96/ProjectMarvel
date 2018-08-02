package com.example.paulofelipeoliveirasouza.marvelproject

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class Constants{
    companion object {
        @JvmStatic
        val API_KEY_PUBLIC = "3e616b2f82f2ba22d71e96aba9c54f2f"

        @JvmStatic
        private val API_KEY_PRIVATE = "0bbac4c7765f3d15170d5cda7c24edda6f969978"

        @JvmStatic
        val TS = (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()

        @JvmStatic
        val HASH = "$TS$API_KEY_PRIVATE$API_KEY_PUBLIC".md5()

        @JvmStatic
        fun String.md5(): String {
            val md = MessageDigest.getInstance("MD5")
            val digested = md.digest(toByteArray())
            return digested.joinToString("") {
                String.format("%02x", it)
            }
        }

        class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int, private val includeEdge: Boolean) : RecyclerView.ItemDecoration() {

            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                val position = parent.getChildAdapterPosition(view) // item position
                val column = position % spanCount // item column

                if (includeEdge) {
                    outRect.left = spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
                    outRect.right = (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)

                    if (position < spanCount) { // top edge
                        outRect.top = spacing
                    }
                    outRect.bottom = spacing // item bottom
                } else {
                    outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
                    outRect.right = spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                    if (position >= spanCount) {
                        outRect.top = spacing // item top
                    }
                }
            }
        }
    }
}