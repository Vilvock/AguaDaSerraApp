package com.aguadaserra.app.controller.webservice.config

//import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

fun String.toAuthToken() = "Bearer $this"

//Versao mais nova
fun String?.generateMultipartRequestBody(): RequestBody? =
    this?.toRequestBody("multipart/form-data".toMediaTypeOrNull())

//Versao antiga
//fun String?.generateMultipartRequestBody(): RequestBody? = if(this == null) null
//    else RequestBody.create(MediaType.get("multipart/form-data"), this)