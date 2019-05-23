package gq.smktech.whoknows;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Ask_Activity extends AppCompatActivity {
    EditText etQuestion,etDescription;
    public TextView user;
    Button Post;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Posts");
    DatabaseReference dbAttendance;
    SharedPreferences sharedPreferences;

    String userid;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);
        user=(TextView)findViewById(R.id.user);
        etQuestion=(EditText)findViewById(R.id.question);
        etDescription=(EditText)findViewById(R.id.description);
        Post=(Button)findViewById(R.id.btnPost);

        user.setText(DashboardActivity.userName);

        sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
        userid = sharedPreferences.getString("userMail", null);








    }
    public void Post(View view)
    {
        String question=etQuestion.getText().toString();
        String description=etDescription.getText().toString();
        String uName=DashboardActivity.userName.toString();
        uName=uName.replace(".", ",");
        String status="Open";
        String id;
        if(!TextUtils.isEmpty(question))
        {
            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

            //String id=Ask.push().getKey();
            //Ask ask=new Ask(question,description,uName);
            //Ask.child(id).setValue(ask);
            id=ref.push().getKey();
            DatabaseReference dbuser = ref.child(id);
            dbuser.child("question").setValue(question);
            dbuser.child("description").setValue(description);
            dbuser.child("status").setValue(status);
            dbuser.child("postedBy").setValue(userid);
            dbuser.child("postedOn").setValue(date);


            Toast.makeText(this,"Question Posted as "+uName,Toast.LENGTH_LONG).show();
            Intent i=new Intent(Ask_Activity.this,DashboardActivity.class);
            startActivity(i);
        }
        else
        {
            Toast.makeText(this,"Fill all the fields",Toast.LENGTH_LONG).show();
        }
    }




}

