package com.example.carat.Repository

import com.example.carat.Model.*
import com.example.carat.Network.CaratClient
import com.example.carat.Util.MyApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

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

    fun saveEmail(email: String) {
        sharedPreferences?.saveEmail = email
    }

    fun getLoginState(): Boolean? = sharedPreferences?.isLogin
    fun getAccess(): String? = sharedPreferences?.saveToken
    fun getRefresh(): String? = sharedPreferences?.saveRefreshToken
    fun getEmail() = sharedPreferences?.saveEmail

    suspend fun doLogin(): TokenData {
        return withContext(Dispatchers.IO) {
            api.doLogin(LoginData.getInstance())
        }
    }

    suspend fun doSignUp(parameter: SignData): ServerMessage {
        return withContext(Dispatchers.IO) {
            api.signUp(parameter)
        }
    }

    suspend fun getTimeLine(parameter: RequestCaringData): TimeLineData {
        return withContext(Dispatchers.IO) {
            api.getTimeLine(parameter)
        }
    }

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

    suspend fun createCaring(image: List<MultipartBody.Part>, caring: String): ServerMessage {
        return withContext(Dispatchers.IO) {
            api.createCaring(TokenData.getInstance().access_token, image, caring)
        }
    }

    suspend fun detailCaring(id: String): DetailTimeLineData {
        return withContext(Dispatchers.IO) {
            api.detailCaring(id)
        }
    }

    suspend fun reCaring(id: Int): ServerMessage {
        return withContext(Dispatchers.IO) {
            api.reCaring(TokenData.getInstance().access_token, id)
        }
    }

    suspend fun cancelReCaring(id: String): ServerMessage {
        return withContext(Dispatchers.IO) {
            api.cancelReCaring(id, TokenData.getInstance().access_token)
        }
    }

    suspend fun doCarat(id: String): ServerMessage {
        return withContext(Dispatchers.IO) {
            api.doCarat(id, TokenData.getInstance().access_token)
        }
    }

    suspend fun cancelCarat(id: String): ServerMessage {
        return withContext(Dispatchers.IO) {
            api.cancelCarat(id, TokenData.getInstance().access_token)
        }
    }

    suspend fun getCaringList(id: String): FollowingData {
        return withContext(Dispatchers.IO) {
            api.getCaringList(id, TokenData.getInstance().access_token)
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