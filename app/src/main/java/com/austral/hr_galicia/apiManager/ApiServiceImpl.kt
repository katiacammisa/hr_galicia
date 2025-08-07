package com.austral.hr_galicia.apiManager

import android.content.Context
import android.widget.Toast
import com.austral.hr_galicia.R
import com.austral.hr_galicia.api.RequestResponse
import com.austral.hr_galicia.api.UserResponse
import com.austral.hr_galicia.api.toUser
import com.austral.hr_galicia.data.User
import retrofit.Call
import retrofit.Callback
import retrofit.GsonConverterFactory
import retrofit.Response
import retrofit.Retrofit
import javax.inject.Inject

class ApiServiceImpl @Inject constructor() {

    fun getUsers(context: Context, onSuccess: (User) -> Unit, onFail: () -> Unit, page: String) {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(
                context.getString(R.string.users_url)
            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()

        val service: ApiService = retrofit.create(ApiService::class.java)

        val call: Call<RequestResponse> = service.getUsers(page = page)

        call.enqueue(object : Callback<RequestResponse> {
            override fun onResponse(response: Response<RequestResponse>?, retrofit: Retrofit?) {
                if(response?.isSuccess == true) {
                    val user = response.body().results.firstOrNull()
                    if (user != null) {
                        onSuccess(user.toUser())
                    }

                } else {
                    onFailure(Exception("Bad request"))
                }
            }

            override fun onFailure(t: Throwable?) {
                Toast.makeText(context, "Can't get users", Toast.LENGTH_SHORT).show()
                onFail()
            }
        })
    }
}