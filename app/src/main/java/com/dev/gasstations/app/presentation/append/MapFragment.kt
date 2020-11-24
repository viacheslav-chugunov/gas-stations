package com.dev.gasstations.app.presentation.append

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.dev.gasstations.R
import com.dev.gasstations.app.ext.showToast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.util.*

class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMapClickListener {

    var address: String = ""
    private var userLocation: Location? = null
    private var selectedMarker: Marker? = null
    private lateinit var locationProviderClient: FusedLocationProviderClient
    private lateinit var map: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        locationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())
        defineUserLocation()
    }

    private fun defineUserLocation() {
        val accessFineLocation = ActivityCompat.checkSelfPermission(requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
        val accessCoarseLocation = ActivityCompat.checkSelfPermission(requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED

        if (accessFineLocation && accessCoarseLocation) {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 101)
            return
        }

        val task: Task<Location> = locationProviderClient.lastLocation
        task.addOnSuccessListener { location ->
            userLocation = location
            val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
            mapFragment?.getMapAsync(this)
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        val permissionGranted = requestCode == 101 && grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED

        if (permissionGranted)
            defineUserLocation()
    }

    // OnMapReadyCallback Impl
    override fun onMapReady(map: GoogleMap) {
        this.map = map
        map.setOnMapClickListener(this)
        userLocation?.let {
            val position = LatLng(it.latitude, it.longitude)
            map.animateCamera(CameraUpdateFactory.newLatLng(position))
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 15f))
            val options = MarkerOptions()
                .position(position)
                .title(getString(R.string.user_location))
            map.addMarker(options)
        }
    }

    // GoogleMap.OnMapClickListener Impl
    override fun onMapClick(pos: LatLng) {
        selectedMarker?.remove()
        val options = MarkerOptions().position(pos)
        selectedMarker = map.addMarker(options)
        tryToGetAddress()
    }

    private fun tryToGetAddress() {
        selectedMarker?.position?.let {
            // Studio EMU have a bug with Geocoder,
            // for this case try / catch block here
            try {
                val locations = Geocoder(context, Locale.ENGLISH)
                    .getFromLocation(it.latitude, it.longitude, 1)

                address = if (locations.isNotEmpty())
                    locations?.first()?.thoroughfare ?: getString(R.string.unknown)
                else
                    getString(R.string.unknown)
            } catch (e: Exception) { e.printStackTrace() }
        }
    }

}