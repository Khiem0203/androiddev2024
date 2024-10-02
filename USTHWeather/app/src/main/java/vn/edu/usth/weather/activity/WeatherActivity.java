package vn.edu.usth.weather.activity;

import android.os.AsyncTask;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.content.Intent;

import vn.edu.usth.weather.adapter.HomeFragmentPagerAdapter;
import vn.edu.usth.weather.R;

public class WeatherActivity extends AppCompatActivity {
    public static final String TAG = "Weathering";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_weather);
        Log.i(TAG, "onCreate");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        new Refresh().execute();

        ViewPager pager = findViewById(R.id.pager);
        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        TabLayout tablayout = findViewById(R.id.tab_layout);
        tablayout.setupWithViewPager(pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bars, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            new Refresh().execute();
            return true;
        } else if (id == R.id.action_settings) {
            Intent intent = new Intent(this, PrefActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_music) {
            Intent intent = new Intent(this, MusicActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    private class Refresh extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            Toast.makeText(WeatherActivity.this, "Waiting", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(Void... voids) {
                String result = "";
                try {
                    URL url = new URL("http://ict.usth.edu.vn/wp-content/" + "uploads/usth/usthlogo.png");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);
                    connection.connect();
                    int response = connection.getResponseCode();
                    Log.i("USTHWeather", "The response is: " + response);
                    InputStream is = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    ImageView logo = (ImageView) findViewById(R.id.usth_logo);
                    logo.setImageBitmap(bitmap);
                    connection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute (String result){
                Toast.makeText(WeatherActivity.this, result, Toast.LENGTH_SHORT).show();
            }

        }
}