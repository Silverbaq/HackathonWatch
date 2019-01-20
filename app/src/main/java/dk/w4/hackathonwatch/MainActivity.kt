package dk.w4.hackathonwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import dk.w4.hackathonwatch.data.HackathonAPIService
import dk.w4.hackathonwatch.data.network.ConnectivityIntercepterImpl
import dk.w4.hackathonwatch.data.network.HackerthonNetworkDataSourceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
