package gq.smktech.whoknows;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Profile_Activity extends AppCompatActivity {
    private TextView txtEmailaddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
        txtEmailaddress=(TextView)findViewById(R.id.txtComment);
    }
}
