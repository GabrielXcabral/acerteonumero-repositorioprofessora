package com.example.sorte

import kotlin.random.Random

class Jogo {
    private var numerosorteado: Int = 0;
    private var ganhou: Boolean = false;
    private var jogoFinalizado: Boolean = false;
    private var valormin: Int = 1;
    private var valormax: Int = 100;

    fun sortear(): Int {
        val radom = Random;
        numerosorteado = radom.nextInt(1,101);
        return numerosorteado;
    }

    fun chutar (chute: Int): Boolean {
        if(chute < valormin || chute > valormax){
            jogoFinalizado = true;
            return jogoFinalizado;
        }

        if (chute < numerosorteado) {
                valormin = chute + 1;
            }
            else if (chute > numerosorteado) {
                valormax = chute - 1;
            }
            else {
                ganhou = true;
                return ganhou;
            }

        if (valormin == valormax){
            if(chute < numerosorteado){
                valormin = chute + 1;
            } else if (chute > numerosorteado){
                valormax = chute - 1;
            }
            jogoFinalizado = true;
            return jogoFinalizado;
        }

        return false;
    }

    fun reset() {
        valormin = 1;
        valormax = 100;
        ganhou = false;
        jogoFinalizado = false;
    }

    fun getValormin (): Int {
        return valormin;
    }

    fun getValormax (): Int {
        return valormax;
    }

    fun getGanhou (): Boolean{
        return ganhou;
    }

    fun getJogofinalizado(): Boolean {
        return jogoFinalizado;
    }


}