package dam.nathan.poolngm

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.round

/*
 * PH IDEAL = 7.4
 * Producto
 * Piscina
 *
 * IDEAL - LECTURA * 10 * M3 * GRAMOS
 */

val phIDEAL = 7.4

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        var bCalcular = this.findViewById<Button>(R.id.btCalc)
        var bBorrar = this.findViewById<Button>(R.id.btDelete)
        var tfM3 = this.findViewById<EditText>(R.id.numMetroCubico)
        var tfPH = this.findViewById<EditText>(R.id.numPH)
        var tfGramos = this.findViewById<EditText>(R.id.numGramos)
        var resultado = this.findViewById<TextView>(R.id.result)

        bCalcular.setOnClickListener{
            tfM3 = this.findViewById<EditText>(R.id.numMetroCubico)
            tfPH = this.findViewById<EditText>(R.id.numPH)
            tfGramos = this.findViewById<EditText>(R.id.numGramos)
            resultado = this.findViewById<TextView>(R.id.result)

            if (tfM3 != null && tfPH != null && tfGramos != null) {
                var m3 : Int = tfM3.text.toString().toInt()
                var ph : Double = tfPH.text.toString().toDouble()
                var gramos : Int = tfGramos.text.toString().toInt()
                var final : Double = (phIDEAL - ph) * 10 * m3 * gramos
                if (final >= 1000) {
                    final = final / 1000
                    resultado.setText("$final KG")
                } else {
                    resultado.setText("$final gramos")
                }
            } else {
                resultado.setText("No valido")
            }
        }

        bBorrar.setOnClickListener{
            tfM3 = this.findViewById<EditText>(R.id.numMetroCubico)
            tfPH = this.findViewById<EditText>(R.id.numPH)
            tfGramos = this.findViewById<EditText>(R.id.numGramos)
            resultado = this.findViewById<TextView>(R.id.result)
            tfM3.setText("")
            tfPH.setText("")
            tfGramos.setText("")
            resultado.setText("")
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
