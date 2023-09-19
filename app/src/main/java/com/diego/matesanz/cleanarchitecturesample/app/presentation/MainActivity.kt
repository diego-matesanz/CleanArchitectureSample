package com.diego.matesanz.cleanarchitecturesample.app.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.diego.matesanz.cleanarchitecturesample.app.framework.FakeLocationSource
import com.diego.matesanz.cleanarchitecturesample.app.framework.InMemoryLocationsPersistenceSource
import com.diego.matesanz.cleanarchitecturesample.data.LocationsRepository
import com.diego.matesanz.cleanarchitecturesample.databinding.ActivityMainBinding
import com.diego.matesanz.cleanarchitecturesample.domain.Location
import com.diego.matesanz.cleanarchitecturesample.useCases.GetLocations
import com.diego.matesanz.cleanarchitecturesample.useCases.RequestNewLocation

class MainActivity : AppCompatActivity(), Render {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel
    private val locationItemAdapter: LocationItemAdapter by lazy { LocationItemAdapter(emptyList()) }

    init {
        val persistence = InMemoryLocationsPersistenceSource()
        val deviceLocation = FakeLocationSource()
        val locationsRepository = LocationsRepository(persistence, deviceLocation)
        viewModel = MainViewModel(GetLocations(locationsRepository), RequestNewLocation(locationsRepository), this)
        viewModel.getSavedLocations()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = locationItemAdapter
        }

        binding.buttonRequestLocation.setOnClickListener {
            viewModel.newLocationClicked()
        }
    }

    override fun renderLocations(locations: List<Location>) {
        locationItemAdapter.setLocations(locations)
    }
}
