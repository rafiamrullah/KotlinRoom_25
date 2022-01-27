package com.example.kotlinroom_25

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.kotlinroom_25.room.Constant
import com.example.kotlinroom_25.room.Movie
import com.example.kotlinroom_25.room.MovieDb
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.log

class AddActivity : AppCompatActivity() {

    val db by lazy { MovieDb(this) }
    private var movieId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setupListener()
        setupView()
    }

    fun setupView() {
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
            Constant.TYPE_CREATE -> {
                btn_update.visibility = View.GONE
            }

            Constant.TYPE_READ -> {
                btn_save.visibility = View.GONE
                btn_update.visibility = View.GONE
                getMovie()
            }

            Constant.TYPE_UPDATE -> {
                btn_save.visibility = View.GONE
                getMovie()
            }
        }
    }

    private fun setupListener() {
        btn_save.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.movieDao().addMovie(
                    Movie(
                        0,
                        et_title.text.toString(),
                        et_desc.text.toString()
                    )
                )
                finish()
            }
        }

        btn_update.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.movieDao().updateMovie(
                    Movie(
                        movieId,
                        et_title.text.toString(),
                        et_desc.text.toString()
                    )
                )
                finish()
            }
        }
    }

    fun getMovie() {
        movieId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val movies = db.movieDao().getMovies(movieId)[0]
            et_title.setText(movies.title)
            et_desc.setText(movies.desc)
        }
    }
}
