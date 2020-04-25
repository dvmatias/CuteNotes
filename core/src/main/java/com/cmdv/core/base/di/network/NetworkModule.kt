package com.cmdv.core.base.di.network

import android.content.Context
import com.cmdv.data.platform.NetworkHandler
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

	companion object {
		private const val CONNECT_TIMEOUT_VALUE = 45L
		private const val READ_TIMEOUT_VALUE = 45L
		private const val WRITE_TIMEOUT_VALUE = 45L
	}

//	@Provides
//	@Singleton
//	fun provideRetrofit(gson: Gson): Retrofit =
//		Retrofit.Builder()
//			.baseUrl(BuildConfig.BASE_URL)
//			.addConverterFactory(GsonConverterFactory.create(gson))
//			.client(createRetrofitClient())
//			.build()
//
//	private fun createRetrofitClient(): OkHttpClient {
//		val interceptor = HttpLoggingInterceptor()
//		interceptor.level = HttpLoggingInterceptor.Level.BODY
//		val okHttpClient = OkHttpClient.Builder()
//			.connectTimeout(CONNECT_TIMEOUT_VALUE, TimeUnit.SECONDS)
//			.readTimeout(READ_TIMEOUT_VALUE, TimeUnit.SECONDS)
//			.writeTimeout(WRITE_TIMEOUT_VALUE, TimeUnit.SECONDS)
//			.addInterceptor(interceptor)
//		return okHttpClient.build()
//	}
//
//	@Singleton
//	@Provides
//	fun provideGson(): Gson =
//		GsonBuilder().disableHtmlEscaping().create()

	@Singleton
	@Provides
	fun provideNetworkHandler(context: Context): NetworkHandler =
		NetworkHandler(context)

}