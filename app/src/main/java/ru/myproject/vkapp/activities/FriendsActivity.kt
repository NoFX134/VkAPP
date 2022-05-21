package ru.myproject.vkapp.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.myproject.vkapp.adapters.FriendsAdapter
import ru.myproject.vkapp.databinding.ActivityFriendsBinding
import ru.myproject.vkapp.models.FriendsModel
import ru.myproject.vkapp.presenter.FriendsPresenter
import ru.myproject.vkapp.views.FriendsView


class FriendsActivity : MvpAppCompatActivity(), FriendsView {

    @InjectPresenter
    lateinit var friendsPresenter: FriendsPresenter
    private lateinit var binding: ActivityFriendsBinding
    private lateinit var friendsAdapter: FriendsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFriendsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.edFriendsQuery.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                friendsAdapter.filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        friendsPresenter.loadFriends()
        friendsAdapter = FriendsAdapter()
        binding.rvFriends.apply {
            adapter = friendsAdapter
            layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
        }
    }
    override fun showError(errorResource: Int) {
        binding.tvError.text = getString(errorResource)
    }

    override fun setupEmptyList() {
        binding.rvFriends.visibility = View.GONE
        binding.tvError.visibility = View.VISIBLE
    }

    override fun setupFriendsList(friendsList: ArrayList<FriendsModel>) {
        binding.rvFriends.visibility = View.VISIBLE
        binding.tvError.visibility = View.GONE
        friendsAdapter.setupFriends(friendsList)
    }

    override fun startLoading() {
        binding.rvFriends.visibility = View.GONE
        binding.pbLoading.visibility = View.VISIBLE

    }

    override fun endLoading() {
        binding.pbLoading.visibility = View.GONE
    }
}
