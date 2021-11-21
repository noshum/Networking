package ua.shumov.networking.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*
import ua.shumov.networking.R
import ua.shumov.networking.model.MovieModel

class MovieAdapter(private val movieList: List<MovieModel>, private val context: Context) :
    RecyclerView.Adapter<MovieAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return VH(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(movieList[position], context)
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var dataMovie: MovieModel

        init {
            itemView.btnDetail.setOnClickListener(this)
        }

        fun onBind(movie: MovieModel, context: Context) {
            dataMovie = movie
            itemView.tvTItile.text = movie.title
            itemView.tvOverView.text = movie.overview
            Glide.with(context).load(movie.image).into(itemView.imgMovie)
        }

        override fun onClick(v: View?) {
            val intent = Intent(itemView.context, DetailActivity::class.java)
            intent.putExtra(MainActivity.DATA_MOVIE, dataMovie)
            itemView.context.startActivity(intent)
        }

    }
}