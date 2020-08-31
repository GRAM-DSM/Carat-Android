package com.example.carat.Repository

import com.example.carat.Model.*
import com.example.carat.Network.CaratClient
import com.example.carat.Util.MyApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Part

class Repository {
    private val sharedPreferences = SharedPreferencesManager.getInstance(MyApp.context!!)
    private val api = CaratClient.caratApi

    fun saveLoginState(isLogin: Boolean) {
        sharedPreferences?.isLogin = isLogin
    }

    fun saveToken(access: String, refresh: String) {
        sharedPreferences?.saveToken = access
        sharedPreferences?.saveRefreshToken = refresh
    }

    fun saveEmail() {
        sharedPreferences?.saveEmail = UserObject.getInstance().email
    }

    fun getLoginState(): Boolean? = sharedPreferences?.isLogin
    fun getAccess(): String? = sharedPreferences?.saveToken
    fun getRefresh(): String? = sharedPreferences?.saveRefreshToken
    fun getEmail() = sharedPreferences?.saveEmail

    suspend fun getCaringTimeLine(
        email: String,
        parameter: RequestCaringData
    ): ProfileTimeLinePostData {
        return withContext(Dispatchers.IO) {
            api.getCaringTimeLine(email, TokenData.getInstance().access_token, parameter)
        }
    }

    suspend fun getCaratTimeLine(
        email: String,
        parameter: RequestCaringData
    ): ProfileTimeLinePostData {
        return withContext(Dispatchers.IO) {
            api.getCaratTimeLine(email, TokenData.getInstance().access_token, parameter)
        }
    }

    suspend fun getCaratList(id: String): CaratData {
        return withContext(Dispatchers.IO) {
            api.getCaratList(id, TokenData.getInstance().access_token)
        }
    }

    suspend fun getProfile(path: String): UserData {
        return withContext(Dispatchers.IO) {
            api.getProfile(path, TokenData.getInstance().access_token)
        }
    }

    suspend fun updateProfile(
        hash: HashMap<String, MultipartBody.Part>, name: String, about: String
    ): Call<Unit> {
        return withContext(Dispatchers.IO) {
            api.modifyProfile(TokenData.getInstance().access_token, hash, name, about)
        }
    }

    suspend fun getFollowingList(path: String): FollowingData {
        return withContext(Dispatchers.IO) {
            api.getFollowingList(path, TokenData.getInstance().access_token)
        }
    }

    suspend fun getFollowersList(path: String): FollowerData {
        return withContext(Dispatchers.IO) {
            api.getFollowersList(path, TokenData.getInstance().access_token)
        }
    }

    suspend fun doFollow(path: String): Call<Unit> {
        return withContext(Dispatchers.IO) {
            api.doFollow(path, TokenData.getInstance().access_token)
        }
    }

    suspend fun cancelFollow(path: String): Call<Unit> {
        return withContext(Dispatchers.IO) {
            api.cancelFollow(path, TokenData.getInstance().access_token)
        }
    }
}