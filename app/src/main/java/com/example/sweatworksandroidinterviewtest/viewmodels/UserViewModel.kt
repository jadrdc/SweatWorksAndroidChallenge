package com.example.sweatworksandroidinterviewtest.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.sweatworksandroidinterviewtest.interactors.UserInteractor
import com.example.sweatworksandroidinterviewtest.model.entity.UserFavorite
import com.example.sweatworksandroidinterviewtest.model.response.User
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableMaybeObserver

class UserViewModel(application: Application) : AndroidViewModel(application) {
    var userList = MutableLiveData<List<User>>()
    private var disposables = CompositeDisposable()
    private var interactor = UserInteractor(application)
    var searchList = MutableLiveData<List<String>>()
    var userFavoriteList = MutableLiveData<List<UserFavorite>>()
    var isSavingUser = MutableLiveData<Boolean>()
    var isDeletedUser = MutableLiveData<Boolean>()
    var isDuplicatedUser = MutableLiveData<Boolean>()
    fun setIsDeleteUser() {
        isDeletedUser.postValue(false)

    }

    fun saveFavoriteUser(userFavorite: UserFavorite) {
        var disposable = interactor.getUser(name = userFavorite.name)
            .subscribeWith(object : DisposableMaybeObserver<UserFavorite>() {
                override fun onSuccess(t: UserFavorite) {
                    isDuplicatedUser.postValue(true)

                }

                override fun onComplete() {
                    interactor.saveFavoriteUser(userFavorite)
                        .subscribe {
                            isSavingUser.postValue(false)
                        }.let {
                            disposables.add(it)
                        }
                }

                override fun onError(e: Throwable) {
                }

            })
        disposable?.let {
            disposables.add(it)
        }

    }

    fun getUserFavoriteList() {
        var disposable = interactor.getFavoriteUsers()
            ?.subscribe { list ->
                userFavoriteList.postValue(list)
            }
        disposable?.let {
            disposables.add(it)
        }

    }

    fun deleteUser(userFavorite: UserFavorite) {
        var disposable = interactor.deleteUser(userFavorite).subscribe {
            getUserFavoriteList()
        }
        disposable.let {
            disposables.add(it)
        }

    }

    fun getUserList(page: Int) {
        var disposable = interactor.getUsers(page)?.subscribe { it ->
            userList.postValue(it.results)
            var wordsToSearch = mutableListOf<String>()
            it.results.forEach { user ->
                user.name.first?.let { it1 -> wordsToSearch.add(it1) }
                user.name.last?.let { it1 -> wordsToSearch.add(it1) }
                user.name.title?.let { it1 -> wordsToSearch.add(it1) }
            }
            searchList.value?.let { words ->
                var oldWords = words
                oldWords.let { list ->
                    wordsToSearch.addAll(list)
                }
            }
            searchList.postValue(wordsToSearch)
        }

        disposable?.let {
            disposables.add(it)
        }


    }


    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}