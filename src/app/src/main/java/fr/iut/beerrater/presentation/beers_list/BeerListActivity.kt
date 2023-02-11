package fr.iut.beerrater.presentation.beers_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import fr.iut.beerrater.R
import fr.iut.beerrater.presentation.beer_detail.BeerDetailActivity
import fr.iut.beerrater.presentation.beer_detail.BeerDetailFragment

@AndroidEntryPoint
class BeerListActivity : AppCompatActivity(),
    BeerDetailFragment.OnInteractionListener, BeerListFragment.OnInteractionListener {
    private var areTwoPanesPresent: Boolean = false
    private lateinit var beerListFragment: BeerListFragment

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
        TODO("Not yet implemented")
    }

    override fun onReviewEdited(reviewId: Int) {
        TODO("Not yet implemented")
    }

    override fun onReviewAdded() {
        TODO("Not yet implemented")
    }
}