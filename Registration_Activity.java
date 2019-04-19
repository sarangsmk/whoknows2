package gq.smktech.whoknows;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Registration_Activity extends AppCompatActivity {
    private EditText txtEmail;
    private EditText txtPassword,txtTags;
    public ImageView userPhoto;
    static int PReqCode =1;
    static int REQUESCODE=1;
    Uri pickedImageUri;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_);
        txtEmail=(EditText) findViewById(R.id.txtEmail);
        txtPassword=(EditText) findViewById(R.id.txtPassword);
        txtTags=(EditText) findViewById(R.id.txtTags);
        userPhoto=(ImageView) findViewById(R.id.userPhoto);
        firebaseAuth=FirebaseAuth.getInstance();




        userPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT >=22)
                {
                    checkAndRequestForPermission();
                }
                else
                {
                    openGallery();
                }
            }
        });







    }

    private void openGallery() {

        Intent galleryIntent=new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);

    }

    private void checkAndRequestForPermission() {

        if (ContextCompat.checkSelfPermission(Registration_Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(Registration_Activity.this,Manifest.permission.READ_EXTERNAL_STORAGE))
            {
                Toast.makeText(Registration_Activity.this,"Please accept requireed permissions",Toast.LENGTH_SHORT).show();
            }
            else
            {
                ActivityCompat.requestPermissions(Registration_Activity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PReqCode);
            }
        }
        else
        {
            openGallery();
        }
    }

    public void btnRegister(View v) {
        if (txtEmail.getText().toString().isEmpty() || txtPassword.getText().toString().isEmpty()) {
            Toast.makeText(Registration_Activity.this, "Fill All fields", Toast.LENGTH_LONG).show();

        } else {
            final ProgressDialog progressDialog = ProgressDialog.show(Registration_Activity.this, "Please wait...", "Processing", true);
            (firebaseAuth.createUserWithEmailAndPassword(txtEmail.getText().toString(), txtPassword.getText().toString())).addOnCompleteListener(new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {
                    progressDialog.dismiss();
                    if (task.isSuccessful()) {
                        Toast.makeText(Registration_Activity.this, "Registration Successfull", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Registration_Activity.this, Login_Activity.class);
                        startActivity(i);
                    } else {
                        Log.e("ERROR", task.getException().toString());
                        Toast.makeText(Registration_Activity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data!= null)
        {
            pickedImageUri=data.getData();

            userPhoto.setImageURI(pickedImageUri);

        }
    }
}
