package ru.myproject.vkapp.provider

import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.sdk.api.base.dto.BaseBoolInt
import com.vk.sdk.api.friends.FriendsService
import com.vk.sdk.api.friends.dto.FriendsGetFieldsResponse
import com.vk.sdk.api.users.dto.UsersFields
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
                    isOnline = true
                )
                val friend3 = FriendsModel(
                    name = "Nicole",
                    surname = "Kidman",
                    city = "Mayami",
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

    fun loadFriends() {
        val userFields: List<UsersFields> =
            listOf(
                UsersFields.SEX,
                UsersFields.BDATE,
                UsersFields.CITY,
                UsersFields.COUNTRY,
                UsersFields.PHOTO_200_ORIG,
                UsersFields.ONLINE,
                UsersFields.HAS_MOBILE
            )

        VK.execute(FriendsService().friendsGet(fields = userFields),
            object : VKApiCallback<FriendsGetFieldsResponse> {
                override fun fail(error: Exception) {
                    presenter.showError(R.string.friends_error_loading)
                }

                override fun success(result: FriendsGetFieldsResponse) {
                    val friendsList: ArrayList<FriendsModel> = ArrayList()
                    result.items.forEach {
                        val friends = FriendsModel(
                            name = it.firstName ?: "",
                            surname = it.lastName ?: "",
                            city = it.city?.title,
                            avatar = it.photo200Orig,
                            isOnline = it.onlineMobile==BaseBoolInt.YES
                        )
                    friendsList.add(friends)
                    }
                    presenter.friendsLoaded(friendsList)
                }
            })
    }
}