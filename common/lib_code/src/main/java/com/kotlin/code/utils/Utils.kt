package com.kotlin.code.utils

import android.app.Activity
import android.app.Dialog
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.*
import android.support.v4.content.FileProvider
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import okhttp3.*
import java.io.*
import java.text.DecimalFormat
import java.util.*
import java.util.concurrent.Executors
import java.util.regex.Pattern


object Utils {
    /**
     * 隐藏软键盘
     *
     * @param context :上下文环境，一般为Activity实例
     * @param view    :一般为EditText
     */
    fun hideKeyboard(view: View) {
        var manager: InputMethodManager = view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager;
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    fun hideKeyboard(activity: Activity) {
        val view = activity.getCurrentFocus()
        if (null != view) {
            val inputMethodManager =
                    activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                    view!!.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    fun getFromAssets(content: Context, fileName: String): String {
        try {
            val inputReader = InputStreamReader(content.resources.assets.open(fileName))
            val bufReader = BufferedReader(inputReader)
            var line = ""
            var Result = ""
            do {
                line = bufReader.readLine()
                if (line != null) {
                    Result += line
                }
            } while (true)
//            while ((line = bufReader.readLine()) != null)
//                Result += line
            return Result
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    /**
     * 手机号判断
     */

    fun isPhoneNumber(input: String): Boolean {// 判断手机号码是否规则
        val regex = "(1[0-9][0-9]|15[0-9]|18[0-9])\\d{8}"
        val p = Pattern.compile(regex)
//        return p.matches(regex, input)//如果不是号码，则返回false，是号码则返回true
        return p.toRegex().matches(input)// regex, input)//如果不是号码，则返回false，是号码则返回true
    }

    /**
     * 邮箱判断
     */
    fun checkEmail(email: String): Boolean {
        var check =
                "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"
        var regex = Pattern.compile(check)
        var matcher = regex.matcher(email);
        var isMatched = matcher.matches();
        return !isMatched
    }

    /**
     * 过滤重复数据
     */
    fun <T> getSingle(list: ArrayList<T>): ArrayList<T> {
        var newList = ArrayList<T>();     //创建新集合
        var it = list.iterator();        //根据传入的集合(旧集合)获取迭代器
        while (it.hasNext()) {          //遍历老集合
            var obj = it.next();       //记录每一个元素
            if (!newList.contains(obj)) {      //如果新集合中不包含旧集合中的元素
                newList.add(obj);       //将元素添加
            }
        }
        return newList;
    }


    /**
     * 获取版本号
     *
     * @return
     */
    fun getVersionCode(content: Context): String {
        // 包管理器 可以获取清单文件信息
        var packageManager = content.getPackageManager();
        try {
            // 获取包信息
            // 参1 包名 参2 获取额外信息的flag 不需要的话 写0
            var packageInfo = packageManager.getPackageInfo(
                    content.getPackageName(), 0
            );
            return packageInfo.versionName;
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace();
        }
        return "";
    }

    fun setText(textView: TextView, txt: String) {
        textView.text = txt
    }

    fun getBf(num: Float): String {
        val df = DecimalFormat("0.00%")
        return df.format(num)
    }

    fun reperString(txt: String): String {
        if (txt == null) {
            return ""
        }
        if (TextUtils.isEmpty(txt)) {
            return txt
        } else {
            if (txt.contains(".0")) {
                return txt.replace(".0", "")
            } else if (txt.contains(" 00:00:00")) {
                return txt.replace(" 00:00:00", "")
            } else {
                return txt
            }
        }
    }

    fun isWeixinAvilible(context: Context): Boolean {
        val packageManager = context.packageManager // 获取packagemanager
        val pinfo =
                packageManager.getInstalledPackages(0) // 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (i in pinfo.indices) {
                val pn = pinfo[i].packageName
                if (pn == "com.tencent.mm") {
                    return true
                }
            }
        }
        return false
    }

    /**
     * 直接分享图片到微信好友
     * @param context
     * @param picFile
     */
    fun shareWechatFriend(
            context: Context,
            picFile: File?
    ) {
        if (isWeixinAvilible(context)) {
            val intent = Intent()
            val cop =
                    ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI")
            intent.component = cop
            intent.action = Intent.ACTION_SEND
            intent.type = "image/*"
            if (picFile != null) {
                if (picFile.isFile() && picFile.exists()) {
                    val uri: Uri
                    uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        FileProvider.getUriForFile(context, "com.seedling.fileProvider", picFile)
                    } else {
                        Uri.fromFile(picFile)
                    }
                    intent.putExtra(Intent.EXTRA_STREAM, uri)
                    //                    intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, Uri);
                }
            }
            //            intent.putExtra("Kdescription", !TextUtils.isEmpty(content) ? content : "");
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            // context.startActivity(intent);
            context.startActivity(Intent.createChooser(intent, "分享名片"))
        } else {
            Toast.makeText(context, "您需要安装微信客户端", Toast.LENGTH_LONG).show()
        }
    }


    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    fun callPhone(phoneNum: String) {
        var intent = Intent(Intent.ACTION_DIAL);
        var data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
//        startActivity(ActivityUtils.getTopActivity(), intent, Bundle());
    }

    //edit 不可用
    fun setEditViewNo(editText: EditText) {
        editText.isEnabled = false
        editText.isFocusable = false
        editText.isFocusableInTouchMode = false
    }

//    fun recyclerView2Top(recyclerView: RecyclerView) {
//        recyclerView?.let {
//            val delayTask = object : TimerTask() {
//                override fun run() {
//                    ThreadUtils.runOnMainThread(object : Runnable {
//                        override fun run() {
//                            recyclerView.scrollToPosition(0);
//                        }
//                    })
//                }
//            }
//            var timer = Timer()
//            timer?.schedule(delayTask, 100)
//        }
//    }

    val pattern = Pattern.compile("\\S*[?]\\S*")

    /**
     * 获取链接的后缀名
     * @return
     */
    fun parseSuffix(url: String): String? {
        val matcher = pattern.matcher(url)
        val spUrl = url.split("/").toTypedArray()
        val len = spUrl.size
        val endUrl = spUrl[len - 1]
        if (matcher.find()) {
            val spEndUrl = endUrl.split("\\?").toTypedArray()
            return spEndUrl[0].split("\\.").toTypedArray()[1]
        }
        if (endUrl.contains(".")) {
            var d = endUrl.split(".")
            var size = d.size

            if( size <= 1 ){
                return d[0]
            }else {
                return d[size - 1]
            }
        }
        return endUrl.split("\\.").toTypedArray()[1]
    }

    fun saveFileByUrl(
            sweetAlertDialog: Dialog,
            context: Context,
            uri: String?,
            fileName: String
    ) {
        val mainHandler = Handler(Looper.getMainLooper())
        val executor =
                Executors.newSingleThreadExecutor()
        executor.execute(Runnable {
            var request = Request.Builder()
                    .url(uri)
                    .build();
            var call = OkHttpClient().newCall(request);
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    mainHandler.post {
                        sweetAlertDialog.dismiss()
                        Toast.makeText(
                                context,
                                "保存失败！",
                                Toast.LENGTH_SHORT
                        ).show()

                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    // 保存文件到本地
                    saveFile(sweetAlertDialog, mainHandler, context, response, fileName)
                }

            });

        })
    }

