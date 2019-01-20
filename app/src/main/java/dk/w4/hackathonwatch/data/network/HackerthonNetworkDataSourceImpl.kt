package dk.w4.hackathonwatch.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dk.w4.hackathonwatch.data.HackathonAPIService
import dk.w4.hackathonwatch.data.db.entity.Hackathon
import dk.w4.hackathonwatch.internal.NoConnectivityException

class HackerthonNetworkDataSourceImpl(
    private val hackathonApiService: HackathonAPIService
) : HackerthonNetworkDataSource {

    private val _downloadedHackathons = MutableLiveData<List<Hackathon>>()
    override val downloadedHackathons: LiveData<List<Hackathon>>
        get() = _downloadedHackathons

    override suspend fun fetchHackathons() {
        try {
            val fetchedHackathon = hackathonApiService
                .getAll().await()
            _downloadedHackathons.postValue(fetchedHackathon)
        }
        catch (e: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.")
        }
    }
}