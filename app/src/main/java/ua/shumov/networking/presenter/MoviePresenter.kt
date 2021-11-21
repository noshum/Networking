package ua.shumov.networking.presenter

import android.content.Context
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import ua.shumov.networking.R
import ua.shumov.networking.model.MovieModel
import ua.shumov.networking.utils.ViewMovie

class MoviePresenter {

    fun getMovie(context: Context, lifecycleScope: LifecycleCoroutineScope, view: ViewMovie){
        view.showLoading()
        val movieList = ArrayList<MovieModel>()
        lifecycleScope.async {
            delay(2000)
            val titles = context.resources.getStringArray(R.array.titles)
            val images = context.resources.getStringArray(R.array.images)
            val overView = context.resources.getStringArray(R.array.overview)
            val vote = context.resources.getStringArray(R.array.vote_average)

            for (index in titles.indices){
                val movie = MovieModel(
                    titles[index],
                    images[index],
                    overView[index],
                    vote[index]
                )
                movieList.add(movie)
            }
            view.hideLoading()
            view.receiveDataMovie(movieList)
        }
    }
}