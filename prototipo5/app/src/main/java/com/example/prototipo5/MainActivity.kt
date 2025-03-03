package com.example.prototipo5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.prototipo5.ui.login.Inicio

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


            var toinicio = findViewById<Button>(R.id.toinicio)
            toinicio.setOnClickListener {

                val intent = Intent(this, Inicio::class.java)
                startActivity(intent)

            }

        }
    }
