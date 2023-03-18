package com.example.m19_location.ui.main

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.m19_location.R
import com.example.m19_location.data.ModelLandmarkRepository

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener,
    GoogleMap.OnMyLocationClickListener {
    val rep = ModelLandmarkRepository()
    private lateinit var mMap: GoogleMap

    // val context =this@MapsFragment.requireContext()

    /*private val _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!*/
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if (ActivityCompat.checkSelfPermission(
                this@MapsFragment.requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this@MapsFragment.requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        mMap.isMyLocationEnabled = true
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMyLocationButtonClickListener(this)
        mMap.setOnMyLocationClickListener(this)



        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.landmark.collect { featureList ->


                featureList.forEach {

                    Log.d("TAG", it.toString())
                    val sydney = LatLng(
                        it.geometry.coordinates.reversed().first(),
                        it.geometry.coordinates.reversed().last()
                    )
                    mMap.addMarker(
                        MarkerOptions()
                            .position(sydney)
                            .title(it.properties.name)
                            .snippet(it.properties.kinds)
                            .snippet(
                                it.geometry.coordinates.reversed().joinToString(
                                    separator = " / "

                                )
                            )
                    )



                    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
                }
            }
        }
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(
            this@MapsFragment.requireContext(),
            "MyLocation button clicked",
            Toast.LENGTH_SHORT
        )
            .show()

        return false
    }

    override fun onMyLocationClick(location: Location) {
        Toast.makeText(
            this@MapsFragment.requireContext(),
            "Current location:\n$location",
            Toast.LENGTH_LONG
        )
            .show()
    }


}