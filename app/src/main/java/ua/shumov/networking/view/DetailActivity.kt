package ua.shumov.networking.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import ua.shumov.networking.R
import ua.shumov.networking.model.MovieModel

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val bundle = intent.extras
        if (bundle != null){
            // get movie data from MainActivity
            val movie = bundle.getParcelable<MovieModel>(MainActivity.DATA_MOVIE)
            Glide.with(this).load(movie?.image).into(imgMovieDetail)
            tvOverviewDetail.text = movie?.overview
            tvVote.text = movie?.vote_average

            // set Toolbar title
            title = movie?.title
        }
    }
}
