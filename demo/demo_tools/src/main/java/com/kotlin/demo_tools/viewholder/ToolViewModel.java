package com.kotlin.demo_tools.viewholder;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kotlin.demo_tools.adapter.ToolsAdapter;

import java.util.ArrayList;
import java.util.List;

public class ToolViewModel extends ViewModel {
    MutableLiveData<List<String>> list = new MutableLiveData();

    public MutableLiveData<List<String>> getList() {
        return list;
    }

    public void refreshData(){
        ArrayList lists = new ArrayList();
        lists.add("ddddd23dd");
        lists.add("ddddd23dd");
        lists.add("ddd221dddd");
        lists.add("ddd2dddd");
        lists.add("ddd122dddd");
        lists.add("ddd12dddd");
        lists.add("ddd1dddd");
        lists.add("dedddddd");
        lists.add("ddddddd");
        lists.add("ddt43ddddd");
        lists.add("ddd23dddd");
        lists.add("dddddd23d");
        lists.add("dd2233ddddd");
        list.setValue(lists);
    }
}
