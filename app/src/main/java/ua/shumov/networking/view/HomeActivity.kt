package ua.shumov.networking.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import ua.shumov.networking.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnNoPattern.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

        btnMVVM.setOnClickListener {
            startActivity(Intent(this, MVVMActivity::class.java))
        }
    }
}
