package ru.myproject.vkapp.provider

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.myproject.vkapp.R
import ru.myproject.vkapp.models.FriendsModel
import ru.myproject.vkapp.presenter.FriendsPresenter

class FriendsProvider(var presenter: FriendsPresenter) {
    fun testLoadFriends(hasFriends: Boolean) {
        MainScope().launch {
            delay(2000L)
            val friendsList: ArrayList<FriendsModel> = ArrayList()
            if (hasFriends) {
                val friend1 = FriendsModel(
                    name = "Petr",
                    surname = "Petrov",
                    city = null,
                    avatar = "https://sovpilots.ru/cards/34_pet-pl/petrov_petr_mikhailovich.jpg",
                    isOnline = true
                )
                val friend2 = FriendsModel(
                    name = "Ivan",
                    surname = "Ivanov",
                    city = "Tomsk",
                    avatar = "https://www.gravatar.com/avatar/e169a09f4d75e4f5792be2ecdbfa0940?s=256&d=identicon&r=PG",
                    isOnline =true
                )
                val friend3 = FriendsModel(
                    name = "Nicole",
                    surname = "Kinsman",
                    city = "Mayumi",
                    avatar = "https://www.film.ru/sites/default/files/styles/thumb_260x320/public/people/1455175-2115438.jpg",
                    isOnline = false
                )

                friendsList.add(friend1)
                friendsList.add(friend2)
                friendsList.add(friend3)

            }
            presenter.friendsLoaded(friendsList)
        }
    }
}