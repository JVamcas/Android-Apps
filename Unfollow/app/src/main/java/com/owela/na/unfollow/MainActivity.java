package com.owela.na.unfollow;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Properties prop = new Properties();
        String filePath = "src/src.main/src.resources";
        filePath = new File(filePath).getAbsolutePath() + "/twitter4j.properties";

        try {
            InputStream stream = new FileInputStream(filePath);
            getBaseContext().getAssets().open("app.properties");
            String cKey = prop.getProperty("cKey");
            String cSecret = prop.getProperty("cSecret");
            String callBackURL = prop.getProperty("callBackURL");
            String aToken = prop.getProperty("aToken");
            String aSecret = prop.getProperty("aSecret");
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey(cKey)
                    .setOAuthConsumerSecret(cSecret)
                    .setOAuthAccessTokenSecret(aSecret)
                    .setOAuthAccessToken(aToken);
            TwitterFactory twitterFactory = new TwitterFactory(cb.build());
            twitter = twitterFactory.getInstance();
            //requestToken = twitter.getOAuthRequestToken(callBackURL);
        } catch (Exception e) {

        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
