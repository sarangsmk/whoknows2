package gq.smktech.whoknows;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentActivity extends AppCompatActivity {
    TextView txtQuestion,txtDescription,txtPostedBy,txtPostedOn;
    CircleImageView proPic;
    RecyclerView recyclerView;
    ArrayList<comment> list;
    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Posts");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        //getting current user
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        txtQuestion = this.findViewById(R.id.txtQuestion3);
        txtDescription = findViewById(R.id.txtDescription3);
        txtPostedBy = findViewById(R.id.txtUser3);
        txtPostedOn = findViewById(R.id.txtDate3);
        proPic = findViewById(R.id.proPic);
        recyclerView = findViewById(R.id.recyclerView);

        //getting selected Questions details
        Intent intent = getIntent();
        String question = intent.getStringExtra("question");
        String description = intent.getStringExtra("description");
        String postedBy = intent.getStringExtra("postedBy");
        String postedOn = intent.getStringExtra("postedOn");
        String dp = intent.getStringExtra("dp");

        //setting selected questions details
        Glide.with(CommentActivity.this).load(dp).into(proPic);
        txtQuestion.setText(question);
        txtDescription.setText(description);
        txtPostedBy.setText(postedBy);
        txtPostedOn.setText(postedOn);

        //comments
        list =new ArrayList<comment>();


    }
}
