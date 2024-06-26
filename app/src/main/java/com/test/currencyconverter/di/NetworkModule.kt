package com.test.currencyconverter.di

import android.content.Context
import com.test.currencyconverter.BuildConfig
import com.test.currencyconverter.R
import com.test.currencyconverter.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyStore
import java.security.cert.CertificateFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS).build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    private fun generateSecureOkHttpClient(context: Context): OkHttpClient {
        // Create a simple builder for our http client, this is only por example purposes
        val client = OkHttpClient.Builder()
        client.followRedirects(false)
        client.followSslRedirects(false)
        client.connectTimeout(2, TimeUnit.SECONDS)
        client.callTimeout(5, TimeUnit.SECONDS)


        // Here you may wanna add some headers or custom setting for your builder
        // create self-signed server certificate
        val cf = CertificateFactory.getInstance("X.509")
        val cert = context.resources?.openRawResource(R.raw.openexchangerates_org)
        try {
            val ca = cf.generateCertificate(cert)
            val keyStoreType = KeyStore.getDefaultType()
            val keyStore = KeyStore.getInstance(keyStoreType)
            keyStore.load(null, null)
            keyStore.setCertificateEntry("ca", ca)

            val tmfAlgo = TrustManagerFactory.getDefaultAlgorithm()
            val tmf = TrustManagerFactory.getInstance(tmfAlgo)
            tmf.init(keyStore)

            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, tmf.trustManagers, null)

            client.sslSocketFactory(
                sslContext.socketFactory, tmf.trustManagers[0] as X509TrustManager
            )
        } finally {
            cert?.close()
        }
        return client.build()
    }


//    @Provides
//    fun provideHttpClient(sessionHandler: SessionHandler): HttpClient =
//        MiniTalesHttpClientBuilder(sessionHandler).protocol(URLProtocol.HTTP)
//            .host(BuildConfig.MINI_TALES_HOST).port(8080).build()
//
//    @Provides
//    fun provideRequestHandler(client: HttpClient) = RequestHandler(client)
}

