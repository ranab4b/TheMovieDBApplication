package com.example.starzplayassignment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.starzplayassignment.R
import com.example.starzplayassignment.model.RecyclerItemModel

class GenaricRecyclerAdapter(
    val list: ArrayList<RecyclerItemModel>,
    private val mListener: (Int) -> Unit
) :
    RecyclerView.Adapter<GenaricRecyclerAdapter.ViewHolder>() {
    var requestOptions = RequestOptions()
    init {
        requestOptions.placeholder(R.drawable.place_holder)
        requestOptions.error(R.drawable.place_holder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            position.let { it1 -> mListener.invoke(it1) }
        }
        Glide.with(holder.itemView.context).applyDefaultRequestOptions(requestOptions).load(list.get(position).imageUrl).into(holder.mImageView)
        holder.mTextView.setText(list.get(position).mTitle)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mImageView = itemView.findViewById(R.id.mImageView) as ImageView
        val mTextView = itemView.findViewById(R.id.mTextView) as TextView


    }
}