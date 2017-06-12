package org.example.kotlin.mixed

import android.content.Intent
import android.os.Bundle
import android.app.Activity
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Button
import android.widget.LinearLayout
import android.webkit.WebViewClient



class KotlinActivity : Activity() {

    internal val LOGTAG: String = KotlinActivity::class.java.simpleName

    // webview object must be initialized before to be used
    private var webview: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_main2)
        //var main_layout = findViewById(R.layout.activity_main2) as LinearLayout
        var main_layout = this.layoutInflater.inflate(R.layout.activity_main2, null) as LinearLayout
        Log.i(LOGTAG, "main_layout: " + main_layout)

        // get button
        val next = main_layout.findViewById(R.id.Button02) as Button
        Log.i(LOGTAG, "next - btn2: " + next)

        /*
        next.setOnClickListener( object : View.OnClickListener {
            override fun onClick(view: View?) {
                Log.e("grass", "onClick --> startActivityForResult...")
                val intent: Intent = Intent(view?.context, JavaActivity::class.java)
                startActivityForResult(intent, 0)
            }
        })
        */

        next.setOnClickListener({ view ->
                Log.e("grass", "onClick --> got to java activity...")
                val intent: Intent = Intent(view?.context, JavaActivity::class.java)
                startActivityForResult(intent, 0)
        })

//      val intent: Intent = Intent()    setResult(RESULT_OK, intent)    finish()

        next.setText(R.string.action_to_java_activity)

        var layout_params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)

        webview = WebView(this)
        // mutable var, and may be null
        webview?.layoutParams = layout_params

        webview?.setWebViewClient(WebViewClient())


        main_layout.addView(webview)

        // set main content layout
        setContentView(main_layout)
    }

    var ui_handler: Handler = Handler()

    override fun onResume() {
//
//        ui_handler.post( object : java.lang.Runnable {
//            override  fun run() {
//                Log.e(LOGTAG, "webview.loadUrl...")
//                webview?.loadUrl("v.qq.com")
//            }
//        })

        ui_handler.postDelayed(
                { ->
                    Log.e(LOGTAG, "webview.loadUrl...")
                    webview?.loadUrl("http://v.qq.com")
                }, 500)

        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_activity2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        var id = item?.itemId

        if (id == R.id.action_exit) {
            Log.e(LOGTAG, "action_exit_java...")
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
