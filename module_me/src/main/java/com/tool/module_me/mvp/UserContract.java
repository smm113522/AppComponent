package com.tool.module_me.mvp;

import com.tool.module_me.model.User;

import java.util.List;

import androidx.annotation.NonNull;

public interface UserContract {
    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showTasks(List<User> tasks);

        void showAddTask();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void addNewTask();
    }
}
