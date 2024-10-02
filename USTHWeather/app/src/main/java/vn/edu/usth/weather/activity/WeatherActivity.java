package vn.edu.usth.weather.activity;

import android.app.Activity;
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

        ViewPager pager = findViewById(R.id.pager);
        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        TabLayout tablayout = findViewById(R.id.tab_layout);
        tablayout.setupWithViewPager(pager);
        ImageView imageView = findViewById(R.id.usth_logo);
        new Refresh(imageView).execute("https://haitrieu.com/wp-content/uploads/2022/11/Logo-Truong-Dai-hoc-Khoa-hoc-va-Cong-nghe-Ha-Noi-VN-France-University.png");


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

    public class Refresh extends AsyncTask<String, Void, Bitmap> {
        private ImageView imageView;

        // Constructor to set the ImageView
        public Refresh(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(WeatherActivity.this, "Waiting", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String imageUrl = urls[0];
            Bitmap bitmap = null;
            try {
                URL url = new URL(imageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();
                int response = connection.getResponseCode();
                Log.i("USTHWeather", "The response is: " + response);
                InputStream is = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(is);
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if (result != null) {
                Toast.makeText(WeatherActivity.this, "Logo downloaded", Toast.LENGTH_SHORT).show();
                imageView.setImageBitmap(result); // Set the downloaded image to the ImageView
            } else {
                Toast.makeText(WeatherActivity.this, "Failed to download image", Toast.LENGTH_SHORT).show();
            }
        }
    }
}