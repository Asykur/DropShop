package asykurkhamid.dropshop.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import asykurkhamid.dropshop.R;

public class RegisterActivity extends AppCompatActivity {
    private EditText email;
    private EditText pwd;
    private EditText name;
    private EditText address;
    private EditText shop;
    private EditText telp;
    private Button btnReg;
    private TextView tvLogin;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private DatabaseReference reference;
    private String uid, completeName, userEmail, userPass, userShop, userAddress, userTelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.etEmailReg);
        pwd = findViewById(R.id.etPass);
        name = findViewById(R.id.regName);
        shop = findViewById(R.id.regShopName);
        address = findViewById(R.id.regAddress);
        telp = findViewById(R.id.regTelp);

        btnReg = findViewById(R.id.btnReg);
        tvLogin = findViewById(R.id.btnLogin);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userEmail = email.getText().toString();
                userPass = pwd.getText().toString();
                completeName = name.getText().toString();
                userAddress = address.getText().toString();
                userShop = shop.getText().toString();
                userTelp = telp.getText().toString();

                if (userEmail.isEmpty()) {
                    email.setError("Email Cannot Empty");
                    email.requestFocus();
                } else if (!userEmail.contains("@") && !userEmail.contains(".")) {
                    email.setError("Emain Invalid");
                    email.requestFocus();
                } else if (userPass.isEmpty()) {
                    pwd.setError("Password Cannot Empty");
                    pwd.requestFocus();
                } else if (userPass.length() < 6) {
                    pwd.setError("Minimum 6 Characters");
                    pwd.requestFocus();
                } else if (completeName.isEmpty()) {
                    name.setError("Your Name Cannot Empty");
                    name.requestFocus();
                } else if (userAddress.isEmpty()) {
                    address.setError("Your Address Cannot Empty");
                    address.requestFocus();
                } else if (userShop.isEmpty()) {
                    shop.setError("Shop Name Cannot Empty");
                    shop.requestFocus();
                } else if (userTelp.isEmpty()) {
                    telp.setError("Phone Cannot Empty");
                    telp.requestFocus();
                } else {
                    progressDialog.setMessage("Registering User..");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    doRegistration(completeName, userEmail, userPass, userAddress, userShop, userTelp);
                }

            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void doRegistration(final String name, final String mail, String password, final String address, final String shop, final String telp) {
        mAuth.createUserWithEmailAndPassword(mail, password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            String userID = mAuth.getCurrentUser().getUid();
                            DatabaseReference userReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);

                            Map data = new HashMap();
                            data.put("name", name);
                            data.put("email", mail);
                            data.put("shopName", shop);
                            data.put("address", address);
                            data.put("phone", telp);
                            userReference.setValue(data);

                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            intent.putExtra("uid", uid);
                            intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Error when registering", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}