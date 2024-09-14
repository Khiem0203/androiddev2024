package vn.edu.usth.weather;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

public class WeatherActivity extends AppCompatActivity {

    private static final String TAG = "WeatherActivity";
    private HomeFragmentAdapter homeFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        WeatherAndForecastFragment weatherAndForecastFragment = new WeatherAndForecastFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, weatherAndForecastFragment).commit();

        ViewPager viewPager = findViewById(R.id.viewPager);
        HomeFragmentAdapter homeFragmentAdapter = new HomeFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(homeFragmentAdapter);


        Log.i(TAG,"Create");

    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Start");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Pause");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Resume");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Destroy");
    }
}