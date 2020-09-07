package com.example.carat.Repository

import com.example.carat.Model.*
import com.example.carat.Network.CaratClient
import com.example.carat.Util.MyApp
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response

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

    suspend fun doLogin(): Response<TokenData> {
        val login = LoginData.getInstance()
        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("email", login.email)
            .addFormDataPart("password", login.password)
            .build()

        return api.doLogin(requestBody)
    }

    suspend fun doSignUp(parameter: SignData): Response<ServerMessage> {
        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("name", parameter.name)
            .addFormDataPart("email", parameter.email)
            .addFormDataPart("password", parameter.password)
            .build()

        return api.signUp(requestBody)
    }

    suspend fun getTimeLine(parameter: RequestCaringData): TimeLineData {
        return api.getTimeLine(parameter)
    }

    suspend fun getCaringTimeLine(
        email: String,
        parameter: RequestCaringData
    ): ProfileTimeLinePostData {
        return api.getCaringTimeLine(email, TokenData.getInstance().access_token, parameter)
    }

    suspend fun getCaratTimeLine(
        email: String,
        parameter: RequestCaringData
    ): ProfileTimeLinePostData {
        return api.getCaratTimeLine(email, TokenData.getInstance().access_token, parameter)
    }

    suspend fun createCaring(image: List<MultipartBody.Part>, caring: String): Call<Void> {
        return api.createCaring(TokenData.getInstance().access_token, image, caring)
    }

    suspend fun detailCaring(id: String): DetailTimeLineData {
        return api.detailCaring(id)
    }

    suspend fun reCaring(id: Int): Call<Void> {
        return api.reCaring(TokenData.getInstance().access_token, id)
    }

    suspend fun cancelReCaring(id: String): Call<Void> {
        return api.cancelReCaring(id, TokenData.getInstance().access_token)
    }

    suspend fun doCarat(id: String): Call<Void> {
        return api.doCarat(id, TokenData.getInstance().access_token)
    }

    suspend fun cancelCarat(id: String): Call<Void> {
        return api.cancelCarat(id, TokenData.getInstance().access_token)
    }

    suspend fun getCaringList(id: String): FollowingData {
        return api.getCaringList(id, TokenData.getInstance().access_token)
    }

    suspend fun getCaratList(id: String): CaratData {
        return api.getCaratList(id, TokenData.getInstance().access_token)
    }


    suspend fun getProfile(path: String): UserData {
        return api.getProfile(path, TokenData.getInstance().access_token)
    }

    suspend fun updateProfile(
        hash: HashMap<String, MultipartBody.Part>, name: String, about: String
    ): Call<Void> {
        return api.modifyProfile(TokenData.getInstance().access_token, hash, name, about)
    }

    suspend fun getFollowingList(path: String): FollowingData {
        return api.getFollowingList(path, TokenData.getInstance().access_token)
    }

    suspend fun getFollowersList(path: String): FollowerData {
        return api.getFollowersList(path, TokenData.getInstance().access_token)
    }

    suspend fun doFollow(path: String): Call<Void> {
        return api.doFollow(path, TokenData.getInstance().access_token)
    }

    suspend fun cancelFollow(path: String): Call<Void> {
        return api.cancelFollow(path, TokenData.getInstance().access_token)
    }
}