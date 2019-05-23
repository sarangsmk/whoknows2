package gq.smktech.whoknows;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class CommentActivity extends AppCompatActivity {
    TextView txtQuestion,txtDescription,txtPostedBy,txtPostedOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        txtQuestion = this.findViewById(R.id.txtQuestion3);
        txtDescription = findViewById(R.id.txtDescription3);
        txtPostedBy = findViewById(R.id.txtUser3);
        txtPostedOn = findViewById(R.id.txtDate3);

        txtQuestion.setText("dfjhjhdf");

        SharedPreferences prefs = getSharedPreferences("questionDetails", MODE_PRIVATE);
        txtQuestion.setText(prefs.getString("question",null));
        txtDescription.setText(prefs.getString("description",null));
        txtPostedBy.setText(prefs.getString("postedOn",null));
        txtPostedOn.setText(prefs.getString("postedBy",null));
        Toast.makeText(this, prefs.getString("question",null).toString(), Toast.LENGTH_SHORT).show();
    }
}
