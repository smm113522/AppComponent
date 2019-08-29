package com.replugin.host;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PluginFragment extends Fragment {

    public PluginFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


//        return inflater.inflate(R.layout.activity_plugin_fragment,container,false);
        return LayoutInflater.from(getActivity()).inflate(R.layout.activity_plugin_fragment,container,false);
    }
}
