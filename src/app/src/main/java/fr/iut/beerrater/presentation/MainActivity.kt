package fr.iut.beerrater.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import fr.iut.beerrater.R
import fr.iut.beerrater.presentation.beers_list.FragmentBeersList

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.toolbar_activity)

        setSupportActionBar(findViewById(R.id.toolbar_activity))

        if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, FragmentBeersList())
                .commit()
        }
    }
}