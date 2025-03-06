package com.example.prototipo5

import android.content.Intent
import android.os.Bundle
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.prototipo5.databinding.ActivityMainBinding
import com.example.prototipo5.databinding.ActivityMainMenuBinding
import com.google.android.material.bottomnavigation.BottomNavigationView



class MainMenu : AppCompatActivity() {

    private fun ReplaceFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.Frame_Layout, fragment).commit()


    }
    private lateinit var binding : ActivityMainMenuBinding

    private lateinit var bottomNavigationView: BottomNavigationView

    fun showPopupMenu(view: View) {
        val popup = PopupMenu(this, view)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.settings_menu, popup.menu) // Uses settings_menu.xml

        popup.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.OpUsuario -> {
                    Toast.makeText(this, "Usuario selected", Toast.LENGTH_SHORT).show()
                    true

                    val intent = Intent(this, UsuarioOp1::class.java)
                    startActivity(intent)
                    true
                }

                R.id.OpFavoritos -> {
                    Toast.makeText(this, "Favoritos selected", Toast.LENGTH_SHORT).show()
                    true
                    val intent = Intent(this, FavoritosOp2::class.java)
                    startActivity(intent)
                    true
                }
                R.id.OpContactos -> {
                    Toast.makeText(this, "Contactos selected", Toast.LENGTH_SHORT).show()
                    true
                    val intent = Intent(this, ContactosOp3::class.java)
                    startActivity(intent)
                    true
                }
                R.id.OpAyuda -> {
                    Toast.makeText(this, "Ayuda selected", Toast.LENGTH_SHORT).show()
                    true
                    val intent = Intent(this, AyudaOp4::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        popup.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)

        setContentView(binding.root)

        bottomNavigationView = findViewById(R.id.bottomNavi)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.Bottom_Settings -> {
                    ReplaceFragment(Settings())
                    true
                    showPopupMenu(bottomNavigationView) // Show the popup menu
                    true
                }
                R.id.Bottom_Chat -> {
                    ReplaceFragment(Chat())
                    true
                }
                R.id.Bottom_General -> {
                    ReplaceFragment(General())
                    true
                }
                else -> false
            }
        }
        ReplaceFragment(Chat())

        //enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        /* MENU DESPEGALBLE QUE AUN NO FUNCIONA

        val popUpsettings = findViewById<Button>(R.id.Bottom_Settings) as Button
        popUpsettings.setOnClickListener {view ->

            val popUpMenu = PopupMenu(this@MainMenu, view)
            popUpMenu.inflate(R.menu.settings_menu)

            popUpMenu.setOnMenuItemClickListener { menuItem ->

                when(menuItem.itemId){

                    R.id.OpUsuario ->{

                        Toast.makeText(this@MainMenu,"USUARIO",Toast.LENGTH_LONG).show()

                    true
                    }

                    R.id.OpFavoritos->{

                        Toast.makeText(this@MainMenu,"FAVORITOS",Toast.LENGTH_LONG).show()

                        true
                    }

                    R.id.OpContactos ->{

                        Toast.makeText(this@MainMenu,"CONTACTOS",Toast.LENGTH_LONG).show()

                        true
                    }

                    R.id.OpAyuda ->{

                        Toast.makeText(this@MainMenu,"AYUDA",Toast.LENGTH_LONG).show()

                        true
                    }

                    else -> {
                        false
                    }




                }

            }

            popUpMenu.show()
        } */

    }





}



