package com.kotlin.demo.net.retrofit


import androidx.lifecycle.MutableLiveData
import com.kotlin.demo.net.retrofit.bean.BaseResponse

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @author Laizexin on 2019/12/2
 * @description
 */
class BaseObserver<T : BaseResponse<*>>(
    private val liveData: MutableLiveData<T>,
    private val repository: BaseRepository?
) : Observer<T> {

    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
//        repository.subscribe(d)
    }

    override fun onNext(response: T) {
        when(response.errorCode){
            0 -> {
                if(response.data is List<*>){
                    if((response.data as List<*>).isEmpty()){
                        return
                    }
                }
                liveData.postValue(response)
            }
            -1001 ->{
//                UserContext.instance.logoutSuccess()
//                loadState.postValue(State(StateType.ERROR,msg = "登录过期"))
            }
            else -> {
//                loadState.postValue(State(StateType.ERROR,msg = response.errorMsg))
            }
        }
    }

    override fun onError(e: Throwable) {
//        if(BuildConfig.DEBUG){
//            e.message?.let { Logger.e(it) }
//        }
//        loadState.postValue(State(StateType.NETWORK_ERROR))
    }


}