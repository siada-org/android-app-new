package ru.student_app.student;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
//Импорт класа httpRequest
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ru.student_app.student.HttpRequest;


public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        //TODO: Сделать оттдельный файл запроса для навигатора
        String response = HttpRequest.get("http://student-app.ru/resources/helpers/scripts/json/recive_user_info.php").body();


        try {
            JSONObject jObject = null;
            jObject = new JSONObject(response);

            TextView NavHeaderUsername = (TextView) findViewById(R.id.menu_header_userName);
            ImageView NavHeaderUserImage = (ImageView) findViewById(R.id.menu_header_userImage);

            String UPhotoPath = jObject.getString("PERSONAL_PHOTO");
            String UName = jObject.getString("NAME");
            String UNickname = jObject.getString("TITLE");
            String ULname = jObject.getString("LAST_NAME");

            assert NavHeaderUsername != null;
            NavHeaderUsername.setText(UName+" "+UNickname+" "+ULname);

            Picasso.with(this)
                    .load("http://student-app.ru"+UPhotoPath)
                    .placeholder(R.drawable.logo_s)
                    .error(R.drawable.logo_s)
                    .into(NavHeaderUserImage);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {

            TextView textName = (TextView) findViewById(R.id.username);
            TextView textLastName = (TextView) findViewById(R.id.user_last_name);
            TextView textFirstName = (TextView) findViewById(R.id.user_first_name);

            String response = HttpRequest.get("http://student-app.ru/resources/helpers/scripts/json/recive_user_info.php").body();

            try {
                ImageView photoPlace = (ImageView) findViewById(R.id.photoPlace);

                JSONObject jObject = new JSONObject(response);

                String UPhotoPath = jObject.getString("PERSONAL_PHOTO");
                String Uname = jObject.getString("NAME");
                String ULname = jObject.getString("LAST_NAME");

                assert textName != null;
                assert textLastName != null;

                textLastName.setText(ULname);
                textName.setText(Uname);

                Picasso.with(this).load("http://student-app.ru"+UPhotoPath).into(photoPlace);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else if (id == R.id.nav_friends) {

        } else if (id == R.id.nav_pm) {

        } else if (id == R.id.nav_chat) {

        } else if (id == R.id.nav_events) {

        } else if (id == R.id.nav_works) {

        } else if (id == R.id.nav_map) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_search) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
