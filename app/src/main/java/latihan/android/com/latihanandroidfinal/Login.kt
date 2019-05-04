package latihan.android.com.latihanandroidfinal

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.login.*
import latihan.android.com.latihanandroidfinal.helper.UserHelper

class Login: AppCompatActivity() {


    //deklarasi request code
    private val RC_SIGN_IN = 7
    //deklarasi untuk sign client
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)



        mAuth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        ).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        btn_login.setOnClickListener {
           val email: String = et_emailLogin.text.toString()
            val password: String = et_passwordLogin.text.toString()
            if (email.isEmpty()||password.isEmpty()){
                Toast.makeText(this, "please insert email & password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (!it.isSuccessful){
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    UserHelper(this).statusLogin = true
                    finish()
                    return@addOnCompleteListener

                }
            }.addOnFailureListener {
                Log.d("main", "Login failure ${it.message}")
                Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
            }
        }
        tv_register.setOnClickListener {
            val intent = Intent(applicationContext, Register::class.java)
            startActivity(intent)
        }
        go_sign.setOnClickListener {
            signIn()
        }
    }
    private fun signIn(){
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    private fun firebaseAuthWithGoogle(
        acct : GoogleSignInAccount
    ){
        Log.d("LOGIN", "FirebaseAuth"+ acct.id!!)
        val credential = GoogleAuthProvider.getCredential(acct.idToken,null)
        mAuth.signInWithCredential(credential).addOnCompleteListener(this){
                task ->
            if (task.isSuccessful){
                Log.d("LOGIN", "Sign In succes")
                val user = mAuth.currentUser
                updateUI(user)
            }else{
                Log.w("LOGIN", "SIGN IN ERROR", task.exception)
                Toast.makeText(this, "Sign in Failure", Toast.LENGTH_SHORT).show()
                updateUI(null)
            }
        }
    }
    fun updateUI(user: FirebaseUser?){
        if (user != null) {
            val intent =Intent (applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(
                    ApiException::class.java
                )
                firebaseAuthWithGoogle(account!!)
            }catch (e: ApiException){
                Log.w("LOGIN","LOGIN FAILED", e)
            }
        }
    }

//    override fun onStart() {
//        super.onStart()
//        val currentUser = mAuth.currentUser
//        updateUI(currentUser)
//    }
}