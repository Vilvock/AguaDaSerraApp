package com.aguadaserra.app.controller.user

import com.aguadaserra.app.controller.webservice.config.ServiceResponse
import com.aguadaserra.app.global_model.User
import java.io.File

interface UserServiceInterface {

    suspend fun saveFcm(
        user: User
    ): ServiceResponse<List<User>>

    suspend fun listNotifications(
        user: User
    ): ServiceResponse<List<User>>

    suspend fun login(
        user: User
    ): ServiceResponse<List<User>>

    suspend fun loginExternal(
        name: String,
        email: String,
        cellphone: String,
        genre: String,
        birth: String,
        token: String,
        latitude: String,
        longitude: String,
        url: File,
        passionList: List<String>,
    ): ServiceResponse<List<User>>

    suspend fun verifyEmail(
        user: User
    ): ServiceResponse<List<User>>

    suspend fun register(
        user: User
    ): ServiceResponse<List<User>>

    suspend fun recoverPassword(
        user: User
    ): ServiceResponse<List<User>>

    suspend fun verifyTokenPassword(
        user: User
    ): ServiceResponse<List<User>>

    suspend fun updatePassword(
        user: User
    ): ServiceResponse<List<User>>

    suspend fun updateData(
        user: User
    ): ServiceResponse<List<User>>

    suspend fun listId(
        user: User
    ): ServiceResponse<User>

    suspend fun updateLocation(
        user: User
    ): ServiceResponse<List<User>>

    suspend fun updateOtherDetails(
        user: User
    ): ServiceResponse<List<User>>

    suspend fun updatePassions(
        user: User
    ): ServiceResponse<User>

    suspend fun updateDescription(
        user: User
    ): ServiceResponse<User>

    suspend fun updateAbout(
        user: User
    ): ServiceResponse<User>

    suspend fun updatePreferences(
        user: User
    ): ServiceResponse<User>

    suspend fun updateAlbum(
        id: String,
        token: String,
        urlList: List<File>,
//        boardList: List<String>,
//        descriptionList: List<String>
    ): ServiceResponse<List<User>>

    suspend fun listPassions(
        user: User
    ): ServiceResponse<List<User>>

    suspend fun listGenres(
        user: User
    ): ServiceResponse<List<User>>

    suspend fun updateDesc(
        user: User
    ): ServiceResponse<User>

    suspend fun listPreferences(
        user: User
    ): ServiceResponse<List<User>>

    suspend fun listAlbum(
        user: User
    ): ServiceResponse<List<User>>

//    suspend fun getLocation(
//        user: User
//    ): ServiceResponse<User>

    suspend fun deletePhotoAlbum(
        user: User
    ): ServiceResponse<User>

    suspend fun addPhotoCapa(
        user: User
    ): ServiceResponse<User>

    suspend fun listOtherDetails(
        user: User
    ): ServiceResponse<List<User>>

    suspend fun disableUser(
        user: User
    ): ServiceResponse<User>


}