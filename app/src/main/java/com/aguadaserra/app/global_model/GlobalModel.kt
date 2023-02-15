package com.aguadaserra.app.global_model

import com.google.gson.annotations.SerializedName

open class GlobalModel {

    @field:SerializedName("horario")
    var schedule: String? = null

    @field:SerializedName("qrcode_64")
    var qrCode64: String? = null

    @field:SerializedName("qrcode")
    var qrCode: String? = null

    @field:SerializedName("sobrenome")
    var lastName: String? = null

    @field:SerializedName("valor")
    var value: String? = null

    @field:SerializedName("latitude")
    var latitude: String? = null

    @field:SerializedName("longitude")
    var longitude: String? = null

    @field:SerializedName("id_user")
    var idUser: String? = null

    @field:SerializedName("documento")
    var document: String? = null

    @field:SerializedName("id_para")
    var idTo: String? = null

    @field:SerializedName("id_de")
    var idFrom: String? = null

    @field:SerializedName("id")
    var id: String? = null

    @field:SerializedName("customer_id")
    var idCustomer: String? = null

    @field:SerializedName("token")
    var token: String? = null

    @field:SerializedName("rows")
    var rows: String? = null

    @field:SerializedName("id_moip")
    var moipId: String? = null

    @field:SerializedName("msg")
    var msg: String? = null

    @field:SerializedName("status")
    var status: String? = null

    @field:SerializedName("data")
    var date: String? = null

    @field:SerializedName("type")
    var type: String? = null

    @field:SerializedName("tipo")
    var typeC: String? = null

    @field:SerializedName("avatar")
    var avatar: String? = null

    @field:SerializedName("url")
    var url: String? = null

    @field:SerializedName("url_avatar")
    var urlAvatar: String? = null

    @field:SerializedName("titulo")
    var title: String? = null

    @field:SerializedName("descricao")
    var description: String? = null

    @field:SerializedName("obs")
    var observation: String? = null

    @field:SerializedName("card_id")
    var cardId: String? = null

    @field:SerializedName("security_code")
    var cvv: String? = null

    @field:SerializedName("card_cvc")
    var cardCvc: String? = null

    @field:SerializedName("nome_cartao")
    var cardName: String? = null

    @field:SerializedName("expiration_year")
    var yearValidity: String? = null

    @field:SerializedName("expiration_month")
    var monthValidity: String? = null

    @field:SerializedName("card_number")
    var cardNumber: String? = null

    @field:SerializedName("ultimos_digitos")
    var lastDigitsCard: String? = null

    @field:SerializedName("bandeira")
    var flagCard: String? = null

    @field:SerializedName("nome")
    var name: String? = null

    @field:SerializedName("celular")
    var cellphone: String? = null

    @field:SerializedName("data_nascimento")
    var birth: String? = null

    @field:SerializedName("email")
    var email: String? = null

    @field:SerializedName("idade")
    var age: String? = null

    @field:SerializedName("capa")
    var board: String? = null

    @field:SerializedName("id_endereco")
    var idAddress: String? = null

    @field:SerializedName("cep")
    var cep: String? = null

    @field:SerializedName("estado")
    var state: String? = null

    @field:SerializedName("cidade")
    var city: String? = null

    @field:SerializedName("bairro")
    var neighborhood: String? = null

    @field:SerializedName("numero")
    var number: String? = null

    @field:SerializedName("complemento")
    var complement: String? = null

    @field:SerializedName("endereco")
    var address: String? = null

    @field:SerializedName("distancia")
    var distance: String? = null

    @field:SerializedName("municipio")
    var county: String? = null
}