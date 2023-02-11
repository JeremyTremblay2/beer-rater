package fr.iut.beerrater.presentation.beer_detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import fr.iut.beerrater.R
import fr.iut.beerrater.common.Constants.APP_NAME
import fr.iut.beerrater.common.Constants.DEFAULT_BEER_ID

@AndroidEntryPoint
class BeerDetailActivity : AppCompatActivity(), BeerDetailFragment.OnInteractionListener {
    private var beerId = DEFAULT_BEER_ID

    companion object {
        private const val BEER_ID = "fr.iut.beerrater.presentation.beer_detail.beer_id"

        fun getIntent(context: Context, beerId: Int) = Intent(
            context,
            BeerDetailActivity::class.java
        ).apply {
            putExtra(BEER_ID, beerId)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        beerId = intent.getIntExtra(BEER_ID, DEFAULT_BEER_ID)
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        setContentView(R.layout.toolbar_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null) {
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.fragment_container,
                    BeerDetailFragment.newInstance(beerId)
                )
                .commit()
        }
    }

    override fun onReviewDeleted(reviewId: Int) {
        Log.i(APP_NAME, "Review deleted!")
    }

    override fun onReviewEdited(reviewId: Int) {
        Log.i(APP_NAME, "Review edited!")
        //startActivityForResult
    }

    override fun onReviewAdded() {
        Log.i(APP_NAME, "Review added!")
        //startActivityForResult
    }
}