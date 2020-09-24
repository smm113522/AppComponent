## 基础知识



## 进阶基础


## 其他尝试

一般是扩展使用



```kotlin
fun View.Gone() {
    this.visibility = View.GONE
}

fun View.Visible() {
    this.visibility = View.VISIBLE
}

fun TextView.setTextColorCompat(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        this.setTextColor(App.application.resources.getColor(color, App.application.theme))
    } else {
        this.setTextColor(App.application.resources.getColor(color))
    }
}


fun Activity.hideKeyboard() {
    val inputMethodManager =
        getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(
        currentFocus?.getWindowToken(),
        InputMethodManager.HIDE_NOT_ALWAYS
    )
}

fun getPath(): String {
    var path = Environment.getExternalStorageDirectory().toString() + "/Luban/image/";
    var file = File(path);
    if (file.mkdirs()) {
        return path;
    }
    return path;
}

fun Lubans(context: Context, photos: String, onCompressListener: OnCompressListener) {
    Luban.with(context)
        .load(photos)
        .ignoreBy(100)
        .setTargetDir(getPath())
        .filter({ path ->
            !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(
                ".gif"
            ))
        })
        .setCompressListener(onCompressListener).launch()
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

/**
 * 中文 + 字母
 */
var typeFilterEnAndCh = InputFilter { source, start, end, dest, dstart, dend ->
    val p: Pattern = Pattern.compile("[a-zA-Z|\u4e00-\u9fa5]+")
    val m: Matcher = p.matcher(source.toString())
    if (!m.matches()) "" else null
}
```

对于?和！的使用

adapter 中如果用java 多谢viewholder

用kotlin 多写View 然后基础lineaylayout 部分。可以免去findbyid功能



```kotlin
class ItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {


    init {
        View.inflate(context, R.layout.item_child, this)
    }
}
```



实体类

```kotlin
@Parcelize
data class Author(
        @SerializedName("id") val id: Int? = 0,
        @SerializedName("icon") val icon: String? = "",
        @SerializedName("name") val name: String? = "",
        @SerializedName("description") val description: String? = "",
        @SerializedName("link") val link: String? = "",
        @SerializedName("latestReleaseTime") val latestReleaseTime: Long? = 0,
        @SerializedName("videoNum") val videoNum: Int? = 0,
        @SerializedName("follow") val follow: Follow? = Follow(),
        @SerializedName("shield") val shield: Shield? = Shield(),
        @SerializedName("approvedNotReadyVideoCount") val approvedNotReadyVideoCount: Int? = 0,
        @SerializedName("ifPgc") val ifPgc: Boolean? = false
) : Parcelable
```

 liveData {
            while (true) {
            // 发送
                emit(System.currentTimeMillis())
                // 延时
                delay(1000)
            }
        }
 emitSource(dataSource.fetchWeather())
           private val viewmodel: LiveDataViewModel by viewModels { LiveDataVMFactory }

viewModelScope.launch {
            dataSource.fetchNewData()
        }
lifecycleScope.launch {
            // Run
        }