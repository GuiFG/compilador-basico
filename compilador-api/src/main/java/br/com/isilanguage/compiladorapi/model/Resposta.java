package br.com.isilanguage.compiladorapi.model;

import java.util.ArrayList;


public class Resposta {
    public String erro;
    public ArrayList<String> avisos;
    
    public Resposta(String erro) {
        this.erro = erro;
    }
    
    public Resposta(String erro, ArrayList<String> avisos) {
        this.erro = erro;
        this.avisos = avisos;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public ArrayList<String> getAvisos() {
        return avisos;
    }

    public void setAvisos(ArrayList<String> avisos) {
        this.avisos = avisos;
    }
}
