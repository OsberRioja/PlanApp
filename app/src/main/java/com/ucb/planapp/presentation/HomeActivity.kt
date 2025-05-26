package com.ucb.planapp.presentation

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.ImageButton
import android.widget.Toast
import java.net.URLEncoder
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.appcompat.app.AppCompatActivity
import com.ucb.planapp.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val flipper = findViewById<ViewFlipper>(R.id.planFlipper)

        for (i in 0 until flipper.childCount) {
            val planView = flipper.getChildAt(i)

            val planName = planView.findViewById<TextView>(R.id.planName)
            val planPrice = planView.findViewById<TextView>(R.id.planPrice)
            val planData = planView.findViewById<TextView>(R.id.planData)
            val planFeatures = planView.findViewById<TextView>(R.id.planFeatures)

            when (i) {
                0 -> {
                    planName.text = "Plan FLEX 5"
                    planPrice.text = "$199 / mes"
                    planData.text = "5GB"
                    planFeatures.text = "✓ Llamadas ilimitadas\n✓ Hotspot\n✓ Redes sociales\n✓ CO2 Negativo"
                }
                1 -> {
                    planName.text = "Plan FLEX 8"
                    planPrice.text = "$249 / mes"
                    planData.text = "8GB"
                    planFeatures.text = "✓ Llamadas ilimitadas\n✓ Hotspot\n✓ Apps premium\n✓ CO2 Negativo"
                }
                2 -> {
                    planName.text = "Plan FLEX 10"
                    planPrice.text = "$299 / mes"
                    planData.text = "10GB"
                    planFeatures.text = "✓ Todo ilimitado\n✓ Hotspot\n✓ Sin límites\n✓ CO2 Negativo"
                }
            }
        }

        val btnNext = findViewById<Button>(R.id.btnNext)
        val btnPrev = findViewById<Button>(R.id.btnPrev)

        btnNext.setOnClickListener { flipper.showNext() }
        btnPrev.setOnClickListener { flipper.showPrevious() }

        val btnWhatsApp = findViewById<ImageButton>(R.id.btnWhatsApp)
        btnWhatsApp.setOnClickListener {
            // Texto a enviar
            val message = "Hola, UCB mobile, preciso su ayuda"
            // Codificar mensaje para URL
            val encodedMessage = URLEncoder.encode(message, "UTF-8")
            // Construir URI usando web.whatsapp.com (abre contactos)
            val uri = Uri.parse("https://api.whatsapp.com/send?text=$encodedMessage")

            // Intent para abrir WhatsApp
            val sendIntent = Intent(Intent.ACTION_VIEW, uri)
            sendIntent.setPackage("com.whatsapp")

            try {
                startActivity(sendIntent)
            } catch (e: ActivityNotFoundException) {
                // Si WhatsApp no está instalado, mostramos un Toast
                Toast.makeText(this, "WhatsApp no está instalado.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
