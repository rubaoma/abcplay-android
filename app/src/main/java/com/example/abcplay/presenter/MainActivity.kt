package com.example.abcplay.presenter

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.abcplay.api.ApiInterface
import com.example.abcplay.api.RetrofitInstance
import com.example.abcplay.databinding.ActivityMainBinding
import com.example.abcplay.model.SignInBody
import com.example.abcplay.model.Token
import com.example.abcplay.model.UserBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import kotlin.coroutines.coroutineContext


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var email: String = ""
    private var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(this.layoutInflater)
        setContentView(this.binding.root)

//        binding.emailInputText.editText?.text.toString()
//        binding.passwordInput.editText?.text.toString()

        binding.buttonLogin.setOnClickListener {
            if (binding.emailInputText.editText?.text.toString().isBlank() || binding.passwordInput.editText?.text.toString().isBlank()){
                Toast.makeText(
                        this,
                        "Informe email e senha!",
                        Toast.LENGTH_SHORT
                ).show()
            }else{
                signin(binding.emailInputText.editText?.text.toString(), binding.passwordInput.editText?.text.toString())
            }

        }

    }

    private fun signin(email: String, password: String) {
        val retIn = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        val signInInfo = SignInBody(email, password)

        retIn.signin(signInInfo).enqueue(object : retrofit2.Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 200) {

                    val token = response.body()
                    val intent = Intent(
                            this@MainActivity,
                            WelcomeActivity::class.java
                    )
                    startActivity(intent)

                    Toast.makeText(
                            this@MainActivity,
                            "Login sucess",
                            Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                        this@MainActivity,
                        t.message,
                        Toast.LENGTH_SHORT
                ).show()
            }

        })
    }


    private fun signup(
            email: String,
            password: String,
            name: String,
            type: String
            ) {
        val retIn = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        val registerInfo = UserBody(email, password, name, type="estudante")

        retIn.registerUser(registerInfo).enqueue(object :
                retrofit2.Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 201) {
                    Toast.makeText(
                            this@MainActivity,
                            "Cadastrado com sucesso",
                            Toast.LENGTH_SHORT
                    ).show()
                }

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                        this@MainActivity,
                        t.message,
                        Toast.LENGTH_SHORT
                ).show()
            }

        })
    }


}


