package ru.myproject.vkapp.presenter


import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vk.api.sdk.auth.VKAuthenticationResult
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.myproject.vkapp.R
import ru.myproject.vkapp.views.LoginView


@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {
    fun login(isSuccess: Boolean) {
        viewState.startLoading()
        MainScope().launch {
            delay(1000L)
            viewState.endLoading()
            if (isSuccess) {
                viewState.openFriends()
            } else {
                viewState.showError(R.string.loading_error)
            }
        }
    }

    fun loginVK(result: VKAuthenticationResult) {
        when (result) {
            is VKAuthenticationResult.Success -> {
                viewState.openFriends()
                        }
            is VKAuthenticationResult.Failed -> {
               viewState.showError(R.string.login_enter)
            }
        }
    }
}