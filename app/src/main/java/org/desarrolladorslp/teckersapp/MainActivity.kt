package org.desarrolladorslp.teckersapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.android.material.snackbar.Snackbar
import android.content.Intent
import android.widget.TextView
import com.google.android.gms.common.api.ApiException
import kotlinx.android.synthetic.main.activity_main.main_layout
import kotlinx.android.synthetic.main.activity_main.signInButton
import kotlinx.android.synthetic.main.activity_main.signOutButton
import org.desarrolladorslp.teckersapp.data.SharedApp
import org.desarrolladorslp.teckersapp.model.LoggedUser
import org.desarrolladorslp.teckersapp.model.User
import org.desarrolladorslp.teckersapp.service.APIEndpoint
import org.desarrolladorslp.teckersapp.service.LoginService
import org.desarrolladorslp.teckersapp.service.RetrofitManager
import org.desarrolladorslp.teckersapp.ui.messages.PriorityholderFragment.Companion.AUTH_ERROR
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private var googleSignInButton: SignInButton? = null

    val googleAuthClientId: String = BuildConfig.ApiKey
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        signInButton.setOnClickListener(this)
        signOutButton.setOnClickListener(this)
        googleSignInButton = findViewById(R.id.signInButton)

        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        auth = FirebaseAuth.getInstance()

        val isAuthError = intent.getBooleanExtra(AUTH_ERROR, false)
        if (isAuthError) {
            authorizationFailure()
        }
        if(SharedApp.data.LOG_OUT)
        {
            signOut()
            updateUI(null)
        }

    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    fun signOut() {
        auth.signOut()
        googleSignInClient.signOut().addOnCompleteListener(this) {
            updateUI(null)
        }
    }

    private fun revokeAccess() {
        auth.signOut()
        googleSignInClient.revokeAccess().addOnCompleteListener(this) {
            updateUI(null)
        }
    }

     private fun authorizationFailure()
    {

        auth.signOut()
        googleSignInClient.revokeAccess().addOnCompleteListener(this){
            val snackbar= Snackbar.make(main_layout,R.string.error_authentication , Snackbar.LENGTH_LONG)
            val textView = snackbar.view.findViewById(R.id.snackbar_text) as TextView
            textView.textSize = 20f
            snackbar.show()
        }

    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(this, NavigationMenuActivity::class.java)
            startActivity(intent)
        } else {
            signInButton.visibility = View.VISIBLE
            signOutButton.visibility = View.GONE
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                updateUI(null)

            }
        }
    }

    private fun firebaseAuthWithGoogle(googleSignInAccount: GoogleSignInAccount) {
        if(googleSignInAccount!=null) {
            val credential = GoogleAuthProvider.getCredential(googleSignInAccount.idToken, null)
            auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        val firebaseTokenId = user?.zze()?.zzd()

                        setProfileInformation(googleSignInAccount, firebaseTokenId)
                    } else {
                        Snackbar.make(main_layout, "Authentication Failed.", Snackbar.LENGTH_SHORT)
                            .show()
                        updateUI(null)
                    }

                }
        }
    }

    private fun setProfileInformation(googleSignInAccount: GoogleSignInAccount, firebaseTokenId: String?) {

        val loginService = RetrofitManager.instance()?.create(LoginService::class.java)

        val loginCall = loginService?.login("firebase", firebaseTokenId)

        loginCall?.enqueue(object : Callback<LoggedUser> {
            override fun onResponse(call: Call<LoggedUser>, response: Response<LoggedUser>) {
                APIEndpoint.setAccessToken(response.body()?.accessToken)
                user = User(googleSignInAccount)
                /*ROLE_PARENT=response.body()?.hasRole("ROLE_PARENT")!!
                ROLE_TECKER=response.body()?.hasRole("ROLE_TECKER")!!
                ROLE_MENTOR=response.body()?.hasRole("ROLE_MENTOR")!!
                ROLE_ADMINISTRATOR=response.body()?.hasRole("ROLE_ADMINISTRATOR")!!*/
                updateUI(auth.currentUser)
            }

            override fun onFailure(call: Call<LoggedUser>, t: Throwable) {
                authorizationFailure()
            }
        })
    }


    override fun onClick(v: View) {
        val i = v.id
        when (i) {
            R.id.signInButton -> signIn()
            R.id.signOutButton -> signOut()
            //R.id.disconnectButton -> revokeAccess()
        }
    }

    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001

    }
}
