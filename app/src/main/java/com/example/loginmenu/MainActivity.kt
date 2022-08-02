package com.example.loginmenu

import androidx.appcompat.app.AppCompatActivity
import com.android.volley.RequestQueue
import com.google.android.material.textfield.TextInputLayout
import android.os.Bundle
import com.example.loginmenu.R
import com.android.volley.toolbox.Volley
import android.content.Intent
import android.view.View
import com.example.loginmenu.MenuActividades
import com.example.loginmenu.MainActivity
import com.android.volley.toolbox.JsonArrayRequest
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONException
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError

class MainActivity : AppCompatActivity() {
    // Request
    var queue: RequestQueue? = null
    val BASEURL = "https://my-json-server.typicode.com/RobertoSuarez/loginmenu/users"

    // Controles
    var txtUsername: TextInputLayout? = null
    var txtPassword: TextInputLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtUsername = findViewById(R.id.txtUsername)
        txtPassword = findViewById(R.id.txtPassword)
        queue = Volley.newRequestQueue(this)
    }

    fun btnLogin(view: View?) {
        val username = txtUsername!!.editText!!.text.toString().trim { it <= ' ' }
        val password = txtPassword!!.editText!!.text.toString().trim { it <= ' ' }
        checkUser(username, password, this)
    }

    //                Toast.makeText(this, "Fatal, parace que no funca tu usuario o contraseña", Toast.LENGTH_SHORT).show();
    fun SendTableMenu(modo: String, usuario: String?) {
        println("Modo: $modo")
        val intent = Intent(this, MenuActividades::class.java)
        intent.putExtra("MODO", modo)
        intent.putExtra("USERNAME", usuario)
        startActivity(intent)
    }

    fun checkUser(username: String, password: String, activity: MainActivity) {
        val usuariosReques = JsonArrayRequest(
            Request.Method.GET,
            BASEURL,
            null,
            Response.Listener { response ->
                for (i in 0 until response.length()) {
                    try {
                        val usuario = response.getJSONObject(i)
                        // Revisamos si es el usuario
                        if (usuario.getString("username") == username && usuario.getString("password") == password) {
                            activity.SendTableMenu(
                                usuario.getString("modo"),
                                usuario.getString("username")
                            )
                            return@Listener
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
                Toast.makeText(
                    activity,
                    "Fatal, parace que no funca tu usuario o contraseña",
                    Toast.LENGTH_SHORT
                ).show()
            }
        ) {
            Toast.makeText(activity, "Fatal, parace que no funca el server", Toast.LENGTH_LONG)
                .show()
        }
        queue!!.add(usuariosReques)
    }

    internal inner class StatusUser(val isOk: Boolean, val modo: String)
}