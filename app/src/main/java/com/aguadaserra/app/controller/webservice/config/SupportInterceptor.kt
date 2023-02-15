package com.aguadaserra.app.controller.webservice.config

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class IgnoreSecurity

/**
 * Interceptor class for setting of the headers for every request
 */
class SupportInterceptor(
    private val context: Context,
//    private val localPreferences: LocalPreferencesInterface
) : Interceptor {

//    companion object {
//        const val ACTION_REST_UNAUTHORIZED = "action_rest_unauthorized"
//    }

//    val requestsIgnoringSecurity = hashSetOf(
//        "drivers/login",
//        "passengers/login",
//        "auth/facebook/callback",
//        "auth/local",
//        "drivers/register",
//        "auth/forgot-password",
//        "passengers/register"
//    )

    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()

        val invocation = request.tag(Invocation::class.java)
        val ignoreSecurity = invocation?.method()?.getAnnotation(IgnoreSecurity::class.java)

        if (ignoreSecurity != null || request.header("authentication") != null) {
            return chain.proceed(request)
        }

        //se nao tiver anotacao ignoresecurity passa para fazer autenticacao com token no header,
        //nao se faz necessario aqui pois a autenticacao e feita por uma string TOKEN nas requests

//        val userJwt = localPreferences.getString(PREFS_TOKEN_AUTH, null)
//        val userJwt = WSContants.TOKEN
//
//        if (userJwt != null) {
//            request = request.newBuilder()
//                .addHeader("authorization", userJwt.toAuthToken())
//                .build()
//        }

        val result = chain.proceed(request)

        //serve para fazer autenticacao, caso queira fazer um sistema para deslogar o usuario caso ele n tenha uma
        //usa broadcast para chamar outra classe apra fazer a acao de deslogar
//        if (result.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
//            fireNoAuthenticationBroadcast()
//        }

        return result
    }

//    private fun fireNoAuthenticationBroadcast() {
//        val intent = Intent(ACTION_REST_UNAUTHORIZED)
//        intent.putExtra("401", HttpURLConnection.HTTP_UNAUTHORIZED)
//        LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
//    }
}