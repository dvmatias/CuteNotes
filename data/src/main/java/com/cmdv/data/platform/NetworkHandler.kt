package com.cmdv.data.platform

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.cmdv.domain.base.Mockable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Mockable
class NetworkHandler @Inject constructor(private val context: Context) {
    
    val isConnected
        get() = context.networkInfo?.isConnectedOrConnecting
    
}

val Context.networkInfo: NetworkInfo?
    get() = (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo