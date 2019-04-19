package gq.smktech.whoknows;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView=(ImageView) findViewById(R.id.imglogo);
        textView=(TextView) findViewById(R.id.developer);




        Animation myanim= AnimationUtils.loadAnimation(this,R.anim.mytransition);
        imageView.startAnimation(myanim);
        textView.startAnimation(myanim);
        final Intent i=new Intent(this,Login_Activity.class);
        Thread timer=new Thread(){
            public void run(){
                try{
                    sleep(5000);

                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}
