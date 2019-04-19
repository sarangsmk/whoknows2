package gq.smktech.whoknows;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void registration(View v)
    {
      Intent i=new Intent(MainActivity.this,Registration_Activity.class);
      startActivity(i);
    }
    public void login(View v)
    {
        Intent i=new Intent(MainActivity.this,Login_Activity.class);
        startActivity(i);
    }
}
