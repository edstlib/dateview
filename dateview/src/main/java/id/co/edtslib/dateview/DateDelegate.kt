package id.co.edtslib.dateview

import java.util.*

interface DateDelegate {
    fun onChanged(date: Date)
}