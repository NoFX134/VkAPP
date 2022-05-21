package ru.myproject.vkapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthenticationResult
import com.vk.api.sdk.auth.VKScope
import ru.myproject.vkapp.databinding.ActivityMainBinding
import ru.myproject.vkapp.presenter.LoginPresenter
import ru.myproject.vkapp.utils.VkApplication
import ru.myproject.vkapp.views.LoginView


class LoginActivity : MvpAppCompatActivity(), LoginView {


    @InjectPresenter
    lateinit var loginPresenter: LoginPresenter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        val authLauncher = VK.login(this@LoginActivity) { result: VKAuthenticationResult ->
            loginPresenter.loginVK(result)
        }

        binding.btnLogin.setOnClickListener {
            authLauncher.launch(arrayListOf(VKScope.FRIENDS, VKScope.PHOTOS))
        }

    }

    override fun startLoading() {
        binding.btnLogin.visibility = View.GONE
        binding.pbLoading.visibility = View.VISIBLE
    }

    override fun endLoading() {
        binding.btnLogin.visibility = View.VISIBLE
        binding.pbLoading.visibility = View.GONE
    }

    override fun showError(errorResource:Int) {
        Toast.makeText(applicationContext, getString(errorResource), Toast.LENGTH_SHORT).show()
    }

    override fun openFriends() {
        val intent = Intent(applicationContext, FriendsActivity::class.java)
        startActivity(intent)
    }
}