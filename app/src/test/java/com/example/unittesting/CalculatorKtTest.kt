package com.example.unittesting



import org.junit.Assert
import org.junit.Test
import java.lang.ArithmeticException


class CalculatorKtTest {

    @Test
    fun `add() with pos input()`(){
        val n1=200;
        val n2=300;

        val result=add(n1,n2);

        Assert.assertEquals(500,result)
    }

    @Test
    fun `subtract() with pos input()`(){
        val n1=200;
        val n2=300;

        val result=subtract(n1,n2);

        Assert.assertEquals(-100,result)
    }
    @Test
    fun `devide() with valid input()`(){
        val n1=200;
        val n2=300;

        val result=devide(n1,n2);

        Assert.assertEquals(0,result)
    }
    @Test(expected = ArithmeticException::class)
    fun `devide() with 0 dominirator input()`(){
        val n1=200;
        val n2=0;

        val result=devide(n1,n2);

        Assert.assertEquals(0,result)
    }


}