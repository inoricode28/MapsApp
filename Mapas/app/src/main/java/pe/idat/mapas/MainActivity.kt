package pe.idat.mapas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import pe.idat.mapas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var p:ActivityMainBinding
    lateinit var map:GoogleMap
    var lati:Double=0.0
    var longi:Double=0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        p = ActivityMainBinding.inflate(layoutInflater)
        setContentView(p.root)

        verfragmento()


        p.btnnuevo.setOnClickListener {
            p.txtlat.setText("")
            p.txtlon.setText("")
            p.txtleyenda.setText("")
            p.txtlat.requestFocus()

        }
        p.btnmostrar.setOnClickListener {
            lati = p.txtlat.text.toString().toDouble()
            longi = p.txtlon.text.toString().toDouble()
            onMapReady(map)

        }
    }
    fun verfragmento(){
        val mapas = supportFragmentManager.findFragmentById(R.id.mapa) as SupportMapFragment
        mapas.getMapAsync(this)

    }

    override fun onMapReady(p0: GoogleMap) {
        map=p0
        marcador()
    }
    fun marcador(){
        val coord = LatLng(lati,longi)
        val marcar= MarkerOptions().position(coord).title(p.txtleyenda.toString())
        marcar.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        map.addMarker(marcar)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coord,18f),
            4000,
            null
        )

    }
}