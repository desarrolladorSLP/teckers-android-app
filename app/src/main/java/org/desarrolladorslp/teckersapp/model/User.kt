package org.desarrolladorslp.teckersapp.model
import android.net.Uri
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

data class User(var username: String?,var givenNameUser:String?, var familyNameUser:String?,var emailUser:String?,var idUser:String?, var photoUrlUser:Uri?) {
    constructor(acct: GoogleSignInAccount): this(acct.displayName,acct.givenName,acct.familyName,acct.email,acct.id,acct.photoUrl)
}
