package com.kotlin.main.adapter

import com.kotlin.code.adapter.BaseDBRVAdapter
import com.kotlin.main.BR
import com.kotlin.main.R
import com.kotlin.main.bean.MainHome
import com.kotlin.main.databinding.ItemMainBinding

class MainListAdapter : BaseDBRVAdapter<MainHome?, ItemMainBinding?>(R.layout.item_main, BR.bean) {

}
