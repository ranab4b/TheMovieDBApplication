package com.example.starzplayassignment.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.starzplaysamplelibrary.utils.Consts
import com.example.starzplayassignment.R
import com.example.starzplayassignment.databinding.ActivityDetailBinding
import com.example.starzplayassignment.model.RecyclerItemModel
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.content_detail.view.*
import kotlinx.android.synthetic.main.toolbar_ayout.view.*

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    lateinit var data: RecyclerItemModel

    var requestOptions = RequestOptions()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_detail
        )
        setSupportActionBar(binding.root.mToolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        binding.root.fab.setOnClickListener { view ->
            startActivity(Intent(this@DetailActivity, MediaPlayerActivity::class.java))
        }

        getDatafromIntent()
        upDateUi()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item)
    }
    private fun upDateUi() {
        requestOptions.placeholder(R.drawable.place_holder)
        requestOptions.error(R.drawable.place_holder)

        if (::data.isInitialized) {

            supportActionBar?.title = data.mTitle
            Glide.with(this@DetailActivity).applyDefaultRequestOptions(requestOptions).load(data.imageUrl).into(
                binding.root.mImageView
            )



            when (data.type) {
                Consts.IS_Movie -> {
                    binding.root.overview.text = data.description
                    binding.root.fab.show()
                }
                Consts.IS_Tv -> {
                    binding.root.overview.text = data.description
                    binding.root.fab.show()
                }
                else -> {
                    binding.root.overview.text = "Well Known For: ${data.description}"
                    binding.root.fab.hide()

                }
            }
        }
    }

    private fun getDatafromIntent() {
        if (intent.extras?.containsKey(Consts.EXTRA_PRODUCT_CONTENT) == true) {
            data = intent.getParcelableExtra(Consts.EXTRA_PRODUCT_CONTENT)!!

        } else {
            super.onBackPressed()
        }
    }
}
