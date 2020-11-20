package com.poran.architecturecomponentsampleapp.utils

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast

fun Context.toast(msg:String){
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()

}
fun ProgressBar.show(){
    this.visibility= View.VISIBLE
}
fun ProgressBar.hide(){
    this.visibility=View.GONE
}