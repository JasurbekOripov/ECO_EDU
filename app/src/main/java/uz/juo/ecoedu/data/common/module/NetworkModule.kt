package uz.juo.ecoedu.data.common.module

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import uz.juo.ecoedu.BuildConfig
import java.lang.Exception
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder().baseUrl(BuildConfig.API_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context,authInterceptor: AuthInterceptor,tokenInterceptro: TokenInterceptor): OkHttpClient =
        OkHttpClient.Builder().also { client ->
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            client.readTimeout(60, TimeUnit.SECONDS)
            client.connectTimeout(60, TimeUnit.SECONDS)
            client.addInterceptor(logging)
            client.addInterceptor(tokenInterceptro)
            client.addInterceptor(authInterceptor)
            client.addInterceptor(ChuckerInterceptor(context))
        }.build()

//    @Provides
//    @Singleton
//    fun provideApi(retrofit: Retrofit):ApiS {
//
//    }

    @Singleton
    class AuthInterceptor @Inject constructor(@ApplicationContext private val context: Context) :
        Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val oldRequest = chain.request()
            val oldResponse = chain.proceed(oldRequest)
            val responseBody = oldResponse.body
            if (oldResponse.code == 401) {
                val modifiedReuest: Request?
                val client = OkHttpClient()
                val params = JSONObject()
                params.put("phone_number", "+998901234567")
                val body: RequestBody =
                    RequestBody.create(
                        responseBody?.contentType(),
                        params.toString()
                    )
                val nRequest = Request.Builder()
                    .post(body)
                    .url(BuildConfig.API_URL)
                    .build()
                val response = client.newCall(nRequest).execute()
                if (response.code == 200) {
                    try {
                        val jsonData = response.body?.string() ?: ""
                        val gson = Gson()
//                        val refreshTokenResponseModel =   gson.fromJson(jsonData, AuthResponse::class.java)

                    } catch (e: Exception) {

                    }
                }
            }
            return oldResponse
        }
    }

    @Singleton
    class TokenInterceptor @Inject constructor() : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer")
                .build()
            return chain.proceed(newRequest)
        }
    }
}