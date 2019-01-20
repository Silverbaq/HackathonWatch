package dk.w4.hackathonwatch.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dk.w4.hackathonwatch.data.db.entity.Hackathon


@Database(
    entities = [Hackathon::class],
    version = 1,
    exportSchema = false
)
abstract class HackathonDatabase : RoomDatabase() {
    abstract fun hackathonDao(): HackathonDao

    companion object {
        @Volatile
        private var instance: HackathonDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, HackathonDatabase::class.java, "hackathon.db")
                .build()
    }
}