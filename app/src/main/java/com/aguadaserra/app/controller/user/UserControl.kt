package com.aguadaserra.app.controller.user

import com.aguadaserra.app.controller.webservice.WebService
import com.aguadaserra.app.controller.webservice.config.RetrofitServices
import com.aguadaserra.app.controller.webservice.config.ServiceResponse
import com.aguadaserra.app.controller.webservice.config.generateMultipartRequestBody
import com.aguadaserra.app.controller.webservice.config.safeApiCall
import com.aguadaserra.app.global_model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class UserControl(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserServiceInterface {

    private val service: WebService = RetrofitServices.getService()

    override suspend fun saveFcm(user: User): ServiceResponse<List<User>> {
        return safeApiCall(dispatcher) {
            service.saveFcm(user)
        }
    }

    override suspend fun listNotifications(user: User): ServiceResponse<List<User>> {
        return safeApiCall(dispatcher) {
            service.listNotifications(user)
        }
    }

    override suspend fun login(user: User): ServiceResponse<List<User>> {
        return safeApiCall(dispatcher) {
            service.login(user)
        }
    }

    override suspend fun loginExternal(
        name: String,
        email: String,
        cellphone: String,
        genre: String,
        birth: String,
        token: String,
        latitude: String,
        longitude: String,
        url: File,
        passionList: List<String>
    ): ServiceResponse<List<User>> {

        val passionListMp = ArrayList<MultipartBody.Part>()
        for(i in passionList.indices) {
            passionListMp.add(MultipartBody.Part.createFormData(
                "paixoes[$i]",
                passionList[i]
            ))
        }

        return safeApiCall(dispatcher) {
            service.loginExternal(
                name = name.generateMultipartRequestBody()!!,
                email = email.generateMultipartRequestBody()!!,
                cellphone = cellphone.generateMultipartRequestBody()!!,
                genre = genre.generateMultipartRequestBody()!!,
                birth = birth.generateMultipartRequestBody()!!,
                token = token.generateMultipartRequestBody()!!,
                latitude = latitude.generateMultipartRequestBody()!!,
                longitude = longitude.generateMultipartRequestBody()!!,
                url = MultipartBody.Part.createFormData("url", url.name, url.asRequestBody("image/*".toMediaTypeOrNull())),
                paixoes = passionListMp)
        }
    }

    override suspend fun verifyEmail(user: User): ServiceResponse<List<User>> {
        return safeApiCall(dispatcher) {
            service.verifyEmail(user)
        }
    }

    override suspend fun register(user: User): ServiceResponse<List<User>> {
        return safeApiCall(dispatcher) {
            service.register(user)
        }
    }

    override suspend fun recoverPassword(user: User): ServiceResponse<List<User>> {
        return safeApiCall(dispatcher) {
            service.recoverPassword(user)
        }
    }

    override suspend fun verifyTokenPassword(user: User): ServiceResponse<List<User>> {
        return safeApiCall(dispatcher) {
            service.verifyTokenPassword(user)
        }
    }

    override suspend fun updatePassword(user: User): ServiceResponse<List<User>> {
        return safeApiCall(dispatcher) {
            service.updatePassword(user)
        }
    }

    override suspend fun updateData(user: User): ServiceResponse<List<User>> {
        return safeApiCall(dispatcher) {
            service.updateData(user)
        }
    }


    override suspend fun listId(user: User): ServiceResponse<User> {
        return safeApiCall(dispatcher) {
            service.listId(user)
        }
    }

    override suspend fun updateLocation(user: User): ServiceResponse<List<User>> {
        return safeApiCall(dispatcher) {
            service.updateLocation(user)
        }
    }

    override suspend fun updateOtherDetails(user: User): ServiceResponse<List<User>> {
        return safeApiCall(dispatcher) {
            service.updateOtherDetails(user)
        }
    }

    override suspend fun updatePassions(user: User): ServiceResponse<User> {
        return safeApiCall(dispatcher) {
            service.updatePassions(user)
        }
    }

    override suspend fun updateAbout(user: User): ServiceResponse<User> {
        return safeApiCall(dispatcher) {
            service.updateAbout(user)
        }
    }
    override suspend fun updateDescription(user: User): ServiceResponse<User> {
        return safeApiCall(dispatcher) {
            service.updateDescription(user)
        }
    }

    override suspend fun updatePreferences(user: User): ServiceResponse<User> {
        return safeApiCall(dispatcher) {
            service.updatePreferencesRatio(user)
        }
    }

    override suspend fun updateAlbum(
        id: String,
        token: String,
        urlList: List<File>,
//        boardList: List<String>,
//        descriptionList: List<String>
    ): ServiceResponse<List<User>> {

        val urlListMultiPart = ArrayList<MultipartBody.Part>()
        for(i in urlList.indices) {
            urlListMultiPart.add(MultipartBody.Part.createFormData(
                "url[$i]",
                urlList[i].name,
                urlList[i].asRequestBody("image/*".toMediaTypeOrNull())))
        }


        return safeApiCall(dispatcher) {
            service.updateAlbum(
                id = id.generateMultipartRequestBody()!!,
                token = token.generateMultipartRequestBody()!!,
                url = urlListMultiPart,
//                capa = audioListMultiPart,
//                legenda = documentListMultiPart
            )
        }
    }

    override suspend fun listPassions(user: User): ServiceResponse<List<User>> {
        return safeApiCall(dispatcher) {
            service.listPassions(user)
        }
    }

    override suspend fun listGenres(user: User): ServiceResponse<List<User>> {
        return safeApiCall(dispatcher) {
            service.listGenres(user)
        }
    }

    override suspend fun updateDesc(user: User): ServiceResponse<User> {
        return safeApiCall(dispatcher) {
            service.updateDesc(user)
        }
    }

    override suspend fun listPreferences(user: User): ServiceResponse<List<User>> {
        return safeApiCall(dispatcher) {
            service.listPreferences(user)
        }
    }

    override suspend fun listAlbum(user: User): ServiceResponse<List<User>> {
        return safeApiCall(dispatcher) {
            service.listAlbum(user)
        }
    }



//    override suspend fun getLocation(user: User): ServiceResponse<User> {
//        return safeApiCall(dispatcher) {
//            service.getLocation(user)
//        }
//    }

    override suspend fun deletePhotoAlbum(user: User): ServiceResponse<User> {
        return safeApiCall(dispatcher) {
            service.deletePhotoAlbum(user)
        }
    }
    override suspend fun addPhotoCapa(user: User): ServiceResponse<User> {
        return safeApiCall(dispatcher) {
            service.updateCapa(user)
        }
    }

    override suspend fun listOtherDetails(user: User): ServiceResponse<List<User>> {
        return safeApiCall(dispatcher) {
            service.listOtherDetails(user)
        }
    }

    override suspend fun disableUser(user: User): ServiceResponse<User> {
        return safeApiCall(dispatcher) {
            service.disableUser(user)
        }
    }


}