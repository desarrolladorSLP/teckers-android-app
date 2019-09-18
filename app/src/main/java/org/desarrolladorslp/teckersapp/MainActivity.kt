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
import com.google.android.gms.common.api.ApiException
import kotlinx.android.synthetic.main.activity_main.main_layout
import kotlinx.android.synthetic.main.activity_main.signInButton
import kotlinx.android.synthetic.main.activity_main.signOutButton
import org.desarrolladorslp.teckersapp.model.LoggedUser
import org.desarrolladorslp.teckersapp.model.User
import org.desarrolladorslp.teckersapp.service.APIEndpoint
import org.desarrolladorslp.teckersapp.service.LoginService
import org.desarrolladorslp.teckersapp.service.AuthEndpoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private var googleSignInButton: SignInButton? = null
    private var profile: GoogleSignInAccount? = null


    val googleAuthClientId: String = BuildConfig.ApiKey
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signInButton.setOnClickListener(this)
        signOutButton.setOnClickListener(this)
        googleSignInButton = findViewById(R.id.signInButton);

        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        auth = FirebaseAuth.getInstance()
        signOut()

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

    private fun signOut() {
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

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(this, NavigationMenu::class.java)
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

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val firebaseTokenId = user?.zze()?.zzd()

                    setProfileInformation(acct, firebaseTokenId)
                } else {
                    Snackbar.make(main_layout, "Authentication Failed.", Snackbar.LENGTH_SHORT)
                        .show()
                    updateUI(null)
                }

            }
    }

    private fun setProfileInformation(acct: GoogleSignInAccount?, firebaseTokenId: String?) {

        if (acct != null) {

            var loginService = AuthEndpoint.instance()?.create(LoginService::class.java);

            var loginCall = loginService?.login("firebase", firebaseTokenId)

            loginCall?.enqueue(object : Callback<LoggedUser> {
                override fun onResponse(call: Call<LoggedUser>, response: Response<LoggedUser>) {
                    if (response.code() == 200) {
                        APIEndpoint.setAccessToken(response.body()?.accessToken)
                        user = User(acct)
                        updateUI(auth.currentUser)
                    }
                }

                override fun onFailure(call: Call<LoggedUser>, t: Throwable) {
                    val m = t.message;
                }
            })
        }
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
