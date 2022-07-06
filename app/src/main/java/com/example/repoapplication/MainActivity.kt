package com.example.repoapplication

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repositoryviewer.RecyclerViewAdaptor
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private val BASE_URL = "https://api.github.com"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkInternetAndShowRepos(this)
    }

    private fun checkInternetAndShowRepos(context: Context){
        setContentView(R.layout.loading)
        if(isOnline(this)) {
            getData(this)
        }
        else{
            setContentView(R.layout.no_internet)
            findViewById<Button>(R.id.try_again_button).setOnClickListener {
                checkInternetAndShowRepos(context)
            }
        }
    }

    private fun getData(context: Context){
        val retrofitBulider = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        retrofitBulider.getdata().enqueue(object : Callback<List<RepoItem>?> {
            override fun onResponse(
                call: Call<List<RepoItem>?>,
                response: Response<List<RepoItem>?>
            ) {
                if(response.body()!=null && response.body()!!.size > 0){
                    setContentView(R.layout.activity_main)
                    reposList.adapter = RecyclerViewAdaptor(context, response.body()!!)
                    reposList.layoutManager = LinearLayoutManager(context)
                }
                else{
                    setContentView(R.layout.no_repos)
                }
            }

            override fun onFailure(call: Call<List<RepoItem>?>, t: Throwable) {
                setContentView(R.layout.api_error)
                findViewById<Button>(R.id.try_again_button).setOnClickListener {
                    checkInternetAndShowRepos(context)
                }
            }
        })
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return true
                }
            }
        }
        return false
    }
}