package gq.smktech.whoknows;

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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Ask_Activity extends AppCompatActivity {
    EditText etQuestion,etDescription;
    public TextView user;
    Button Post;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference dbAttendance;


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

            //String id=Ask.push().getKey();
            //Ask ask=new Ask(question,description,uName);
            //Ask.child(id).setValue(ask);
            id=ref.push().getKey();
            DatabaseReference dbuser = ref.child(id);
            dbuser.child(uName);
            dbuser.child(uName).child("Question").setValue(question);
            dbuser.child(uName).child("Description").setValue(description);
            dbuser.child(uName).child("Status").setValue(status);


            Toast.makeText(this,"Question Posted from "+uName,Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"Fill all the fields",Toast.LENGTH_LONG).show();
        }
    }




}

