package com.imdb.movies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.imdb.movies.R
import com.imdb.movies.databinding.ItemMovieBinding
import com.imdb.movies.model.Movie



class MovieAdapter(private val mContext: Context, val movies: List<Movie>, private val mCallback: (view: View, position: Int, movie: Movie) -> Unit) : RecyclerView.Adapter<MovieAdapter.ItemHolder>() {

    private var lastPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemHolder {
        val binding: ItemMovieBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context), R.layout.item_movie, viewGroup, false)
        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {

        holder.binding.cardMovie.setOnClickListener { mCallback.invoke(it, position, movies[position]) }
    }



    override fun getItemCount(): Int = movies.count()
//    override fun getItemCount(): Int = 10




    class ItemHolder(itemBinding: ItemMovieBinding) : RecyclerView.ViewHolder(itemBinding.root) {
         val binding: ItemMovieBinding = itemBinding


    }


}