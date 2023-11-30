package com.imdb.movies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.imdb.movies.R
import com.imdb.movies.databinding.ItemMovieBinding
import com.imdb.movies.model.Movie
import com.imdb.movies.util.loadImage


class FavMovieAdapter(private val mContext: Context, val movies: MutableList<Movie>, private val mCallback: (view: View, position: Int, movie: Movie) -> Unit) : RecyclerView.Adapter<FavMovieAdapter.ItemHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemHolder {
        val binding: ItemMovieBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context), R.layout.item_movie, viewGroup, false)
        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.binding.movie = movies[position]
        holder.binding.imvMovie.loadImage(movies[position].image?.imageUrl)

        holder.binding.btnFav.setOnClickListener {
//            movies[position].updateFavourite(!movies[position].favourite)
            movies[position].favourite = !movies[position].favourite
            mCallback.invoke(it, position, movies[position])
        }
        holder.binding.cardMovie.setOnClickListener { mCallback.invoke(it, position, movies[position]) }
    }

    override fun getItemCount(): Int = movies.count()
//    override fun getItemCount(): Int = 10

    class ItemHolder(itemBinding: ItemMovieBinding) : RecyclerView.ViewHolder(itemBinding.root) {
         val binding: ItemMovieBinding = itemBinding

    }


}