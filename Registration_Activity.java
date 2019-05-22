package gq.smktech.whoknows;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
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
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.*;

public class Registration_Activity extends AppCompatActivity {
    private EditText txtEmail,txtName;
    private EditText txtPassword,txtTags;
    public ImageView userPhoto;
    static int PReqCode =1;
    static int REQUESCODE=1;
    Uri pickedImageUri;
    private FirebaseAuth firebaseAuth;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
    String firebase_Image_Url;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://who-knows-ccf3c.appspot.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_);
        txtName = (EditText) findViewById(R.id.txtName);
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
        if (pickedImageUri==null || txtName.getText().toString().isEmpty() || txtEmail.getText().toString().isEmpty() || txtPassword.getText().toString().isEmpty()) {
            Toast.makeText(Registration_Activity.this, "Fill All fields", Toast.LENGTH_LONG).show();

        } else {
            final ProgressDialog progressDialog = ProgressDialog.show(Registration_Activity.this, "Please wait...", "Processing", true);
            (firebaseAuth.createUserWithEmailAndPassword(txtEmail.getText().toString(), txtPassword.getText().toString())).addOnCompleteListener(new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {
                    progressDialog.dismiss();
                    register();
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

    public void register()
    {
//        String user = txtName.getText().toString();
        uploadDp();

    }
    private String getFileExtension(Uri uri2)
    {
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return  mime.getExtensionFromMimeType(contentResolver.getType(uri2));
    }

    public void uploadDp()
    {
        //StorageReference childRef = storageRef.child(txtName.getText().toString());

        //uploading the image
        final UploadTask uploadTask;
       // UploadTask uploadTask = childRef.putFile(pickedImageUri);
       // String link = uploadTask.getResult().getMetadata().getReference().getDownloadUrl().toString();
        final StorageReference fileRefernce = storageRef.child(System.currentTimeMillis()+"."+getFileExtension(pickedImageUri));
//        uploadTask = fileRefernce.putFile(pickedImageUri);
//
//        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                Toast.makeText(Registration_Activity.this, fileRefernce.getDownloadUrl().toString(), Toast.LENGTH_SHORT).show();
//
//                    Uri uri = Uri.parse("http://" + fileRefernce.getDownloadUrl().toString()); // missing 'http://' will cause crashed
//                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                    startActivity(intent);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(Registration_Activity.this, "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
//            }
//        });


        if (pickedImageUri != null) {
            final StorageReference imgReference = fileRefernce.child(pickedImageUri.getLastPathSegment());
            uploadTask = imgReference.putFile(pickedImageUri);

            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    return imgReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri taskResult = task.getResult();
//                        Uri uri = Uri.parse(taskResult.toString()); // missing 'http://' will cause crashed
//                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                        startActivity(intent);
                        firebase_Image_Url = taskResult.toString();
//                        FriendlyMessage message = new FriendlyMessage(null, mUsername, taskResult.toString());
//                        mMessagesDatabaseReference.push().setValue(message);
                        String user = ref.push().getKey();
                        DatabaseReference dbuser = ref;
                        dbuser.child(user).child("Name").setValue(txtName.getText().toString());
                        dbuser.child(user).child("Email").setValue(txtEmail.getText().toString());
                        dbuser.child(user).child("Tags").setValue(txtTags.getText().toString());
                        dbuser.child(user).child("dp").setValue(firebase_Image_Url);
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
