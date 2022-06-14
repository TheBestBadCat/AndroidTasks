package com.stanislavkorneev.korneevapp.data.repository

import android.util.Log
import com.stanislavkorneev.korneevapp.data.api.ApiService
import com.stanislavkorneev.korneevapp.data.api.UserApi
import com.stanislavkorneev.korneevapp.domain.entities.Auth
import com.stanislavkorneev.korneevapp.domain.entities.User
import com.stanislavkorneev.korneevapp.domain.entities.UserRole
import com.stanislavkorneev.korneevapp.domain.repository.AuthRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "Repository"

class AuthRepositoryImpl : AuthRepository {

    private val api = ApiService()
    private var responseCode: Int = 0

    override fun login(auth: Auth): String {
        var token: String? = ""

        val retrofit = api.retrofit.create(UserApi::class.java)

        val uploadFileRequest: Call<String> = retrofit.login(auth)

        uploadFileRequest.enqueue(object : Callback<String> {

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d(TAG, "Response received: ${response.body()} ${response.code()}")
                token = response.body()
                responseCode = response.code()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d(TAG, "Failed login", t)
            }
        })
        return token!!
    }

    override fun registration(auth: Auth): User {

        var user: User? = User("", UserRole.USER)

        val retrofit = api.retrofit.create(UserApi::class.java)

        val uploadFileRequest: Call<User> = retrofit.registration(auth)

        uploadFileRequest.enqueue(object : Callback<User> {

            override fun onResponse(call: Call<User>, response: Response<User>) {
                Log.d(TAG, "Response received: ${response.body()} ${response.code()}")
                user = response.body()
                responseCode = response.code()
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d(TAG, "Failed registration", t)
            }
        })

        return user!!
    }

    fun getResponseCode() = responseCode
}