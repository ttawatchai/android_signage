package com.example.n007.signage

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
//    private var liveDataUserInfo: MutableLiveData<UserInfoDao>? = null
//
//    fun getUserInfo(username: String): LiveData<UserInfoDao> {
//        if (liveDataUserInfo == null) {
//            liveDataUserInfo = MutableLiveData()
//            providesUserInfoAPIs().getUserInfoGitHub(username)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe({
//                        if (it.isSuccessful) {
//                            liveDataUserInfo?.value = it?.body()
//                        } else {
//                            // TODO : somethings ERROR
//                        }
//                    }, {
//                        // TODO : somethings FAILURE
//                    })
//        }
//        return liveDataUserInfo as MutableLiveData<UserInfoDao>
//    }
//
//    fun providesUserInfoAPIs(): UserInfoApi = Retrofit.Builder()
//            .baseUrl("https://api.github.com/")
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(UserInfoApi::class.java)
}