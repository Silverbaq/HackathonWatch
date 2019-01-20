package dk.w4.hackathonwatch.data.repository

import androidx.lifecycle.LiveData
import dk.w4.hackathonwatch.data.db.entity.Hackathon

interface HackathonRepository {
    suspend fun getHackathons(): LiveData<List<Hackathon>>

    suspend fun getSingleHackathon(id: Int): LiveData<Hackathon>
}