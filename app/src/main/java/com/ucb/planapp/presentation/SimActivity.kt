package com.ucb.planapp.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ucb.planapp.R

class SimActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var etPhone: EditText
    private lateinit var etLatitude: EditText
    private lateinit var etLongitude: EditText
    private lateinit var btnContinue: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sim)

        // Referencias a views
        etPhone     = findViewById(R.id.etPhone)
        etLatitude  = findViewById(R.id.etLatitude)
        etLongitude = findViewById(R.id.etLongitude)
        btnContinue = findViewById(R.id.btnContinue)

        // Inicializar mapa
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Lógica del botón Continuar
        btnContinue.setOnClickListener {
            val phone = etPhone.text.toString().trim()
            val lat   = etLatitude.text.toString().trim()
            val lon   = etLongitude.text.toString().trim()

            // Validamos campos
            if (phone.isEmpty() || lat.isEmpty() || lon.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Mostrar datos
            Toast.makeText(
                this,
                "SIM irá a:\nTel: $phone\nLat: $lat\nLon: $lon",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Ubicación inicial (Cochabamba)
        val defaultLocation = LatLng(-17.3935, -66.1570)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 15f))

        // Listener para clicks en el mapa
        map.setOnMapClickListener { latLng ->
            // Limpiar marcadores anteriores
            map.clear()
            // Agregar nuevo marcador
            map.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title("Ubicación seleccionada")
            )
            // Actualizar campos de texto
            etLatitude.setText(latLng.latitude.toString())
            etLongitude.setText(latLng.longitude.toString())
        }
    }
}
