package com.rquickime

import android.app.Activity
import android.app.StatusBarManager
import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.settings).setOnClickListener {
            try {
                startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS))
            } catch (_: ActivityNotFoundException) {
                Toast.makeText(this, R.string.settings_missing, Toast.LENGTH_LONG).show()
            }
        }
        if (Build.VERSION.SDK_INT >= 33) {
            findViewById<Button>(R.id.add_tile).apply {
                visibility = View.VISIBLE
                setOnClickListener {
                    getSystemService(StatusBarManager::class.java).requestAddTileService(
                        ComponentName(this@MainActivity, ImeTileService::class.java),
                        getString(R.string.tile_name),
                        Icon.createWithResource(this@MainActivity, R.drawable.ic_keyboard),
                        mainExecutor
                    ) { result ->
                        val message = when (result) {
                            StatusBarManager.TILE_ADD_REQUEST_RESULT_TILE_ALREADY_ADDED,
                            StatusBarManager.TILE_ADD_REQUEST_RESULT_TILE_ADDED -> R.string.already_added
                            else -> R.string.use_edit
                        }
                        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
