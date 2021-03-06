package arias.pablo.empezando.lay;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {


    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText repeatPasswordEditText;
    private Button registerButton;

    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        repeatPasswordEditText = (EditText) findViewById(R.id.repeatPasswordEditText);
        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });



        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void register() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            goLogIn();
                        } else {
                            showErrorMessage(task.getException().toString());
                        }
                    }
                });
    }

    private void showErrorMessage(String s) {
        Toast.makeText(getApplicationContext(), "Hay error: " + s, Toast.LENGTH_LONG).show();
    }

    private void goLogIn() {
        finish();
    }
}
