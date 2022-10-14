package com.app.secretlocker

import android.R.attr.password
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.secretlocker.ui.theme.SecretLockerTheme
import net.zetetic.database.sqlcipher.SQLiteDatabase
import java.io.File


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeSQLCipher()
        setContent {
            SecretLockerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

private fun initializeSQLCipher() {
    System.loadLibrary("sqlcipher")
    val databaseFile = "test.db"
    val database = SQLiteDatabase.openOrCreateDatabase(databaseFile, "arnab", null, null)
    if (database != null) {
        database.execSQL("create table t1(a,b);")
        database.execSQL(
            "insert into t1(a,b) values(?, ?);",
            arrayOf("one for the money", "two for the show")
        )
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SecretLockerTheme {
        Greeting("Android")
    }
}