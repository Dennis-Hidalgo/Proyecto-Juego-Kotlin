package com.example.examen

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    lateinit var img11: ImageView
    lateinit var img12: ImageView
    lateinit var img13: ImageView
    lateinit var img14: ImageView

    lateinit var img21: ImageView
    lateinit var img22: ImageView
    lateinit var img23: ImageView
    lateinit var img24: ImageView

    lateinit var img31: ImageView
    lateinit var img32: ImageView
    lateinit var img33: ImageView
    lateinit var img34: ImageView

    lateinit var img41: ImageView
    lateinit var img42: ImageView
    lateinit var img43: ImageView
    lateinit var img44: ImageView

    lateinit var txtJ1: TextView
    lateinit var txtJ2: TextView

    lateinit var btnSonido: ImageButton

    lateinit var mp: MediaPlayer
    lateinit var mpFondo: MediaPlayer
    lateinit var imagen1: ImageView
    lateinit var imagen2: ImageView

    var vacio by Delegates.notNull<Boolean>()

    val desaparacer = AlphaAnimation(1.0f, 0.0f)
    val aparecer = AlphaAnimation(0.0f, 1.0f)

    lateinit var arrayImg: List<ImageView>
    var imagenesArray = arrayOf(11, 12, 13, 14, 15, 16, 17, 18, 21, 22, 23, 24, 25, 26, 27, 28)
    var python = 0
    var cSharp = 0
    var varC = 0
    var cPlus = 0
    var html = 0
    var var_java = 0
    var var_js = 0
    var var_php = 0

    var turno = 1
    var puntosj1 = 0
    var puntosj2 = 0
    var numeroImagen = 1
    var audio = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        linkGUI()
    }

    private fun linkGUI() {
        img11 = findViewById(R.id.img11)
        img12 = findViewById(R.id.img12)
        img13 = findViewById(R.id.img13)
        img14 = findViewById(R.id.img14)
        img21 = findViewById(R.id.img21)
        img22 = findViewById(R.id.img22)
        img23 = findViewById(R.id.img23)
        img24 = findViewById(R.id.img24)
        img31 = findViewById(R.id.img31)
        img32 = findViewById(R.id.img32)
        img33 = findViewById(R.id.img33)
        img34 = findViewById(R.id.img34)
        img41 = findViewById(R.id.img41)
        img42 = findViewById(R.id.img42)
        img43 = findViewById(R.id.img43)
        img44 = findViewById(R.id.img44)

        arrayImg = listOf(
            img11, img12, img13, img14,
            img21, img22, img23, img24,
            img31, img32, img33, img34,
            img41, img42, img43, img44,
        )

        btnSonido = findViewById(R.id.btnSonido)
        btnSonido.setColorFilter(Color.GREEN)
        sonido("background", true)

        for (img in arrayImg) {
            img.tag = arrayImg.indexOf(img)
        }

        python = R.drawable.python
        cSharp = R.drawable.csharp
        varC = R.drawable.imgc
        cPlus = R.drawable.cplus
        html = R.drawable.html
        var_java = R.drawable.java
        var_js = R.drawable.js
        var_php = R.drawable.php

        imagenesArray.shuffle()

        txtJ1 = findViewById(R.id.txtJ1)
        txtJ2 = findViewById(R.id.txtJ2)

        txtJ1.setTextColor(Color.WHITE)
        txtJ2.setTextColor(Color.GRAY)

        desaparacer.duration = 500
        aparecer.duration = 500
    }

    private fun sonido(nombre: String, loop: Boolean = false) {
        val resID = resources.getIdentifier(
            nombre, "raw", packageName
        )
        if (nombre == "background") {
            mpFondo = MediaPlayer.create(this, resID)
            mpFondo.isLooping = loop
            mpFondo.setVolume(1F, 1F)
            if (!mpFondo.isPlaying) {
                mpFondo.start()
            }
        } else {
            mp = MediaPlayer.create(this, resID)
            mp.setOnCompletionListener { mediaPlayer ->
                mediaPlayer.stop()
                mediaPlayer.release()
            }
            if (!mp.isPlaying) {
                mp.start()
            }
        }
    }


    fun musicafondo(v: View) {
        if (audio) {
            mpFondo.pause()
            btnSonido.setImageResource(R.drawable.btn_volume_off)
            btnSonido.setColorFilter(Color.GRAY)
        } else {
            mpFondo.start()
            btnSonido.setImageResource(R.drawable.btn_volume_on)
            btnSonido.setColorFilter(Color.GREEN)
        }
        audio = !audio
    }

    fun seleccionar(imagen: View) {
        sonido("select")
        verificar(imagen)
    }

    private fun verificar(imagen: View) {
        var img = (imagen as ImageView)
        var tag = imagen.tag.toString().toInt()
        img.startAnimation(aparecer)
        when (imagenesArray[tag]) {
            11 -> img.setImageResource(python)
            12 -> img.setImageResource(cSharp)
            13 -> img.setImageResource(varC)
            14 -> img.setImageResource(cPlus)
            15 -> img.setImageResource(html)
            16 -> img.setImageResource(var_java)
            17 -> img.setImageResource(var_js)
            18 -> img.setImageResource(var_php)
            21 -> img.setImageResource(python)
            22 -> img.setImageResource(cSharp)
            23 -> img.setImageResource(varC)
            24 -> img.setImageResource(cPlus)
            25 -> img.setImageResource(html)
            26 -> img.setImageResource(var_java)
            27 -> img.setImageResource(var_js)
            28 -> img.setImageResource(var_php)
        }

        if (numeroImagen == 1) {
            imagen1 = img
            numeroImagen = 2
            img.isEnabled = false
        } else if (numeroImagen == 2) {
            imagen2 = img
            numeroImagen = 1
            img.isEnabled = false
            desactivarImagenes()
            val h = Handler(Looper.getMainLooper())
            h.postDelayed({ sonIguales() }, 1000)
        }
    }

    private fun sonIguales() {
        if (imagen1.drawable.constantState == imagen2.drawable.constantState) {
            sonido("correct")
            if (turno == 1) {
                puntosj1++
                txtJ1.text = "Jugador 1: $puntosj1"
            } else if (turno == 2) {
                puntosj2++
                txtJ2.text = "Jugador 2: $puntosj2"
            }
            imagen1.isEnabled = false
            imagen2.isEnabled = false
            desaparacer.setAnimationListener(object :
                android.view.animation.Animation.AnimationListener {
                override fun onAnimationStart(animation: android.view.animation.Animation) {
                    imagen1.visibility = View.INVISIBLE
                    imagen2.visibility = View.INVISIBLE
                }
                override fun onAnimationEnd(animation: android.view.animation.Animation) {}
                override fun onAnimationRepeat(animation: android.view.animation.Animation) {}
            })
            imagen1.tag = ""
            imagen2.tag = ""
            imagen1.startAnimation(desaparacer)
            imagen2.startAnimation(desaparacer)
        } else {
            sonido("wrong")
            imagen1.setImageResource(R.drawable.oculto)
            imagen2.setImageResource(R.drawable.oculto)
            imagen1.startAnimation(aparecer)
            imagen2.startAnimation(aparecer)
            if (turno == 1) {
                turno = 2
                txtJ1.setTextColor(Color.GRAY)
                txtJ2.setTextColor(Color.WHITE)
            } else if (turno == 2) {
                turno = 1
                txtJ2.setTextColor(Color.GRAY)
                txtJ1.setTextColor(Color.WHITE)
            }
        }
        for (img in arrayImg) {
            img.isEnabled = !img.tag.toString().isEmpty()
        }
        verificarFin()
    }


    private fun verificarFin() {
        vacio = true
        for (img in arrayImg) {
            if (!img.tag.toString().isEmpty()) {
                vacio = false
            }
        }

        if (vacio) {
            mp.stop()
            mp.release()
            sonido("finish")
            val builder = AlertDialog.Builder(this)
            builder.setTitle("FIN DE LA PARTIDA")
                .setMessage("PUNTOS J1: $puntosj1\nPUNTOS J2: $puntosj2")
                .setCancelable(false)
                .setPositiveButton(
                    "Nuevo Juego",
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    })
                .setNegativeButton("Salir", DialogInterface.OnClickListener { dialogInterface, i ->
                    finish()
                })
            val alert = builder.create()
            alert.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mpFondo.stop()
        mpFondo.release()
    }

    private fun desactivarImagenes() {
        for (img in arrayImg) {
            img.isEnabled = false
        }
    }

}