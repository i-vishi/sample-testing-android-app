package com.vishalgaur.testinguserdata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vishalgaur.testinguserdata.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityDetailBinding.inflate(layoutInflater)

        val args = intent.extras

        binding.detailName.text = args?.getString("userName")
        binding.detailEmail.text = args?.getString("userEmail")
        binding.detailMobile.text = args?.getString("userMob")
        binding.detailBio.text = args?.getString("userBio")
        setContentView(binding.root)
        super.onCreate(savedInstanceState)

        binding.detailGoBackBtn.setOnClickListener {
            onBackPressed()
        }
    }
}