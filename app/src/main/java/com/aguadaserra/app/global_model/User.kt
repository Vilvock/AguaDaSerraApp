package com.aguadaserra.app.global_model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(

    @field:SerializedName("generos")
    var genreList: List<User>? = null,

    @field:SerializedName("cod_indicador")
    var personCode: String? = null,

    @field:SerializedName("cidade_reside")
    var cityPerson: String? = null,

    @field:SerializedName("id_motivo")
    var idReason: String? = null,

    @field:SerializedName("empresa")
    var enterprise: String? = null,

    @field:SerializedName("profissao")
    var idCareer: String? = null,

    @field:SerializedName("id_escolaridade")
    var idSchooling: String? = null,

    @field:SerializedName("id_orientacao")
    var idOrientation: String? = null,

    @field:SerializedName("id_estado_civil")
    var idCivilState: String? = null,

    @field:SerializedName("id_signo")
    var idSign: String? = null,

    @field:SerializedName("altura")
    var height: String? = null,

    @field:SerializedName("idade_in")
    var ageIn: String? = null,

    @field:SerializedName("idade_out")
    var ageOut: String? = null,

    @field:SerializedName("raio")
    var radius: String? = null,

    @field:SerializedName("paixoes")
    var passionList: List<String>? = null,

    @field:SerializedName("paixoes_usuario")
    var passionListUser: List<User>? = null,

    @field:SerializedName("app_generos_id_types")
    var idGenreTypeList: List<User>? = null,

    @field:SerializedName("app_generos_id")
    var idGenreType: String? = null,

    @field:SerializedName("app_generos_id_c")
    var idGenreListMatch: List<String>? = null,

    @field:SerializedName("password")
    var password: String? = null,

    @field:SerializedName("fcm")
    var fcm: String? = null,

    @field:SerializedName("razao_social")
    var corporateName: String? = null,

    @field:SerializedName("tipo_pessoa")
    var typePerson: String? = null,

    @field:SerializedName("cpf")
    var cpf: String? = null,

    @field:SerializedName("cnpj")
    var cnpj: String? = null,

    @field:SerializedName("telefone")
    var telephone: String? = null,

    @field:SerializedName("token_senha")
    var tokenPassword: String? = null,

    @field:SerializedName("registration_id")
    var registrationId: String? = null,



    ) : GlobalModel(), Serializable {

}