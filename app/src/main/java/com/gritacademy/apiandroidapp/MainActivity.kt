package com.gritacademy.apiandroidapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import org.json.JSONObject
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var emailTv: TextView
    lateinit var passwordTv: TextView
    lateinit var loginBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        emailTv = findViewById(R.id.editTextTextEmailAddress)
        passwordTv = findViewById(R.id.editTextTextPassword2)
        loginBtn = findViewById(R.id.button)
        val db = Firebase.firestore
        auth =FirebaseAuth.getInstance()
        val mainActv2 = Intent(this,MainActivity2::class.java)

        loginBtn.setOnClickListener(View.OnClickListener {
            if (emailTv.text.toString() != "" && passwordTv.text.toString() != "") {
                auth.signInWithEmailAndPassword(
                    emailTv.text.toString().trim(),
                    passwordTv.text.toString().trim()
                ).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val logedInUser = auth.currentUser

                        if (logedInUser != null) {
                            db.collection("users").document(logedInUser.uid).get()
                                .addOnSuccessListener { task ->
                                    Toast.makeText(
                                        this,
                                        "welcome ",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    startActivity(mainActv2)
                                }
                        }else{Toast.makeText(this,"idk",Toast.LENGTH_SHORT).show()}
                    } else {
                        Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "please fill in yoour info", Toast.LENGTH_SHORT).show()
            }
        })
    }
}