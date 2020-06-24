package com.example.lamma.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.lamma.Model.AccountsModel;
import com.example.lamma.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    //Declare
    private EditText mEmailEditTxt;
    private EditText mPasswordEditTxt;
    private EditText mConfirmPassEditTxt;
    private EditText mNameEditTxt;
    private RelativeLayout mConfirmPassRL;
    private RelativeLayout mPasswordRL;
    private RelativeLayout mEmailRL;
    private RelativeLayout mNameRL;
    private Button mSignUpBtn;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private FirebaseUser mUser;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mEmailEditTxt = findViewById(R.id.EmailEditTxt);
        mNameEditTxt = findViewById(R.id.NameEditTxt);
        mPasswordEditTxt = findViewById(R.id.PasswordEditTxt);
        mConfirmPassEditTxt = findViewById(R.id.ConfirmPassEditTxt);
        mSignUpBtn = findViewById(R.id.SignUpBtn);
        mProgressBar = findViewById(R.id.saveAndSendBtnProgressBar);
        mConfirmPassRL= findViewById(R.id.ConfirmPassRL);
        mEmailRL= findViewById(R.id.EmailRL);
        mNameRL= findViewById(R.id.NameRL);
        mPasswordRL= findViewById(R.id.PasswordRL);

        handleEditTxtFocus();

        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Extracting the pure text
                final String pureEmail = mEmailEditTxt.getText().toString().trim();
                String purePassword = mPasswordEditTxt.getText().toString();
                String pureConfirmPass = mConfirmPassEditTxt.getText().toString().trim();
                final String pureName = mNameEditTxt.getText().toString();

                mProgressBar.setVisibility(View.VISIBLE);
                mSignUpBtn.setVisibility(View.GONE);

                //checking if the extracted email and password are not empty
                if (!TextUtils.isEmpty(pureEmail) && !TextUtils.isEmpty(purePassword) && !TextUtils.isEmpty(pureConfirmPass)
                        && !TextUtils.isEmpty(pureName)) {
                    //creating account by inserting the DATA.
                    if(purePassword.equals(pureConfirmPass)){
                        mAuth.createUserWithEmailAndPassword(pureEmail, purePassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    mProgressBar.setVisibility(View.GONE);
                                    mSignUpBtn.setVisibility(View.VISIBLE);

                                    mUser = mAuth.getCurrentUser();
                                    AccountsModel acc = new AccountsModel(pureEmail,pureName,mUser.getUid());

                                    mDatabase.getReference("Accounts").child(mUser.getUid()).setValue(acc);
                                    Toast.makeText(getApplicationContext(),"Welcome to Lamma Family!",Toast.LENGTH_SHORT).show();
                                    sendToMain();

                                } else {
                                    mProgressBar.setVisibility(View.GONE);
                                    mSignUpBtn.setVisibility(View.VISIBLE);
                                    String errorMessage = task.getException().getMessage();
                                    Toast.makeText(getApplicationContext()
                                            , "Error: " + errorMessage, Toast.LENGTH_LONG).show();
                                }



                            }
                        });

                    }else {
                        mProgressBar.setVisibility(View.GONE);
                        mSignUpBtn.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(),"Password and Confirm Password doesn't match",Toast.LENGTH_LONG).show();
                    }

                }
                else{
                    {
                        mProgressBar.setVisibility(View.GONE);
                        mSignUpBtn.setVisibility(View.VISIBLE);

                        Toast.makeText(getApplicationContext(), "Please Fill all the above information first", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

    }
    private void handleEditTxtFocus(){
        mEmailRL.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focused) {
                if (focused)
                    mEmailRL.setAlpha(0.2f);
                else
                    mEmailRL.setAlpha(0.3f);
            }
        });
        mNameRL.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focused) {
                if (focused)
                    mNameRL.setAlpha(0.2f);
                else
                    mNameRL.setAlpha(0.3f);
            }
        });
        mPasswordRL.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focused) {
                if (focused)
                    mPasswordRL.setAlpha(0.2f);
                else
                    mPasswordRL.setAlpha(0.3f);
            }
        });
        mConfirmPassRL.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focused) {
                if (focused)
                    mConfirmPassRL.setAlpha(0.2f);
                else
                    mConfirmPassRL.setAlpha(0.3f);
            }
        });

    }
    public void sendToMain(){
        Intent x = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(x);
    }

}
