package com.aguadaserra.app.controller.webservice.config

import android.content.Context
import android.os.Build
import android.util.Log
import com.aguadaserra.app.ApplicationConstants
import com.aguadaserra.app.BuildConfig.DEBUG
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


object RetrofitServices {

    var ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ"

    lateinit var retrofit: Retrofit

    fun init(context: Context/*, localPreferences: LocalPreferencesInterface*/) {
        initRetrofit(context/*, localPreferences*/)
    }

    val gsonConfig = GsonBuilder()
        .setDateFormat(ISO_FORMAT)
        .setLenient()
//        .registerTypeAdapterFactory(StrapiModelTypeAdapterFactory()) // banco strapi
        .create()

    private fun initRetrofit(
        context: Context,
//        localPreferences: LocalPreferencesInterface
    ) {

        Log.d("RETROFIT", "Retrofit initialized! (debug = ${DEBUG})")

        //Configura o cliente Http
        val okHttpClient: OkHttpClient = getOkHttpBuilder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(SupportInterceptor(context/*, localPreferences)*/))
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        //Cria a instancia do Retrofit
        retrofit = Retrofit.Builder()
            .baseUrl(getBaseURL())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gsonConfig))
            .build()
    }

    /**
     * Cria e retorna uma instancia de um servico do tipo T
     */
    inline fun <reified T> getService(): T {
        return retrofit.create(T::class.java)
    }

    private fun getBaseURL() = ApplicationConstants.URL_BASE


    private fun getOkHttpBuilder(): OkHttpClient.Builder =
        //android 10 é o 29
        if (Build.VERSION.SDK_INT in 23..28) {
            getUnsafeOkHttpClient()
        } else {
            // Workaround for the error "Caused by: com.android.org.bouncycastle.jce.exception.ExtCertPathValidatorException:
            // Could not validate certificate: Certificate expired at".
//            OkHttpClient.Builder()
            getUnsafeOkHttpClient()
        }

    private fun getUnsafeOkHttpClient(): OkHttpClient.Builder =
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts: Array<TrustManager> = arrayOf(
                object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(chain: Array<X509Certificate?>?,
                                                    authType: String?) = Unit

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(chain: Array<X509Certificate?>?,
                                                    authType: String?) = Unit

                    override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
                }
            )
            // Install the all-trusting trust manager
            val sslContext: SSLContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory,
                trustAllCerts[0] as X509TrustManager
            )
            builder.hostnameVerifier { _, _ -> true }
            builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }



}

    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T
    ): ServiceResponse<T> {

        return withContext(dispatcher) {
            try {
                val result = apiCall.invoke()
                ServiceResponse.Success(result)
            } catch (throwable: Throwable) {
                Log.e("SafeApiCall", throwable.localizedMessage!!)
                when (throwable) {
                    is HttpException -> {
                        try {
                            val code = throwable.code()
                            throwable.response()?.errorBody()?.string()?.let {
                                Gson().fromJson(it, ServiceResponse.Error.GenericError::class.java)
                            } ?: ServiceResponse.Error.GenericError(code, throwable.message())
                        } catch (jsonException: JsonSyntaxException) {
                            ServiceResponse.Error.GenericError(null, jsonException.message)
                        }
                    }
                    is IOException -> ServiceResponse.Error.NetworkError
                    else -> {
                        ServiceResponse.Error.GenericError(null, throwable.message)
                    }
                }
            }
        }
}