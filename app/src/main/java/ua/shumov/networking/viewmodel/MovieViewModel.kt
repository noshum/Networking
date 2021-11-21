package ua.shumov.networking.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ua.shumov.networking.R
import ua.shumov.networking.model.MovieModel

class MovieViewModel : ViewModel() {
    private var movieData: MutableLiveData<ArrayList<MovieModel>>? = null

    private val loading = MutableLiveData<Boolean>()

    fun loadMovie(application: Application) {
        if (movieData == null) {
            movieData = MutableLiveData<ArrayList<MovieModel>>()
            loading.value = true // UI Thread
            viewModelScope.launch(Dispatchers.IO) {
                delay(2000)
                val titles = application.resources.getStringArray(R.array.titles)
                val images = application.resources.getStringArray(R.array.images)
                val overView = application.resources.getStringArray(R.array.overview)
                val vote = application.resources.getStringArray(R.array.vote_average)

                val movieList = ArrayList<MovieModel>()
                for (index in titles.indices) {
                    val movie = MovieModel(
                        titles[index],
                        images[index],
                        overView[index],
                        vote[index]
                    )
                    movieList.add(movie)
                    movieData?.postValue(movieList) // Background Thread
                }
                loading.postValue(false)
            }
        }
    }

    fun getMovie(): LiveData<ArrayList<MovieModel>>? {
        return movieData
    }

    fun isLoading(): LiveData<Boolean> {
        return loading
    }
}
