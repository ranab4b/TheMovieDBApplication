package com.example.starzplayassignment.ui


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starzplayassignment.R
import com.example.starzplayassignment.adapters.GenaricRecyclerAdapter
import com.example.starzplayassignment.databinding.SearchActivityBinding
import com.example.starzplayassignment.model.RecyclerItemModel
import com.example.starzplaysamplelibrary.MultiSearchViewModel
import com.example.starzplaysamplelibrary.core.BaseActivity
import com.example.starzplaysamplelibrary.utils.Consts
import com.example.starzplaysamplelibrary.utils.Consts.EXTRA_ImagePAth
import com.example.starzplaysamplelibrary.utils.Consts.IS_Movie
import com.example.starzplaysamplelibrary.utils.Consts.IS_Tv
import kotlinx.android.synthetic.main.toolbar_ayout.view.*
import materialDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList


class SearchActivity : BaseActivity<MultiSearchViewModel>() {
    val searchViewModel by viewModel<MultiSearchViewModel>()
    private lateinit var binding: SearchActivityBinding

    var mMovieCarousel: ArrayList<RecyclerItemModel> = ArrayList()
    var mTvCarousel: ArrayList<RecyclerItemModel> = ArrayList()
    var mOtherCarousel: ArrayList<RecyclerItemModel> = ArrayList()

    override fun getViewModel(): MultiSearchViewModel {
        return searchViewModel;
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.search_activity
        )



