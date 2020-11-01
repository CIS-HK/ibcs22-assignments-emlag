package edu.cis.ibcs_app.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import edu.cis.ibcs_app.Controllers.twitter.TwitterController;
import edu.cis.ibcs_app.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
         * Base code from android for this app
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        /*
         * End of base code from android for this app
         */


        //This is not great, for extra credit you can fix this so that network calls happen on a different thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        //TODO 1: Tweet something!
        TwitterController myC = new TwitterController(this);
        myC.postTweet("hello world"); //this will tweet to your account

    }

    public void goToMarketPlace(View v)
    {
        Intent nextScreen = new Intent(getBaseContext(), ListingsActivity.class);
        startActivity(nextScreen);
    }

}