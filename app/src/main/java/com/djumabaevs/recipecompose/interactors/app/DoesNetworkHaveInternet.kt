package com.djumabaevs.recipecompose.interactors.app

import android.util.Log
import com.djumabaevs.recipecompose.util.TAG
import java.io.IOException
import java.net.InetSocketAddress
import javax.net.SocketFactory

object DoesNetworkHaveInternet {

    //Make sure to execute this on a background thread.
    fun execute(socketFactory: SocketFactory): Boolean {
        return try {
            Log.d(TAG, "execute: Pinging GOOGLE.")
            val socket = socketFactory.createSocket()?: throw IOException("socket is null.")
            socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
            socket.close()
            Log.d(TAG, "execute: Success ping.")
            true
        }catch (e: Exception) {
            Log.e(TAG, "execute: No internet connection: ${e}")
            false
        }
    }
}