package com.example.prototipo5.ui.login
import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.prototipo5.CrearUsuario
import com.example.prototipo5.MainMenu
import com.example.prototipo5.databinding.ActivityInicioBinding
import com.example.prototipo5.R

class Inicio() : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityInicioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val username = binding.username
        val password = binding.password
        val loading = binding.loading



        val login = findViewById<Button>(R.id.login)
        login.setOnClickListener {

            val intent3 = Intent(this, MainMenu::class.java)
            startActivity(intent3)

        }
        val contra = findViewById<Button>(R.id.contra)
        contra.setOnClickListener {

            val intent1 = Intent(this, NuevaContra::class.java)
            startActivity(intent1)
        }
        val tocrearusuario = findViewById<Button>(R.id.tocrearusuario)
        tocrearusuario.setOnClickListener {

            val intent2 = Intent(this, CrearUsuario::class.java)
            startActivity(intent2)

            loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
                .get(LoginViewModel::class.java)

            loginViewModel.loginFormState.observe(this@Inicio, Observer {
                val loginState = it ?: return@Observer

                // disable login button unless both username / password is valid
                login.isEnabled = loginState.isDataValid

                if (loginState.usernameError != null) {
                    username.error = getString(loginState.usernameError)
                }
                if (loginState.passwordError != null) {
                    password.error = getString(loginState.passwordError)
                }
            })



            username.afterTextChanged1 {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            password.apply {
                afterTextChanged1 {
                    loginViewModel.loginDataChanged(
                        username.text.toString(),
                        password.text.toString()
                    )
                }

                setOnEditorActionListener { _, actionId, _ ->
                    when (actionId) {
                        EditorInfo.IME_ACTION_DONE ->
                            loginViewModel.login(
                                username.text.toString(),
                                password.text.toString()
                            )
                    }
                    false
                }

                login.setOnClickListener {
                    loading.visibility = View.VISIBLE
                    loginViewModel.login(username.text.toString(), password.text.toString())


                }
            }

            fun updateUiWithUser(model: LoggedInUserView) {
                val welcome = getString(R.string.welcome)
                val displayName = model.displayName
                // TODO : initiate successful logged in experience
                Toast.makeText(
                    applicationContext,
                    "$welcome $displayName",
                    Toast.LENGTH_LONG
                ).show()
            }

            fun showLoginFailed(@StringRes errorString: Int) {
                Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
            }
        }

        fun Intent(editText: EditText, java: Class<NuevaContra>): Intent? {
            return null
        }


        fun updateUiWithUser(success: LoggedInUserView) {

        }

        fun showLoginFailed(error: Int) {
            TODO("Not yet implemented")
        }

        /**
         * Extension function to simplify setting an afterTextChanged action to EditText components.
         */
        fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
            this.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(editable: Editable?) {
                    afterTextChanged.invoke(editable.toString())
                }

                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            })
        }
    }

    fun updateUiWithUser(success: LoggedInUserView) {
        TODO("Not yet implemented")
    }

    fun showLoginFailed(error: Int) {
        TODO("Not yet implemented")
    }

}





