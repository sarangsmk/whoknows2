package gq.smktech.whoknows;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

import de.hdodenhof.circleimageview.CircleImageView;


public class Ask_Activity extends AppCompatActivity implements activity_dialog.DialogListner{
    EditText etQuestion,etDescription,txtMobile;
    public TextView user;
    CircleImageView userPhoto;
    Button Post;
    String mobile;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Posts");
    DatabaseReference refProfile = FirebaseDatabase.getInstance().getReferenceFromUrl("https://who-knows-ccf3c.firebaseio.com/Users");
    ArrayList<profile>list;

    DatabaseReference dbAttendance;
    SharedPreferences sharedPreferences;

    String userid;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        mAuth = FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();

        user=(TextView)findViewById(R.id.user);
        userPhoto = findViewById(R.id.userPhoto);
        etQuestion=(EditText)findViewById(R.id.question);
        etDescription=(EditText)findViewById(R.id.description);
        txtMobile = findViewById(R.id.mobile);
        Post=(Button)findViewById(R.id.btnPost);

        user.setText(currentUser.getDisplayName());
        Glide.with(Ask_Activity.this).load(currentUser.getPhotoUrl()).into(userPhoto);


        sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
        userid = sharedPreferences.getString("userMail", null);


    }


    public void Post(View view)
    {
        String question=etQuestion.getText().toString();
        String description=etDescription.getText().toString();
        String mobile = txtMobile.getText().toString();
        //String uName=DashboardActivity.userName.toString();
        //uName=uName.replace(".", ",");
        String status="Open";
        String id;
        if(!TextUtils.isEmpty(question) && !TextUtils.isEmpty((description)))
        {
            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

            //ask for offline answer
            //openDialog();


            //String id=Ask.push().getKey();
            //Ask ask=new Ask(question,description,uName);
            //Ask.child(id).setValue(ask);
            id=ref.push().getKey();
            DatabaseReference dbuser = ref.child(id);
            dbuser.child("question").setValue(question);
            dbuser.child("description").setValue(description);
            dbuser.child("status").setValue(status);
            dbuser.child("postedBy").setValue(currentUser.getEmail());
            dbuser.child("postedOn").setValue(date);
            dbuser.child("dp").setValue(currentUser.getPhotoUrl().toString());
            dbuser.child("postId").setValue(id.toString());
            dbuser.child("mobile").setValue(mobile);


            Toast.makeText(this,"Question Posted as "+currentUser.getDisplayName(),Toast.LENGTH_LONG).show();
            Intent i=new Intent(Ask_Activity.this,DashboardActivity.class);
            i.putExtra("postId",id);
            startActivity(i);
        }
        else
        {
            Toast.makeText(this,"Fill all the fields",Toast.LENGTH_LONG).show();
        }
    }

    private void openDialog() {

        activity_dialog dialog = new activity_dialog();
        dialog.show(getSupportFragmentManager(),"Example dialog");


    }


    @Override
    public void applyTexts(String mob) {
        mobile = mob;
    }
}

