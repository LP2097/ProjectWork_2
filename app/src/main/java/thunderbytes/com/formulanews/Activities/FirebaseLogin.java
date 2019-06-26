package thunderbytes.com.formulanews.Activities;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import thunderbytes.com.formulanews.Broadcast.InternetReceiver;
import thunderbytes.com.formulanews.MainActivity;
import thunderbytes.com.formulanews.R;

public class FirebaseLogin extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123;
    private static final String TAG = "EMAIL_PASSWORD";
    private FirebaseAuth mAuth;
    EditText emailText, passwordText;
    GoogleApiClient mGoogleApiClient;
    GoogleSignInClient mGoogleSignInClient;
    SignInButton googleSignIn;
    private BroadcastReceiver InternetReceiver = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_layout);
        mAuth = FirebaseAuth.getInstance();
        emailText=findViewById(R.id.rEmailLabel);
        passwordText=findViewById(R.id.rPswdLabel);
        Button login = findViewById(R.id.signInBtn);

        //Check connessione internet
        InternetReceiver = new InternetReceiver();
        broadcastIntent();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString().trim();
                String password = passwordText.getText().toString().trim();

                //serie di controlli per verificare che i campi siano corretti
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(FirebaseLogin.this, "Email field can't be empty",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(FirebaseLogin.this, "Password field can't be empty",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length()<6){
                    Toast.makeText(FirebaseLogin.this, "Password field is too short",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                //metodo che verifica i due valori per fare il sign in
                mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(FirebaseLogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task task) {

                        if (task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(FirebaseLogin.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        Button signupBtn = findViewById(R.id.signupBtn);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FirebaseRegister.class));
            }
        });

        googleSignIn=findViewById(R.id.googleSignInBtn);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestServerAuthCode("45272773089-6ssj81jlmqjuumoq4jpcha1g7p53oif7.apps.googleusercontent.com")
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        googleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignIn();
            }
        });

    }

    private void googleSignIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onStart() {
        super.onStart();

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser!=null || account!=null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(InternetReceiver);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //gestisco i risultati del tentativo di login
        if (requestCode==RC_SIGN_IN){
            IdpResponse response = IdpResponse.fromResultIntent(data);
            //passo i dati che mi tornano alla funzione handle che gestisce il risultato ritornato e passa all'activity
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);

            if (resultCode==RESULT_OK){
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            }else {
                Log.d("LOGIN", "Errore durante il login");
            }

        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask){

        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            startActivity(new Intent(FirebaseLogin.this, MainActivity.class));
        } catch (ApiException e) {
            Log.d("GOOGLE", "Error during sign in "+e.getStatusCode());
        }

    }


    public void broadcastIntent() {
        registerReceiver(InternetReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }
}
