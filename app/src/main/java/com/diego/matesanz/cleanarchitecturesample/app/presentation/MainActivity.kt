package com.diego.matesanz.cleanarchitecturesample.app.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.diego.matesanz.cleanarchitecturesample.R
import com.diego.matesanz.cleanarchitecturesample.app.framework.FakeLocationSource
import com.diego.matesanz.cleanarchitecturesample.app.framework.InMemoryLocationsPersistenceSource
import com.diego.matesanz.cleanarchitecturesample.data.LocationsRepository
import com.diego.matesanz.cleanarchitecturesample.databinding.ActivityMainBinding
import com.diego.matesanz.cleanarchitecturesample.useCases.GetLocations
import com.diego.matesanz.cleanarchitecturesample.useCases.RequestNewLocation

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel
    private val locationItemAdapter: LocationItemAdapter by lazy { LocationItemAdapter(emptyList()) }

    init {
        val persistence = InMemoryLocationsPersistenceSource()
        val deviceLocation = FakeLocationSource()
        val locationsRepository = LocationsRepository(persistence, deviceLocation)
        viewModel = MainViewModel(GetLocations(locationsRepository), RequestNewLocation(locationsRepository))
        viewModel.getSavedLocations()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.stateObservable.addObserver(::updateUI)

        Glide.with(this)
            .load(R.drawable.loader)
            .into(binding.layoutLoader.imageViewLoader)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = locationItemAdapter
        }

        binding.buttonRequestLocation.setOnClickListener {
            viewModel.newLocationClicked()
        }
    }

    override fun onDestroy() {
        viewModel.onDestroy()
        super.onDestroy()
    }

    private fun updateUI(screenState: ScreenState<MainState>) {
        when (screenState) {
            ScreenState.Loading -> {
                binding.isLoading = true
            }

            is ScreenState.Render -> {
                processLocationListState(screenState.renderState)
            }
        }
    }

    private fun processLocationListState(renderState: MainState) {
        binding.isLoading = false
        when (renderState) {
            is MainState.ShowLocations -> {
                locationItemAdapter.setLocations(renderState.locations)
            }

            is MainState.ShowMessage -> {
                Toast.makeText(applicationContext, renderState.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
