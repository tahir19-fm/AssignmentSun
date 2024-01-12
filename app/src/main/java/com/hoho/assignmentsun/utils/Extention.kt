package com.hoho.assignmentsun.utils

import android.content.Context
import android.widget.Toast

fun Context.showToast(message:String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
fun Context.showToast(messageId:Int){
    Toast.makeText(this, this.getString(messageId), Toast.LENGTH_SHORT).show()
}