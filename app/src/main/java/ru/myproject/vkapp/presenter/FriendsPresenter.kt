package ru.myproject.vkapp.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.myproject.vkapp.R
import ru.myproject.vkapp.models.FriendsModel
import ru.myproject.vkapp.provider.FriendsProvider
import ru.myproject.vkapp.views.FriendsView
import java.lang.Error


@InjectViewState
class FriendsPresenter : MvpPresenter<FriendsView>() {
    fun loadFriends() {
        viewState.startLoading()
        FriendsProvider(this).loadFriends()
    }

    fun friendsLoaded(friendsList: ArrayList<FriendsModel>) {
        viewState.endLoading()
        if (friendsList.size == 0) {
            viewState.setupEmptyList()
            viewState.showError(R.string.friends_no_item)
        } else {
            viewState.setupFriendsList(friendsList)
        }
    }
    fun showError(errorResource: Int){
        viewState.showError(errorResource)
    }
}