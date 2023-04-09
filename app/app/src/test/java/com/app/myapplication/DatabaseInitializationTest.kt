package com.app.myapplication
import androidx.test.platform.app.InstrumentationRegistry
import com.app.myapplication.model.repository.SQLiteUser
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4


@RunWith(AndroidJUnit4::class)
class DatabaseInitializationTest {
    @Test
    fun testDatabaseInitialization() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val db = SQLiteUser(context).writableDatabase
        assert(db.isOpen)
        db.close()
    }
}