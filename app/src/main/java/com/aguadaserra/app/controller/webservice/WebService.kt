package com.aguadaserra.app.controller.webservice

import com.aguadaserra.app.global_model.User
import com.aguadaserra.app.controller.webservice.config.IgnoreSecurity
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface WebService {

    //User

    @IgnoreSecurity
    @POST ("usuarios/savefcm/")
    suspend fun saveFcm(@Body u: User): List<User>

    @IgnoreSecurity
    @POST ("usuarios/notificacoes/")
    suspend fun listNotifications(@Body u: User): List<User>

    @IgnoreSecurity
    @POST("usuarios/login/")
    suspend fun login(@Body u: User): List<User>

    @IgnoreSecurity
    @POST("usuarios/loginmidias/")
    @Multipart
    suspend fun loginExternal(
        @Part("nome") name: RequestBody,
        @Part("email") email: RequestBody,
        @Part("celular") cellphone: RequestBody,
        @Part("genero") genre: RequestBody,
        @Part("data_nascimento") birth: RequestBody,
        @Part("token") token: RequestBody,
        @Part("latitude") latitude: RequestBody,
        @Part("longitude") longitude: RequestBody,
        @Part url: MultipartBody.Part,
        @Part paixoes: List<MultipartBody.Part>,
    ): List<User>

    @IgnoreSecurity
    @POST("usuarios/verificaemail/")
    suspend fun verifyEmail(@Body u: User): List<User>

    @IgnoreSecurity
    @POST("usuarios/cadastroapp/")
    suspend fun register(@Body u: User): List<User>

    @IgnoreSecurity
    @POST("usuarios/recuperarsenha/")
    suspend fun recoverPassword(@Body u: User): List<User>

    @IgnoreSecurity
    @POST("usuarios/verificatoken/")
    suspend fun verifyTokenPassword(@Body u: User): List<User>

    @IgnoreSecurity
    @POST("usuarios/updatepassword/")
    suspend fun updatePassword(@Body u: User): List<User>

    @IgnoreSecurity
    @POST("usuarios/updateminhaconta/")
    suspend fun updateData(@Body u: User): List<User>

    @IgnoreSecurity
    @POST("usuarios/perfil/")
    suspend fun listId(@Body u: User): User

    @IgnoreSecurity
    @POST("usuarios/atualizarlocalizacao/")
    suspend fun updateLocation(@Body u: User): List<User>

    @IgnoreSecurity
    @POST("usuarios/updateoutrosdetalhes/")
    suspend fun updateOtherDetails(@Body u: User): List<User>

    @IgnoreSecurity
    @POST("usuarios/updatepaixoes/")
    suspend fun updatePassions(@Body u: User): User

    @IgnoreSecurity
    @POST("usuarios/updatealbum/")
    @Multipart
    suspend fun updateAlbum(
        @Part("id") id: RequestBody,
        @Part("token") token: RequestBody,
        @Part url: List<MultipartBody.Part>,
//        @Part capa: List<RequestBody>,
//        @Part legenda: List<RequestBody>,
    ): List<User>

    @IgnoreSecurity
    @POST("usuarios/updatesobremim/")
    suspend fun updateAbout(@Body u: User): User

    @IgnoreSecurity
    @POST("usuarios/updateradar/")
    suspend fun updatePreferencesRatio(@Body u: User): User

    @IgnoreSecurity
    @POST("usuarios/updatedescricao/")
    suspend fun updateDescription(@Body u: User): User

    @IgnoreSecurity
    @POST("usuarios/listpaixoes/")
    suspend fun listPassions(@Body u: User): List<User>

    @IgnoreSecurity
    @POST("usuarios/listgeneros/")
    suspend fun listGenres(@Body u: User): List<User>

    @IgnoreSecurity
    @POST("usuarios/updatealbumCapa/")
    suspend fun updateCapa(@Body u: User): User

    @IgnoreSecurity
    @POST("usuarios/updatedescricao/")
    suspend fun updateDesc(@Body u: User): User

    @IgnoreSecurity
    @POST("usuarios/listradar/")
    suspend fun listPreferences(@Body u: User): List<User>

    @IgnoreSecurity
    @POST("usuarios/listalbum/")
    suspend fun listAlbum(@Body u: User): List<User>

    @IgnoreSecurity
    @POST("usuarios/deletealbum")
    suspend fun deletePhotoAlbum(@Body u: User): User

    @IgnoreSecurity
    @POST("usuarios/listarOutrosDetalhes/")
    suspend fun listOtherDetails(@Body u: User): List<User>

    @IgnoreSecurity
    @POST("usuarios/desativar/")
    suspend fun disableUser(@Body u: User): User

}