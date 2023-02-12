package fr.iut.beerrater.presentation.add_edit_review

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import fr.iut.beerrater.R
import fr.iut.beerrater.common.Constants
import fr.iut.beerrater.common.Constants.DEFAULT_BEER_ID
import fr.iut.beerrater.common.Constants.NEW_REVIEW_ID
import fr.iut.beerrater.databinding.FragmentAddEditReviewBinding
import fr.iut.beerrater.presentation.beer_detail.BeerDetailActivity
import fr.iut.beerrater.presentation.beer_detail.BeerDetailFragment

private const val WAS_REVIEW_SAVED = "fr.iut.beerrater.presentation.add_edit_review.activity_was_review_saved"

@AndroidEntryPoint
class AddEditReviewActivity : AppCompatActivity(), AddEditReviewFragment.OnInteractionListener {
    private var reviewId = NEW_REVIEW_ID
    private var beerId = DEFAULT_BEER_ID

    companion object {
        private const val REVIEW_ID = "fr.iut.beerrater.presentation.add_edit_review.activity_review_id"
        private const val BEER_ID = "fr.iut.beerrater.presentation.add_edit_review.activity_beer_id"

        fun newIntent(context: Context, reviewId: Int, beerId: Int) = Intent(
            context,
            AddEditReviewActivity::class.java
        ).apply {
            putExtra(REVIEW_ID, reviewId)
            putExtra(BEER_ID, beerId)
        }

        fun wasReviewSaved(result: Intent) =
            result.getBooleanExtra(WAS_REVIEW_SAVED, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        reviewId = intent.getIntExtra(REVIEW_ID, NEW_REVIEW_ID)
        beerId = intent.getIntExtra(BEER_ID, DEFAULT_BEER_ID)

        setContentView(R.layout.toolbar_activity)
        setSupportActionBar(findViewById(R.id.toolbar_activity))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null) {
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.fragment_container,
                    AddEditReviewFragment.newInstance(reviewId, beerId)
                )
                .commit()
        }
    }

    override fun onModificationsSaved() {
        val intent = Intent().apply {
            putExtra(WAS_REVIEW_SAVED, true)
        }
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onModificationsCanceled() {
        setResult(RESULT_CANCELED)
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}