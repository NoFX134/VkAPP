package ru.myproject.vkapp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import java.lang.Error

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface LoginView : MvpView {
    fun startLoading()
    fun endLoading()
    fun showError(errorResource:Int)
    fun openFriends()
}