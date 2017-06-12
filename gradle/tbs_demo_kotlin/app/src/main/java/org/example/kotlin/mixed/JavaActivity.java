package org.example.kotlin.mixed;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class JavaActivity extends Activity {

    final static String LOGTAG = JavaActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button next = (Button) findViewById(R.id.Button01);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent myIntent = new Intent(view.getContext(), KotlinActivity.class);
                //startActivityForResult(myIntent, 0);
                finish();

                Log.e(LOGTAG, "onClick --> bak to Kotlin activity...");


            }
        });
        next.setText(R.string.action_to_kotlin_activity);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_exit_java) {
            Log.e(LOGTAG, "action_exit_java...");
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
    
}
