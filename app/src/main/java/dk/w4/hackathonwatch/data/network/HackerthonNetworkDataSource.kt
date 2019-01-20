package dk.w4.hackathonwatch.data.network

import androidx.lifecycle.LiveData
import dk.w4.hackathonwatch.data.db.entity.Hackathon

interface HackerthonNetworkDataSource {
    val downloadedHackathons: LiveData<List<Hackathon>>

    suspend fun fetchHackathons()
}