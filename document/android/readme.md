## 基础知识



## 进阶基础
bugly 文档
https://blog.csdn.net/skyunicorn/article/details/77905736

## 其他尝试

assets 中
从里面复制到sd卡中
var sdCardDir = Environment.getExternalStorageDirectory();//获取SDCard目录
var file = File(sdCardDir, "test1.torrent")

AssetFile(this.context).fromAsset("test1.torrent").copyAssetsFileToAppFiles(file)

从里面获取json数据
String json = ConvertUtils.toString(content.getAssets().open("area.json"));


okhttp 的使用
//    val okHttpClient: OkHttpClient by lazy {
//        OkHttpClient()
//    }
            //            val request: Request = Request.Builder()
//                    .url(murl)
//                    .build()
//            val call: Call = okHttpClient.newCall(request)
//            call.enqueue(callback)


    var callback = object : Callback {

        override fun onFailure(call: Call, e: IOException) {
            Toast.makeText(this@DemoActivity, "获取失败", Toast.LENGTH_SHORT).show()
        }

        override fun onResponse(call: Call, response: Response) {
            var fileName = hello.getHeaderFileName(murl, response)
            val startsPoint = 0
            // 保存文件到本地
            var `is`: InputStream? = null
            var randomAccessFile: RandomAccessFile? = null
            var bis: BufferedInputStream? = null

            val buff = ByteArray(2048)
            var len = 0
            var downloadLength: Int = 0
            try {
                var total = response.body()!!.contentLength()
                `is` = response.body()!!.byteStream()
                bis = BufferedInputStream(`is`)
                val file: File = getFile(fileName)
                // 随机访问文件，可以指定断点续传的起始位置
                randomAccessFile = RandomAccessFile(file, "rwd")
                randomAccessFile.seek(startsPoint.toLong())
                while (bis.read(buff).also { len = it } != -1) {
                    randomAccessFile.write(buff, 0, len)

                    downloadLength += len
                    println(downloadLength)
                    println(total)
                    var progress = (downloadLength * 1.0f / total * 100)
                    println(progress)
                    Log.e(TORRENT, "progress=$progress")
                    progressBar.progress = progress.toInt()
                }
                Toast.makeText(this@DemoActivity, "下载完成,地址-》" + file.absolutePath, Toast.LENGTH_SHORT).show()
                progressBar.progress = 0
                // 下载完成
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                try {
                    `is`?.close()
                    bis?.close()
                    randomAccessFile?.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    }
    
    
    https://github.com/yoyoyaobin/PreventKeyboardBlockUtil

应用列表 | fir.im 中api 版本查询，然后更新
http://api.bq04.com/apps/latest/com.seedling?id=5e12f2e0f945482c6d15cfe2&type=android&api_token=278e69d6bb804ae7dd190926750e86b7

livedata
## mvvm

 BindFragment : Fragment(R.layout.fragment_blank) {

    // Scoped to the lifecycle of the fragment's view (between onCreateView and onDestroyView)
    private var fragmentBlankBinding: FragmentBlankBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentBlankBinding.bind(view)
        fragmentBlankBinding = binding
        binding.textViewFragment.text = getString(string.hello_from_vb_bindfragment)
    }

    override fun onDestroyView() {
        // Consider not storing the binding instance in a field, if not needed.
        fragmentBlankBinding = null
        super.onDestroyView()
    }

     // Scoped to the lifecycle of the fragment's view (between onCreateView and onDestroyView)
        private var fragmentBlankBinding: FragmentBlankBinding? = null

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            val binding = FragmentBlankBinding.inflate(inflater, container, false)
            fragmentBlankBinding = binding
            binding.textViewFragment.text = getString(R.string.hello_from_vb_inflatefragment)
            return binding.root
        }

        override fun onDestroyView() {
            // Consider not storing the binding instance in a field, if not needed.
            fragmentBlankBinding = null
            super.onDestroyView()
        }

  binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


 ##

 import java.util.concurrent.Executors

 private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

 /**
  * Utility method to run blocks on a dedicated background thread, used for io/database work.
  */
 fun ioThread(f : () -> Unit) {
     IO_EXECUTOR.execute(f)
 }


#