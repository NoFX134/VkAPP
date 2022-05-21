package ru.myproject.vkapp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.myproject.vkapp.models.FriendsModel

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface FriendsView:MvpView {
    fun showError(errorResource: Int)
    fun setupEmptyList()
    fun setupFriendsList(friendsList: ArrayList<FriendsModel>)
    fun startLoading()
    fun endLoading()
}