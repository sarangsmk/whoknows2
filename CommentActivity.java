package gq.smktech.whoknows;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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

import org.w3c.dom.Comment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import io.opencensus.tags.Tag;

public class CommentActivity extends AppCompatActivity {
    TextView txtQuestion,txtDescription,txtPostedBy,txtPostedOn,txtComment;
    CircleImageView proPic;
    RecyclerView recyclerView;
    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;
    commentAdapter adapter;
    String postId,date,id;
    ArrayList<comment> list;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://who-knows-ccf3c.firebaseio.com/Comments");
    DatabaseReference ref2;

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
        recyclerView = findViewById(R.id.recyclerView2);
        txtComment = findViewById(R.id.txtComment);

        //getting selected Questions details
        Intent intent = getIntent();
        String question = intent.getStringExtra("question");
        String description = intent.getStringExtra("description");
        String postedBy = intent.getStringExtra("postedBy");
        String postedOn = intent.getStringExtra("postedOn");
        String dp = intent.getStringExtra("dp");
        postId = intent.getStringExtra("postId");

        //setting selected questions details
        Glide.with(CommentActivity.this).load(dp).into(proPic);
        txtQuestion.setText(question);
        txtDescription.setText(description);
        txtPostedBy.setText(postedBy);
        txtPostedOn.setText(postedOn);

        Toast.makeText(this, "PoastId->"+postId, Toast.LENGTH_SHORT).show();
        String url = "https://who-knows-ccf3c.firebaseio.com/Comments/"+postId;

        ref2 = FirebaseDatabase.getInstance().getReferenceFromUrl(url);


        //comments
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<comment>();
//        comment cmn=new comment();
//        cmn.setComment("Test");
//        cmn.setPostedOn("12-04-1999");
//        cmn.setPostedBy("smk");
//        list.add(cmn);
//        adapter=new commentAdapter(CommentActivity.this,list);
//        recyclerView.setAdapter(adapter);

        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                            System.out.println(dataSnapshot1.child("comment").getValue());
                            comment c = dataSnapshot1.getValue(comment.class);
                            list.add(c);

                }
                adapter=new commentAdapter(CommentActivity.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(CommentActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });




    }

    public void addComment(View view)
    {
        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        if(!TextUtils.isEmpty(txtComment.getText()))
        {
            id=ref.push().getKey();
            DatabaseReference ref2 = ref.child(postId).child(id);
            ref2.child("comment").setValue(txtComment.getText().toString());
            ref2.child("postedBy").setValue(currentUser.getEmail());
            ref2.child("postedOn").setValue(date);
            ref2.child("dp").setValue(currentUser.getPhotoUrl().toString());
//            ref.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    if(dataSnapshot.hasChild(postId))
//                    {
//
//                    }
//                    else
//                    {
//                        ref.child(postId).child(id).child("comment").setValue(txtComment.getText());
//                        ref.child(postId).child(id).child("postedBy").setValue(currentUser.getEmail());
//                        ref.child(postId).child(id).child("postedOn").setValue(date);
//                        ref.child(postId).child(id).child("dp").setValue(currentUser.getPhotoUrl().toString());
//
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });
            Toast.makeText(this, "Thanks For your Help", Toast.LENGTH_SHORT).show();
            txtComment.setText("");
            Intent i=new Intent(CommentActivity.this,DashboardActivity.class);
            startActivity(i);

        }
        else
        {
            Toast.makeText(this, "Please type a comment", Toast.LENGTH_SHORT).show();
        }
    }
}
