package ru.myproject.vkapp.presenter


import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vk.api.sdk.auth.VKAuthenticationResult
import ru.myproject.vkapp.views.LoginView


@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {
    fun loginVK(result: VKAuthenticationResult) {
        when (result) {
            is VKAuthenticationResult.Success -> {
                viewState.openFriends()
                        }
            is VKAuthenticationResult.Failed -> {
               viewState.showError(com.vk.sdk.api.R.string.vk_message_login_error)
            }
        }
    }
}