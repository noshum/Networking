package ua.shumov.networking.utils

import ua.shumov.networking.model.MovieModel

interface ViewMovie {
    fun showLoading()
    fun hideLoading()
    fun receiveDataMovie(movie: List<MovieModel>)
}