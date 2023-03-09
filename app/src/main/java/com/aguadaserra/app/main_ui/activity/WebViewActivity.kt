package com.aguadaserra.app.main_ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.aguadaserra.app.R
import kotlinx.android.synthetic.main.activity_webview.*


class WebViewActivity : AppCompatActivity() {

    private var pdfLink: String = ""

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        setSupportActionBar(toolbar)

        toolbarTitle.text = "Termos de Uso"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        if (intent.extras != null) {

            pdfLink = intent.getStringExtra("url").toString()

            pdfView?.settings?.javaScriptEnabled = true
            pdfView?.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=$pdfLink")
            pdfView?.settings?.builtInZoomControls = true

        }

    }



//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.actionbar_menu_share, menu)
//        return true
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
//            R.id.action_share -> {
//
//                try {
//                    val shareIntent = Intent(Intent.ACTION_SEND)
//                    shareIntent.type = "text/plain"
//                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Instituto Blindar")
//                    val shareMessage: String = "https://drive.google.com/viewerng/viewer?embedded=true&url=$pdfLink"
//                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
//                    startActivity(Intent.createChooser(shareIntent, "Escolha um"))
//                } catch (e: Exception) {
//                    //e.toString();
//                }
//                // Do something
//                true
//            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
