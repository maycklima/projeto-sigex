/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author mayck
 */
public class Evento 
{
 
    private String nomeEvento;
    private String tipoEvento;
    private String chEvento;
    private String dataEvento;
    private String localEvento;
    private int anoEvento;
    private String modeloCertificado;

    public String getLocalEvento() {
        return localEvento;
    }

    public void setLocalEvento(String localEvento) {
        this.localEvento = localEvento;
    }

    public int getAnoEvento() {
        return anoEvento;
    }

    public void setAnoEvento(int anoEvento) {
        this.anoEvento = anoEvento;
    }
    
    

    
    public String getModeloCertificado() {
        return modeloCertificado;
    }

    public void setModeloCertificado(String modeloCertificado) {
        this.modeloCertificado = modeloCertificado;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }
    
    

    public String getChEvento() {
        return chEvento;
    }

    public void setChEvento(String chEvento) {
        this.chEvento = chEvento;
    }
    
    

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }
    
    
}
