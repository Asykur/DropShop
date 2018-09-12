package asykurkhamid.dropshop.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import asykurkhamid.dropshop.Config;
import asykurkhamid.dropshop.Pojo.DataUser;
import asykurkhamid.dropshop.R;

public class LoginActivity extends AppCompatActivity {
    private EditText email;
    private EditText pwd;
    private Button btnLogin,tvRegister;
    private TextView tvForgotpass;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private DatabaseReference uidReference;
    private SharedPreferences sharedPreferences;
    private String emailLogin;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = LoginActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME,MODE_PRIVATE);
        uidReference = FirebaseDatabase.getInstance().getReference();

//      Cek apabila sudah login maka langsung intent ke MainActivity, jika belum login dulu
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        email = findViewById(R.id.etEmail);
        pwd = findViewById(R.id.etPwd);
        btnLogin = findViewById(R.id.btLogin);
        tvRegister = findViewById(R.id.btnRegMember);
        tvForgotpass = findViewById(R.id.tvForgotPwd);
        progressDialog = new ProgressDialog(this);

        tvForgotpass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailLogin = email.getText().toString();
                password = pwd.getText().toString();
                if (emailLogin.isEmpty()) {
                    email.setError("Email Cannot Empty");
                    email.requestFocus();
                } else if (!emailLogin.contains("@") && !emailLogin.contains(".")) {
                    email.setError("Email Invalid");
                    email.requestFocus();
                } else if (password.isEmpty()) {
                    pwd.setError("Password Cannot Empty");
                    pwd.requestFocus();
                } else if (password.length() < 6) {
                    pwd.setError("Minimum 6 Characters");
                    pwd.requestFocus();
                } else {
                    progressDialog.setMessage("Logging in..");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    doLogin(emailLogin, password);
                }
            }
        });


        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


    private void doLogin(String emailLogin, String password) {
        mAuth.signInWithEmailAndPassword(emailLogin, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
//                    saveDataUser();
                    progressDialog.dismiss();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Incorrect email and password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    private void saveDataUser() {
//        userID = FirebaseAuth.getInstance().getCurrentUser();
//        String UID = userID.getUid();
//
//        uidReference.child("Users").child(UID).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()){
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString(Config.PHONE_NUMBER_SHARED_PREF, emailLogin);
//                    editor.putBoolean(Config.VERIFICATION_SHARED_PREF, true);
//                    editor.putBoolean(Config.REGISTER_SHARED_PREF, true);
//                    editor.commit();
//
//                    progressDialog.dismiss();
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//                    finish();
//                }else{
//
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString(Config.PHONE_NUMBER_SHARED_PREF, emailLogin);
//                    editor.putBoolean(Config.VERIFICATION_SHARED_PREF, true);
//                    editor.commit();
//
//                    progressDialog.dismiss();
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//                    finish();
//
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        }
    }
}