    fun saveFile(
            sweetAlertDialog: Dialog,
            mainHandler: Handler,
            content: Context,
            response: Response,
            fileName: String
    ) { // 保存文件到本地
        val startsPoint = 0
        var `is`: InputStream? = null
        var randomAccessFile: RandomAccessFile? = null
        var bis: BufferedInputStream? = null
        val buff = ByteArray(2048)
        var len = 0
        try {
            `is` = response.body()!!.byteStream()
            bis = BufferedInputStream(`is`)
            val file = getFile(fileName)
            // 随机访问文件，可以指定断点续传的起始位置
            randomAccessFile = RandomAccessFile(file, "rwd")
            randomAccessFile.seek(startsPoint.toLong())
            while (bis.read(buff).also { len = it } != -1) {
                randomAccessFile.write(buff, 0, len)
            }

            mainHandler.post {
                sweetAlertDialog.dismiss()
                Toast.makeText(
                        content,
                        "保存成功！ 保存到" + file.absolutePath,
                        Toast.LENGTH_SHORT
                ).show()

            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()

            mainHandler.post {
                sweetAlertDialog.dismiss()
                Toast.makeText(
                        content,
                        "保存失败！",
                        Toast.LENGTH_SHORT
                ).show()

            }
        } finally {
            try {
                `is`?.close()
                bis?.close()
                randomAccessFile?.close()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getFile(fileName: String): File {
        val root = Environment.getExternalStorageDirectory().path
        return File(root, fileName)
    }

}