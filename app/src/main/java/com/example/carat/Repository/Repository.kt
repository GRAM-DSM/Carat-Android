package com.example.carat.Repository

import com.example.carat.Model.*
import com.example.carat.Network.CaratClient
import com.example.carat.Util.MyApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class Repository() {
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

    suspend fun getProfile(path: String): UserData {
        return withContext(Dispatchers.IO) {
            api.getProfile(TokenData.getInstance().access_token, path)
        }
    }

    suspend fun updateProifle(hash: HashMap<String, Any>): Call<Unit> {
        return withContext(Dispatchers.IO) {
            api.modifyProfile(TokenData.getInstance().access_token, hash)
        }
    }

    suspend fun getFollowingList(path: String): FollowingData {
        return withContext(Dispatchers.IO) {
            api.getFollowingList(TokenData.getInstance().access_token, path)
        }
    }

    suspend fun getFollowersList(path: String): FollowerData {
        return withContext(Dispatchers.IO) {
            api.getFollowersList(TokenData.getInstance().access_token, path)
        }
    }

    suspend fun doFollow(path: String): Call<Unit> {
        return withContext(Dispatchers.IO) {
            api.doFollow(TokenData.getInstance().access_token, path)
        }
    }

    suspend fun cancelFollow(path: String): Call<Unit> {
        return withContext(Dispatchers.IO) {
            api.cancelFollow(TokenData.getInstance().access_token, path)
        }
    }
}