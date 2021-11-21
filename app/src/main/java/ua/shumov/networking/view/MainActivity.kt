package ua.shumov.networking.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ua.shumov.networking.R
import ua.shumov.networking.model.MovieModel

class MainActivity : AppCompatActivity() {

    companion object {
        const val DATA_MOVIE = "movies"
    }

    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            pgMain.visibility = View.VISIBLE
            val data = getMovie()
            delay(3000)
            setupRecycler(data)
        }
    }

    private fun setupRecycler(data: ArrayList<MovieModel>) {
        adapter = MovieAdapter(data, applicationContext)
        rvMovie.layoutManager =
            LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        rvMovie.adapter = adapter
        pgMain.visibility = View.GONE
    }

    private fun getMovie(): ArrayList<MovieModel> {
        val movieList = ArrayList<MovieModel>()
        lifecycleScope.async {
            val titles = resources.getStringArray(R.array.titles)
            val images = resources.getStringArray(R.array.images)
            val overView = resources.getStringArray(R.array.overview)
            val vote = resources.getStringArray(R.array.vote_average)

            for (index in titles.indices) {
                val movie = MovieModel(
                    titles[index],
                    images[index],
                    overView[index],
                    vote[index]
                )
                movieList.add(movie)
            }
        }
        return movieList
    }
}
