## 基础知识
一、HashMap和Hashtable区别？

这个一定要去看源码！看源码！看源码！实在看不下去的可以上网看别人的分析。简单总结有几点：

1.HashMap支持null Key和null Value；Hashtable不允许。这是因为HashMap对null进行了特殊处理，将null的hashCode值定为了0，从而将其存放在哈希表的第0个bucket。

2.HashMap是非线程安全，HashMap实现线程安全方法为Map map = Collections.synchronziedMap(new HashMap())；Hashtable是线程安全

3.HashMap默认长度是16，扩容是原先的2倍；Hashtable默认长度是11，扩容是原先的2n+1

4.HashMap继承AbstractMap；Hashtable继承了Dictionary

扩展，HashMap 对比 ConcurrentHashMap ，HashMap 对比 SparseArray，LinkedArray对比ArrayList，ArrayList对比Vector

ViewRoot
-> performTraversal()
-> performMeasure()
-> performLayout()
-> perfromDraw()
-> View/ViewGroup measure()
-> View/ViewGroup onMeasure()
-> View/ViewGroup layout()
-> View/ViewGroup onLayout()
-> View/ViewGroup draw()
-> View/ViewGroup onDraw()
看下invalidate方法，有带4个参数的，和不带参数有什么区别；requestLayout触发measure和layout，如何实现局部重新测量，避免全局重新测量问题。

三、事件分发机制

-> dispatchTouchEvent()
-> onInterceptTouchEvent()
-> onTouchEvent()
requestDisallowInterceptTouchEvent(boolean)
还有onTouchEvent()、onTouchListener、onClickListener的先后顺序

作者：Android高级开发
链接：https://www.jianshu.com/p/5176b6008292
来源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

## 进阶基础
PagedLi
class UserAdapter : PagedListAdapter<User, UserAdapter.ViewHolder>(UserDiffCallback()) {
    private lateinit var onItemClick: (user: User) -> Unit
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position) ?: return
        holder.itemView.userTv.text = data.name
        holder.itemView.setOnClickListener { onItemClick(data) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    infix fun setOnItemClick(onClick: (user: User) -> Unit) {
        this.onItemClick = onClick
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)
}

private class UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}


fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProviders.of(this, ViewModelFactory.getInstance()).get(viewModelClass)
        
## 其他尝试

https://github.com/marquisXuan/util
http://hutool.mydoc.io/
