package gq.smktech.whoknows;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    private TextView user;
    public static String userName;
    FloatingActionButton fab;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Posts");
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    FirebaseDatabase mDatabase;
    FirebaseAuth.AuthStateListener mAuthListener;
    String userid;
    ListView listView;
    SharedPreferences sharedPreferences;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Who Knows"); // for set actionbar title
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);//Back button to parent activity
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
        user=(TextView)findViewById(R.id.user);
        listView = findViewById(R.id.listView);
        fab=(FloatingActionButton)findViewById(R.id.fab);
        TextView question = (TextView) findViewById(R.id.question);
        String questionFromDb = ref.child("who-knows-ccf3c").child("LTetfX5-Sm2ZqF8ae57").getKey();
        firebaseAuth = FirebaseAuth.getInstance();
        userid = firebaseAuth.getCurrentUser().getEmail();
        sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);


        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userMail",userid);
        editor.commit();

        Intent i=getIntent();
        userName=i.getStringExtra("Email");
        //user.setText(userName);

        //Reading Posts From Database
        FirebaseUser user = firebaseAuth.getCurrentUser();
        userid = user.getUid();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Toast.makeText(DashboardActivity.this, "Successfully signed in with: " + user.getEmail(), Toast.LENGTH_SHORT).show();

                } else {
                    // User is signed out
                    Toast.makeText(DashboardActivity.this, "Successfully signed out.", Toast.LENGTH_SHORT).show();
                }
                // ...
            }
        };

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void showData(DataSnapshot dataSnapshot) {
//        for(DataSnapshot ds : dataSnapshot.getChildren()){
//            posts post = new posts();
//            post.setQuestion(ds.child(userid).getValue(posts.class).getQuestion()); //set the Question
//            post.setQuestion(ds.child(userid).getValue(posts.class).getDescription());
////            uInfo.setEmail(ds.child(userID).getValue(UserInformation.class).getEmail()); //set the email
////            uInfo.setPhone_num(ds.child(userID).getValue(UserInformation.class).getPhone_num()); //set the phone_num
//
//            //display all the information
////            Log.d(TAG, "showData: name: " + uInfo.getName());
////            Log.d(TAG, "showData: email: " + uInfo.getEmail());
////            Log.d(TAG, "showData: phone_num: " + uInfo.getPhone_num());
////
//            ArrayList<String> array  = new ArrayList<>();
//            array.add(post.getQuestion());
//            array.add(post.getDescription());
//            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.list_content,array);
//            listView.setAdapter(adapter);
//        }
    }




    public void Question(View v)
    {
        Intent i=new Intent(DashboardActivity.this,Ask_Activity.class);
        startActivity(i);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawermenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.Ask)
        {
            Intent i=new Intent(DashboardActivity.this,Ask_Activity.class);
            startActivity(i);
        }
        else if(id == R.id.Help)
        {
            Toast.makeText(this, "logon to smktech.ml", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.about)
        {
            Toast.makeText(this, "logon to smktech.ml", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.exit)
        {
            finish();
        }
        else if(id == R.id.Profile)
        {
            Intent i=new Intent(DashboardActivity.this,Profile_Activity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
