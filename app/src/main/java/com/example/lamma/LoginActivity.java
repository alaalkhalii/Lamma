package com.example.lamma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    //Variable declaration
    private EditText mEmailETxt;
    private EditText mPasswordETxt;
    private Button mLoginButton;
    private TextView mSignupTxtV;
    private ProgressBar mProgressBar;
    private FirebaseAuth mAuth;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Variable initialization
        mEmailETxt = findViewById(R.id.EmailEditTxt);
        mPasswordETxt = findViewById(R.id.PasswordEditTxt);
        mLoginButton = findViewById(R.id.LoginBtn);
        mSignupTxtV = findViewById(R.id.toSignUpTxtV);
        mProgressBar = findViewById(R.id.saveAndSendBtnProgressBar);
        mAuth = FirebaseAuth.getInstance();
        mContext=LoginActivity.this;

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Extracting the pure text
                final String pureEmail = mEmailETxt.getText().toString().trim();
                String purePassword = mPasswordETxt.getText().toString();
                mProgressBar.setVisibility(View.VISIBLE);
                mLoginButton.setVisibility(View.GONE);
                //checking if the extracted email and password are not empty
                if (!TextUtils.isEmpty(pureEmail) && !TextUtils.isEmpty(purePassword)) {
                    //comparing the inputs with the data in firebase then checking correctness
                    mAuth.signInWithEmailAndPassword(pureEmail, purePassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            if (task.isSuccessful()) {
                                mProgressBar.setVisibility(View.GONE);
                                mLoginButton.setVisibility(View.VISIBLE);

                                    Intent x = new Intent(LoginActivity.this,MainActivity.class);
                                    startActivity(x);
                            }else {
                                mProgressBar.setVisibility(View.GONE);
                                mLoginButton.setVisibility(View.VISIBLE);
                                String errorMessage = task.getException().getMessage();
                                Toast.makeText(LoginActivity.this, "Error: " + errorMessage, Toast.LENGTH_LONG).show();
                                vibrate(100);
                            }

                        }
                    });
                }
                else {
                    mProgressBar.setVisibility(View.GONE);
                    mLoginButton.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Please enter All Data", Toast.LENGTH_SHORT).show();
                    vibrate(100);

                }



            }
        });
    }
    private void vibrate(int millisecond) {
        Vibrator vibrator=(Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(millisecond);
    }

}
