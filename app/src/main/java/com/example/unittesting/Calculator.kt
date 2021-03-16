package com.example.unittesting

import java.lang.ArithmeticException

fun add(num1:Int,num2:Int):Int{
    return num1+num2
}
fun subtract(num1:Int,num2:Int):Int{
    return num1-num2
}
fun devide(num1:Int,num2:Int):Int{
    if(num2==0) throw ArithmeticException("Can not devide by zero")
    return num1/num2
}
class  Calculator {
}