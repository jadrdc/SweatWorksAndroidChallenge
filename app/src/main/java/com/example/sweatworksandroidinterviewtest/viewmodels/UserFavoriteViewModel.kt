package com.example.sweatworksandroidinterviewtest.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.sweatworksandroidinterviewtest.interactors.UserInteractor
import com.example.sweatworksandroidinterviewtest.model.entity.UserFavorite
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserFavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private var disposables = CompositeDisposable()
    private var interactor = UserInteractor(application)
    private var isSavingUser = MutableLiveData<Boolean>()
    private var userFavoriteList = MutableLiveData<List<UserFavorite>>()

    fun saveFavoriteUser(userFavorite: UserFavorite) {
        interactor?.saveFavoriteUser(userFavorite)
             .subscribe {
            isSavingUser.postValue(false)
        }?.let {
            disposables.add(it)
        }
    }

    fun getUserFavoriteList() {
        interactor?.getFavoriteUsers()
                ?.subscribe { list ->
                    userFavoriteList.postValue(list)
                }?.let { disposables.add(it) }

    }
    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}