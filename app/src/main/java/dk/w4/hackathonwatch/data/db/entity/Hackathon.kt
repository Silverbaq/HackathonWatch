package dk.w4.hackathonwatch.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hackathon")
data class Hackathon(
    val description: String,
    val finish_timestamp: Int,
    val full_address: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val public_url: String,
    val start_timestamp: Int
)