package ua.shumov.networking.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_mvvm.*
import ua.shumov.networking.R
import ua.shumov.networking.viewmodel.MovieViewModel

class MVVMActivity : AppCompatActivity() {

    private var viewModel = MovieViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm)
        title = "Movie library"

        viewModel = ViewModelProviders
            .of(this, ViewModelProvider.NewInstanceFactory())
            .get(MovieViewModel::class.java)
        viewModel.loadMovie(application)

        viewModel.isLoading().observe(this, Observer { isLoading ->
            if (isLoading){
                pgMain.visibility = View.VISIBLE
            }else{
                pgMain.visibility = View.GONE
            }
        })

        viewModel.getMovie()?.observe(this, Observer { movieData ->
            if (movieData != null) {
                val adapter = MovieAdapter(movieData, this)
                rvMovie.layoutManager = LinearLayoutManager(this,
                    RecyclerView.VERTICAL, false)
                rvMovie.adapter = adapter
            }
        })
    }

}
