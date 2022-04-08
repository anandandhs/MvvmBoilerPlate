package com.tcs.mvvmboilerplate.utils

object Validations {

    fun validateUserName(userName:String):Pair<Boolean,String>{
        if (userName.isEmpty()) {
            return Pair(false,"Username cannot be Empty!")
        }
        return Pair(true,"Nil")
    }

    fun validateKeyWord(keyWord:String):Pair<Boolean,String>{
        val pattern = Regex("^a")
        if (keyWord.isEmpty()) {
            return Pair(false,"Password can't be Empty!")
        } else if (keyWord.length<8) {
            return Pair(false,"Password can't be less than 8 Characters!")
        }
        return Pair(true,"Nil")
    }
}