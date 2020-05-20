package com.kotlin.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.kotlin.app.adapter.OtherAdapter
import kotlinx.android.synthetic.main.activity_other.*

/**
 * kotlin 基础
 */

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

        bt_ju.setOnClickListener {
            finish()
        }

        tv_title.text = "dddddddceshi "


        recyclerView.layoutManager = LinearLayoutManager(this)

        var list = listOf("2","3","4","5")
        recyclerView.adapter = OtherAdapter(list)


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