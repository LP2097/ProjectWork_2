package thunderbytes.com.formulanews.Activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import thunderbytes.com.formulanews.MainActivity;
import thunderbytes.com.formulanews.R;

public class FirebaseRegister extends AppCompatActivity {

    EditText emailText, passwordText, confirmPasswordtext;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);

        mAuth = FirebaseAuth.getInstance();

        emailText = findViewById(R.id.rEmailLabel);
        passwordText = findViewById(R.id.rPswdLabel);
        confirmPasswordtext = findViewById(R.id.rConfPswdLabel);

        Button regBtn = findViewById(R.id.registerBtn);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString().trim();
                String password1 = passwordText.getText().toString().trim();
                String password2 = confirmPasswordtext.getText().toString().trim();

                //vari controlli dei valori dei campi
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(FirebaseRegister.this, "Email field can't be empty",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password1)){
                    Toast.makeText(FirebaseRegister.this, "Password field can't be empty",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password2)){
                    Toast.makeText(FirebaseRegister.this, "Password field can't be empty",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password1.length()<6){
                    Toast.makeText(FirebaseRegister.this, "Password field is too short",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password2.length()<6){
                    Toast.makeText(FirebaseRegister.this, "Password field is too short",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password1.equals(password2)) {

                    String password = password1;

                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(FirebaseRegister.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                        finish();
                                    }else {

                                    }
                                }
                            });
/*
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(FirebaseRegister.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    } else {
                                        Toast.makeText(FirebaseRegister.this, "An error occurred",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
*/
                } else {

                    Toast.makeText(FirebaseRegister.this, "Passwords dosen't correspond",
                            Toast.LENGTH_SHORT).show();

                }


            }
        });
    }
}
