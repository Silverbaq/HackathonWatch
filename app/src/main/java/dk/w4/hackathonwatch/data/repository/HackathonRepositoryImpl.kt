package dk.w4.hackathonwatch.data.repository

import androidx.lifecycle.LiveData
import androidx.room.PrimaryKey
import dk.w4.hackathonwatch.data.db.HackathonDao
import dk.w4.hackathonwatch.data.db.entity.Hackathon
import dk.w4.hackathonwatch.data.network.HackerthonNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime


class HackathonRepositoryImpl(
    private val hackathonDao: HackathonDao,
    private val hackathonNetworkDataSource: HackerthonNetworkDataSource
) : HackathonRepository {

    init {
        hackathonNetworkDataSource.downloadedHackathons.observeForever { newHackathons ->
            persistFetechedHackershons(newHackathons)
        }
    }

    override suspend fun getHackathons(): LiveData<List<Hackathon>> {
        initHackathonData()
        return withContext(Dispatchers.IO) {
            return@withContext hackathonDao.getAllHackathon()
        }
    }

    override suspend fun getSingleHackathon(id: Int): LiveData<Hackathon> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun persistFetechedHackershons(fetchedHackathons: List<Hackathon>) {
        GlobalScope.launch(Dispatchers.IO) {
            for (hackathon in fetchedHackathons)
                hackathonDao.insert(hackathon)
        }
    }

    private suspend fun initHackathonData() {
        if (isFetchNeeded(ZonedDateTime.now().minusHours(1)))
            fetchHackathon()
    }

    private suspend fun fetchHackathon(){
        hackathonNetworkDataSource.fetchHackathons()
    }

    private fun isFetchNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutsAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutsAgo)
    }
}
