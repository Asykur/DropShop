package asykurkhamid.dropshop.Activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import asykurkhamid.dropshop.R;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText etResetEmail;
    private Button btnResetEmail;
    private TextView tvBack;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        etResetEmail = findViewById(R.id.etResetEmail);
        btnResetEmail = findViewById(R.id.btnResetEmail);
        tvBack = findViewById(R.id.tvBack);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progress);

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnResetEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etResetEmail.getText().toString();
                if (email.isEmpty()){
                    etResetEmail.setError("Email Cannot Empty");
                }else if (!email.contains("@") && !email.contains(".")){
                    etResetEmail.setError("Invalid Email");
                }
                progressBar.setVisibility(View.VISIBLE);
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(ResetPasswordActivity.this, "We have sent you how to reset your password", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(ResetPasswordActivity.this, "Failed to request reset email", Toast.LENGTH_SHORT).show();
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        });

    }
}
