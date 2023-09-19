package com.example.sorte

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resultadonumerosorteado = findViewById<TextView>(R.id.numerosorteado);
        val botaochute = findViewById<Button>(R.id.botaochute);
        val chutedonumero = findViewById<EditText>(R.id.chutarnumero);
        val statusdojogo = findViewById<TextView>(R.id.status);
        val menornumero = findViewById<TextView>(R.id.menor);
        val maiornumero = findViewById<TextView>(R.id.maior);
        val ganhador = findViewById<TextView>(R.id.ganhador);

        val sharedPreferences = getSharedPreferences("MinhasPreferencias", Context.MODE_PRIVATE);
        val ultimoganhador = sharedPreferences.getString("nomeganhador", "...");

        ganhador.text = ultimoganhador;



        val jogo = Jogo();

        resultadonumerosorteado.text = "Nº : " + jogo.sortear();

         botaochute.setOnClickListener(){
             val numerodigitado = chutedonumero.text.toString().toInt();
             chutedonumero.setText("");

             jogo.chutar(numerodigitado);


             if(jogo.getJogofinalizado()){
                 menornumero.text = jogo.getValormin().toString();
                 maiornumero.text = jogo.getValormax().toString();
                 resultadonumerosorteado.visibility = View.GONE;
                 statusdojogo.text = "Jogador Perdeu!";
                 botaochute.isEnabled = false;
             }else if (jogo.getGanhou()){
                 statusdojogo.text = "Jogador Ganhou!"
                 statusdojogo.setTextColor(Color.GREEN);
                 botaochute.isEnabled = false;
                 resultadonumerosorteado.visibility = View.VISIBLE;
                 val  intent = Intent(this, SegundaTela::class.java);
                 startActivity(intent);
             }
             else{
                 menornumero.text = jogo.getValormin().toString();
                 maiornumero.text = jogo.getValormax().toString();
             }
         }

        statusdojogo.setOnLongClickListener(){
            resultadonumerosorteado.text = "Nº : " + jogo.sortear();
            botaochute.isEnabled = true;
            resultadonumerosorteado.visibility = View.GONE;

            jogo.reset();
            menornumero.text = jogo.getValormin().toString();
            maiornumero.text = jogo.getValormax().toString();
            statusdojogo.text = "Em execução..."
            statusdojogo.setTextColor(Color.RED);

            true;
       }
    }
}