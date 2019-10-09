package com.kotlin.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.code.utils.RouterPath

/**
 * kotlin 基础
 */

@Route(path = RouterPath.path_kotlin_activity)
class OthersActivity : AppCompatActivity() {

    //val/var 变量名: 变量类型 = 表达式
    var a = 1
    var c: Long = 2
    var d: Double = 3.0
    var e: Float = 0.0f
    var f: Boolean = false
    var g = arrayOf("222", "21", "33")
    var h = ArrayList<String>()

    val j = intArrayOf(1, 0, 0, 8, 6)                    //产生由1, 0, 0, 8, 6构成的基本数据类型数组
    val k = intArrayOf(*j, 1, 1)                         //*是展开运算符，产生由1, 0, 0, 8, 6, 1, 1构成的基本数据类型数组
    val m = DoubleArray(8)                                  //产生8个0.0构成的基本数据类型数组
    val l = CharArray(26) { (it + 65).toChar() }            //产生26个大写字母构成的基本数据类型数组

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)
//        var a: String? = "abc"
//        println(a!!.length)     //输出3
//        val b: String? = null
//        b!!                     //抛出空指针异常


    }

    fun getName(id: Int, name: String) = {
        "yes"
    }

    fun getName1(id: Int, name: String) = "yes"

    companion object {
        // b 不可以修改
        const val b = 2
    }

}