/*
 * Tencent is pleased to support the open source community by making QMUI_Android available.
 *
 * Copyright (C) 2017-2018 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the MIT License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://opensource.org/licenses/MIT
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kotlin.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.btmv.module_main.R;
import com.code.utils.RouterPath;


/**
 * @author cginechen
 * @date 2016-10-20
 */

public class HomeComponentsController extends BaseController {


    @Override
    protected String getTitle() {
        return "Home";
    }

    private HomeControlListener mHomeControlListener;

    public HomeComponentsController(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.activity_base_controller, this);

        initTopBar();
    }

    protected void startFragment(Fragment fragment) {
        if (mHomeControlListener != null) {
            mHomeControlListener.startFragment(fragment);
        }
    }

    public void setHomeControlListener(HomeControlListener homeControlListener) {
        mHomeControlListener = homeControlListener;
    }

    private void initTopBar() {


    }



}
