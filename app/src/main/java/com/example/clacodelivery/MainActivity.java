package com.example.clacodelivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.clacodelivery.adapter.SplashViewPage;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Timer timer;
    LinearLayout linearLayoutindicator;
    Button loginbtn;
    TextView skipbtn;

    SplashViewPage splashadapter;

    private int dotsCount;
    private ImageView[] dots;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        loginbtn = findViewById(R.id.loginbtn);
        skipbtn = findViewById(R.id.skip_btn);
        viewPager = findViewById(R.id.slide);
        linearLayoutindicator = findViewById(R.id.indicator);

        splashadapter = new SplashViewPage(this);
        viewPager.setAdapter(splashadapter);


        timer = new Timer();
        // Modify your TimerTask to update dots when page changes
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int nextPage = viewPager.getCurrentItem() + 1;
                        if (nextPage == splashadapter.getCount()) {
                            viewPager.setCurrentItem(0); // Go back to the first page
                            addDotsIndicator(0);
                        } else {
                            viewPager.setCurrentItem(nextPage);
                            addDotsIndicator(nextPage);
                        }
                    }
                });
            }
        }, 500, 2000); // Delay 0.5s, repeat every 2s
        // Add this to initialize dots
        dotsCount = splashadapter.getCount();
        dots = new ImageView[dotsCount];
        addDotsIndicator(0); // Initially at position 0


        skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SkipWebView.class);
                startActivity(i);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginWebView.class);
                startActivity(i);
            }
        });
//
//        registerbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(SplashScrollActivity.this, RegisterWebView.class);
//                startActivity(i);
//            }
//        });
    }

    private void addDotsIndicator(int position) {
        linearLayoutindicator.removeAllViews(); // Clear previous dots

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            if (i == position) {
                dots[i].setImageDrawable(getResources().getDrawable(R.drawable.activee_dot)); // Active dot drawable
            } else {
                dots[i].setImageDrawable(getResources().getDrawable(R.drawable.inactive_dot)); // Inactive dot drawable
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(8, 0, 8, 0); // Adjust margin as needed
            params.gravity = Gravity.CENTER;
            linearLayoutindicator.addView(dots[i], params);
        }


    }
}