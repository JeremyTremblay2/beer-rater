package fr.iut.beerrater.presentation.beer_detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import fr.iut.beerrater.R
import fr.iut.beerrater.common.Constants.APP_NAME
import fr.iut.beerrater.common.Constants.DEFAULT_BEER_ID
import fr.iut.beerrater.common.Constants.NEW_REVIEW_ID
import fr.iut.beerrater.presentation.add_edit_review.AddEditReviewActivity
import fr.iut.beerrater.presentation.utils.showSnackBar

@AndroidEntryPoint
class BeerDetailActivity : AppCompatActivity(), BeerDetailFragment.OnInteractionListener {
    private var beerId = DEFAULT_BEER_ID

    companion object {
        private const val BEER_ID = "fr.iut.beerrater.presentation.beer_detail.activity_beer_id"

        fun getIntent(context: Context, beerId: Int) = Intent(
            context,
            BeerDetailActivity::class.java
        ).apply {
            putExtra(BEER_ID, beerId)
        }
    }

    private val addEditReviewLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result -> if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let {
                if (AddEditReviewActivity.wasReviewSaved(it)) {
                    val toast = Toast.makeText(this, getString(R.string.review_saved_toast_text), Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
                    toast.show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beerId = intent.getIntExtra(BEER_ID, DEFAULT_BEER_ID)

        setContentView(R.layout.toolbar_activity)
        setSupportActionBar(findViewById(R.id.toolbar_activity))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
        addEditReviewLauncher.launch(
            AddEditReviewActivity.newIntent(this, reviewId, beerId)
        )
    }

    override fun onReviewAdded() {
        Log.i(APP_NAME, "Review added!")
        addEditReviewLauncher.launch(
            AddEditReviewActivity.newIntent(this, NEW_REVIEW_ID, beerId)
        )
    }
}