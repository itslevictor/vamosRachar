package com.example.vamosrachar

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.Double.parseDouble
import java.lang.Integer.parseInt
import java.text.DecimalFormat
import java.util.Locale


@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity() {
    var valor:Double = 0.0
    var numPessoas:Int = 0
    var resultadoBase:Double = 0.00
    var dec = DecimalFormat("#.##")
    lateinit var resultado : String
    lateinit var texto1 : ImageView
    lateinit var texto2 : ImageView
    lateinit var tts : TextToSpeech

    //ONCREATE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        texto1 = findViewById(R.id.imageView1);
        texto2 = findViewById(R.id.imageView2);

        val edtValor = findViewById<EditText>(R.id.valor)
        val edtGrupo = findViewById<EditText>(R.id.numPessoas)
        val texto = findViewById<TextView>(R.id.resultado)

        //#### Observando o valor recebido
        valor.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
                var textoVariavel = edtValor.getText().toString();
                valor = textoVariavel.toDouble();
                if(numPessoas !=0 ){
                    resultadoBase=valor/numPessoas
                    resultado = dec.format(resultadoBase)
                    texto.setText(resultado)
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("Not yet implemented")
                var textoVariavel = edtValor.getText().toString();
                valor = textoVariavel.toDouble();
                if(numPessoas !=0 ){
                    resultadoBase=valor/numPessoas
                    resultado = dec.format(resultadoBase)
                    texto.setText(resultado)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                TODO("Not yet implemented")
                var textoVariavel = edtValor.getText().toString();
                valor = textoVariavel.toDouble();
                if(numPessoas !=0 ){
                    resultadoBase=valor/numPessoas
                    resultado = dec.format(resultadoBase)
                    texto.setText(resultado)
                }
            }


        })
        ///#### Observando o numero de pessoas
        numPessoas.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
                var textoVariavel = edtValor.getText().toString();
                numPessoas = textoVariavel.toInt();
                if(valor !=0.0 ){
                    resultadoBase=valor/numPessoas
                    resultado = dec.format(resultadoBase)
                    texto.setText(resultado)
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("Not yet implemented")
                var textoVariavel = edtValor.getText().toString();
                numPessoas = textoVariavel.toInt();
                if(valor !=0.0 ){
                    resultadoBase=valor/numPessoas
                    resultado = dec.format(resultadoBase)
                    texto.setText(resultado)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                TODO("Not yet implemented")
                var textoVariavel = edtValor.getText().toString();
                numPessoas = textoVariavel.toInt();
                if(valor !=0.0 ){
                    resultadoBase=valor/numPessoas
                    resultado = dec.format(resultadoBase)
                    texto.setText(resultado)
                }
            }


        })


        //#### recuperando caso exista
        Log.d("PDM23","No onCreate, $resultadoBase")
        if (savedInstanceState != null){
            resultadoBase =savedInstanceState.getDouble("resultado");
            valor = savedInstanceState.getDouble("valor")
            numPessoas = savedInstanceState.getInt("numPessoas")
            resultadoBase=valor/numPessoas
            resultado = dec.format(resultadoBase)
            texto.setText("$resultado"+"reais")
        }


        //#### Compartilhando o resultado
        texto1.setOnClickListener{
            //compartilhar
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra("Compartilhar valor da divisão", "Valor total é $valor," +
                    "dividido para $numPessoas. Valor para cada é $resultado")
            val chooser = Intent.createChooser(intent, "Compartilhe usando:")
            startActivity(chooser);
        }
        if(valor!= 0.0 && numPessoas !=0) {
            resultadoBase = valor / numPessoas
            resultado = dec.format(resultadoBase)
            texto.setText("$resultado" + "reais")
        }

        //#### Chamando o TTS
        texto2.setOnClickListener{
            tts = TextToSpeech(applicationContext,TextToSpeech.OnInitListener {
                if(it==TextToSpeech.SUCCESS){
                    tts.language = Locale.US
                    tts.setSpeechRate(1.0f)
                    tts.speak(texto.text.toString(),TextToSpeech.QUEUE_ADD, null)
                }

            })
        }
    }

}


private fun Int.addTextChangedListener(textWatcher: TextWatcher) {

}

private fun Double.addTextChangedListener(textWatcher: TextWatcher) {

}
