package fr.iut.beerrater.presentation.beer_list

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import fr.iut.beerrater.R
import fr.iut.beerrater.common.Constants
import fr.iut.beerrater.presentation.add_edit_review.AddEditReviewActivity
import fr.iut.beerrater.presentation.beer_detail.BeerDetailActivity
import fr.iut.beerrater.presentation.beer_detail.BeerDetailFragment

@AndroidEntryPoint
class BeerListActivity : AppCompatActivity(),
    BeerDetailFragment.OnInteractionListener, BeerListFragment.OnInteractionListener {
    private var areTwoPanesPresent: Boolean = false
    private lateinit var beerListFragment: BeerListFragment

    private val addEditReviewLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result -> if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let {
                if (AddEditReviewActivity.wasReviewSaved(it)) {
                    Toast.makeText(this, "Your review has been saved.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.toolbar_two_panes_activity)

        if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container,
                    BeerListFragment()
                )
                .commit()
        }

        areTwoPanesPresent = resources.getBoolean(R.bool.landscape_mode)
        if (savedInstanceState != null) {
            beerListFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as BeerListFragment
        }
        if (!areTwoPanesPresent) {
            removeFragmentDetail()
        }
    }

    private fun removeFragmentDetail() {
        supportFragmentManager.findFragmentById(R.id.fragment_container_detail)?.let {
            supportFragmentManager.beginTransaction().remove(it).commit()
        }
    }

    override fun onBeerSelected(beerId: Int) {
        if (areTwoPanesPresent) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_detail, BeerDetailFragment.newInstance(beerId))
                .commit()
        }
        else {
            startActivity(BeerDetailActivity.getIntent(this, beerId))
        }
    }

    override fun onReviewDeleted(reviewId: Int) {
        Log.i(Constants.APP_NAME, "Review deleted!")
    }

    override fun onReviewEdited(reviewId: Int) {
        Log.i(Constants.APP_NAME, "Review edited!")
        addEditReviewLauncher.launch(
            AddEditReviewActivity.newIntent(this, reviewId, 1)
        )
    }

    override fun onReviewAdded() {
        Log.i(Constants.APP_NAME, "Review added!")
        addEditReviewLauncher.launch(
            AddEditReviewActivity.newIntent(this, Constants.NEW_REVIEW_ID, 1)
        )
    }
}