/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Administrator;

import domain.Kupac;
import domain.Vinarija;
import domain.Vino;
import domain.VrstaVina;
import java.util.ArrayList;
import so.administrator.SOGetAllAdministrator;

import so.kupac.SOAddKupac;
import so.kupac.SODeleteKupac;
import so.kupac.SOGetAllKupac;
import so.kupac.SOUpdateKupac;
import so.login.SOLogin;
import so.vinarija.SOAddVinarija;
import so.vinarija.SODeleteVinarija;
import so.vinarija.SOGetAllVinarija;
import so.vinarija.SOUpdateVinarija;
import so.vino.SOGetAllVino;
import so.vrstaVina.SOGetAllVrstaVina;

/**
 *
 * @author PC
 */
public class ServerController {

    private static ServerController instance;

    public ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(administrator);
        return so.getAdmin();
    }

    public void addKupac(Kupac kupac) throws Exception {
        (new SOAddKupac()).templateExecute(kupac);
    }

    public void addVinarija(Vinarija vinarija) throws Exception {
        (new SOAddVinarija()).templateExecute(vinarija);
    }

    public void deleteKupac(Kupac kupac) throws Exception {
        (new SODeleteKupac()).templateExecute(kupac);
    }

    public void deleteVinarija(Vinarija vinarija) throws Exception {
        (new SODeleteVinarija()).templateExecute(vinarija);
    }

    public void updateKupac(Kupac kupac) throws Exception {
        (new SOUpdateKupac()).templateExecute(kupac);
    }

    public void updateVinarija(Vinarija vinarija) throws Exception {
        (new SOUpdateVinarija()).templateExecute(vinarija);
    }

    public ArrayList<Administrator> getAllAdministrator() throws Exception {
        SOGetAllAdministrator so = new SOGetAllAdministrator();
        so.templateExecute(new Administrator());
        return so.getLista();
    }

    public ArrayList<Kupac> getAllKupac() throws Exception {
        SOGetAllKupac so = new SOGetAllKupac();
        so.templateExecute(new Kupac());
        return so.getLista();
    }

    public ArrayList<Vinarija> getAllVinarija() throws Exception {
        SOGetAllVinarija so = new SOGetAllVinarija();
        so.templateExecute(new Vinarija());
        return so.getLista();
    }

    public ArrayList<Vino> getAllVino(Vinarija v) throws Exception {
        SOGetAllVino so = new SOGetAllVino();

        Vino vino = new Vino();
        vino.setVinarija(v);

        so.templateExecute(vino);
        return so.getLista();
    }

    public ArrayList<VrstaVina> getAllVrstaVina() throws Exception {
        SOGetAllVrstaVina so = new SOGetAllVrstaVina();
        so.templateExecute(new VrstaVina());
        return so.getLista();
    }

    
}
