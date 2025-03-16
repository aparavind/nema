package com.example.nema.data

import androidx.room.*

@Entity(tableName = "things")
data class Thing(
    @PrimaryKey val name: String,
    val description: String,
    val location: String
)

@Dao
interface ThingDao {
    @Query("SELECT * FROM things WHERE name LIKE :query")
    fun searchThings(query: String): List<Thing>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertThings(things: List<Thing>)
}

@Database(entities = [Thing::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun thingDao(): ThingDao
}
