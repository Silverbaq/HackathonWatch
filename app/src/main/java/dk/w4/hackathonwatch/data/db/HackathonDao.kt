package dk.w4.hackathonwatch.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dk.w4.hackathonwatch.data.db.entity.Hackathon

@Dao
interface HackathonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(hackathon: Hackathon)

    @Query("select * from hackathon where id = :id")
    fun getHackathon(id: Int): LiveData<Hackathon>

    @Query("select * from hackathon")
    fun getAllHackathon(): LiveData<List<Hackathon>>
}