        initUI()
        attachObserver()

    }

    private fun initUI() {


        if (::binding.isInitialized) {

            binding.root.mToolbar.title = "Search Movies"
            binding.etSearch.setOnEditorActionListener() { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                    //your code here
//                    if (v.text.toString().isEmpty()) {
//                        binding.etSearch.error = "Atleast One Character Required"
//                    } else {
//                        loginViewModel.login(v.text.toString())
//                    }

                    true
                }
                false
            }

            binding.etSearch.addTextChangedListener(object : TextWatcher {
                var lastChange: Long = 0
                override fun afterTextChanged(s: Editable) {


                    Handler().postDelayed(Runnable {
                        if (System.currentTimeMillis() - lastChange >= 500) {
                            if (s.toString().length >= 3) {
                                searchViewModel.MultiSearch(s.toString())
                            }
//                            else {
//                        binding.etSearch.error = "Atleast 3 Characters Required"
//                          }
                        }
                    }, 500)
                    lastChange = System.currentTimeMillis()


                }

                override fun beforeTextChanged(
                    s: CharSequence, start: Int,
                    count: Int, after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence, start: Int,
                    before: Int, count: Int
                ) {

                }
            })

            binding.rvMiviesList.layoutManager =
                LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            binding.rvTvList.layoutManager =
                LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            binding.rvOthersList.layoutManager =
                LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        }
    }


    private fun attachObserver() {


        searchViewModel.multiSearchLiveData.observe(this, Observer {
            it?.apply {
                loadingDialog?.hide()
                mMovieCarousel.clear()
                mTvCarousel.clear()
                mOtherCarousel.clear()
                //  login.setText(it.results!![0]?.title)
                for (n in it.results!!) {
                    when (n!!.mediaType) {
                        IS_Movie -> {
                            if (n.originalTitle != null && n.posterPath != null) {
                                mMovieCarousel.add(
                                    RecyclerItemModel(
                                        EXTRA_ImagePAth + n.posterPath!!,
                                        n.originalTitle!!
                                        , n.overview!!, n.mediaType!!
                                    )
                                )
                            }

                        }
                        IS_Tv -> {
                            if (n.originalName != null && n.posterPath != null) {
                                mTvCarousel.add(
                                    RecyclerItemModel(
                                        EXTRA_ImagePAth + n.posterPath!!,
                                        n.originalName!!
                                        , n.overview!!, n.mediaType!!
                                    )
                                )
                            }


                        }
                        else -> {
                            if (n.name != null && n.profilePath != null && !n.knownFor.isNullOrEmpty()) {
                                //here we can add multiple Known for of persons but currently i have picked up only 1st one
                                mOtherCarousel.add(
                                    RecyclerItemModel(
                                        EXTRA_ImagePAth + n.profilePath!! + "",
                                        n.name!!
                                        , n.knownFor!!.first()!!.overview!!, n.mediaType!!
                                    )
                                )
                            }

                        }
                    }
                }


                if (mMovieCarousel.size > 0) {
                    Collections.sort(mMovieCarousel, object : Comparator<RecyclerItemModel> {
                        override fun compare(
                            obj1: RecyclerItemModel,
                            obj2: RecyclerItemModel
                        ): Int {
                            // ## Ascending order
                            return obj1.mTitle.compareTo(
                                obj2.mTitle,
                                true
                            ) // To compare string values
                        }
                    })
                    binding.tvTitleMovies.setText("Movies (${mMovieCarousel.size})")
                    binding.tvTitleMovies.visibility = View.VISIBLE
                } else {
                    binding.tvTitleMovies.visibility = View.GONE
                }

                if (mTvCarousel.size > 0) {
                    Collections.sort(mTvCarousel, object : Comparator<RecyclerItemModel> {
                        override fun compare(
                            obj1: RecyclerItemModel,
                            obj2: RecyclerItemModel
                        ): Int {
                            // ## Ascending order
                            return obj1.mTitle.compareTo(
                                obj2.mTitle,
                                true
                            ) // To compare string values
                        }
                    })
                    binding.tvTitleTv.setText("Tv (${mTvCarousel.size})")
                    binding.tvTitleTv.visibility = View.VISIBLE
                } else {
                    binding.tvTitleTv.visibility = View.GONE
                }

                if (mOtherCarousel.size > 0) {
                    Collections.sort(mOtherCarousel, object : Comparator<RecyclerItemModel> {
                        override fun compare(
                            obj1: RecyclerItemModel,
                            obj2: RecyclerItemModel
                        ): Int {
                            // ## Ascending order
                            return obj1.mTitle.compareTo(
                                obj2.mTitle,
                                true
                            ) // To compare string values
                        }
                    })
                    binding.tvTitleOthers.setText("XXX (${mOtherCarousel.size})")
                    binding.tvTitleOthers.visibility = View.VISIBLE
                } else {
                    binding.tvTitleOthers.visibility = View.GONE
                }




                binding.rvMiviesList.adapter = GenaricRecyclerAdapter(mMovieCarousel) {
                    startActivity(
                        Intent(
                            this@SearchActivity,
                            DetailActivity::class.java
                        ).also { intent ->
                            intent.putExtra(Consts.EXTRA_PRODUCT_CONTENT, mMovieCarousel.get(it))
                        })

                }
                binding.rvTvList.adapter = GenaricRecyclerAdapter(mTvCarousel) {
                    startActivity(
                        Intent(
                            this@SearchActivity,
                            DetailActivity::class.java
                        ).also { intent ->
                            intent.putExtra(Consts.EXTRA_PRODUCT_CONTENT, mTvCarousel.get(it))
                        })

                }
                binding.rvOthersList.adapter = GenaricRecyclerAdapter(mOtherCarousel)
                {

                    startActivity(
                        Intent(
                            this@SearchActivity,
                            DetailActivity::class.java
                        ).also { intent ->
                            intent.putExtra(Consts.EXTRA_PRODUCT_CONTENT, mOtherCarousel.get(it))
                        })

                }

                if (mMovieCarousel.size == 0 && mTvCarousel.size == 0 && mOtherCarousel.size == 0) {
                    materialDialog("Sorry No Results Found")
                    binding.tvNoSearchResults.visibility = View.VISIBLE
                } else {
                    binding.tvNoSearchResults.visibility = View.GONE
                }
            }
        })


    }
}
