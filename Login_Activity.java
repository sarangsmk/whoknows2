package gq.smktech.whoknows;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.oob.SignUp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends AppCompatActivity {
    private EditText txtEmail;
    private EditText txtPassword;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        txtEmail=(EditText)findViewById(R.id.txtEmail);
        txtPassword=(EditText)findViewById(R.id.txtPassword);
        firebaseAuth=FirebaseAuth.getInstance();
    }

        public void SignUp(View v)
        {
            Intent i=new Intent(Login_Activity.this,Registration_Activity.class);
            startActivity(i);
        }

        public void forgotpassword(View v)
        {
            Toast.makeText(Login_Activity.this, "Coming Soon", Toast.LENGTH_LONG).show();
        }

    public void SignIn(View v) {
        if (txtEmail.getText().toString().isEmpty() || txtPassword.getText().toString().isEmpty())
        {
            Toast.makeText(Login_Activity.this, "Fill All fields", Toast.LENGTH_LONG).show();

        } else {
            final ProgressDialog progressDialog = ProgressDialog.show(Login_Activity.this, "Please wait...", "Processinng", true);
            (firebaseAuth.signInWithEmailAndPassword(txtEmail.getText().toString(), txtPassword.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if (task.isSuccessful()) {
                        Toast.makeText(Login_Activity.this, "Login Successfull", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Login_Activity.this, DashboardActivity.class);
                        i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                        startActivity(i);
                    } else {
                        Log.e("ERROR", task.getException().toString());
                        Toast.makeText(Login_Activity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }


}
