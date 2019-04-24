package gq.smktech.whoknows;

import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DashboardActivity extends AppCompatActivity {
    private TextView user;
    public static String userName;
    FloatingActionButton fab;


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
        fab=(FloatingActionButton)findViewById(R.id.fab);

        Intent i=getIntent();
        userName=i.getStringExtra("Email");
        user.setText(userName);

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
