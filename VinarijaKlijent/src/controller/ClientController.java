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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

/**
 *
 * @author PC
 */
public class ClientController {

    private static ClientController instance;

    public ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        return (Administrator) sendRequest(Operation.LOGIN, administrator);
    }

    public void addKupac(Kupac kupac) throws Exception {
        sendRequest(Operation.ADD_KUPAC, kupac);
    }
    
    public void addVinarija(Vinarija vinarija) throws Exception {
        sendRequest(Operation.ADD_VINARIJA, vinarija);
    }

    public void deleteKupac(Kupac kupac) throws Exception {
        sendRequest(Operation.DELETE_KUPAC, kupac);
    }
    
    public void deleteVinarija(Vinarija vinarija) throws Exception {
        sendRequest(Operation.DELETE_VINARIJA, vinarija);
    }

    public void updateKupac(Kupac kupac) throws Exception {
        sendRequest(Operation.UPDATE_KUPAC, kupac);
    }
   
    public void updateVinarija(Vinarija vinarija) throws Exception {
        sendRequest(Operation.UPDATE_VINARIJA, vinarija);
    }

    public ArrayList<Administrator> getAllAdministrator() throws Exception {
        return (ArrayList<Administrator>) sendRequest(Operation.GET_ALL_ADMINISTRATOR, null);
    }

    public ArrayList<Kupac> getAllKupac() throws Exception {
        return (ArrayList<Kupac>) sendRequest(Operation.GET_ALL_KUPAC, null);
    }
   
    public ArrayList<Vinarija> getAllVinarija() throws Exception {
        return (ArrayList<Vinarija>) sendRequest(Operation.GET_ALL_VINARIJA, null);
    }

    public ArrayList<Vino> getAllVino(Vinarija v) throws Exception {
        return (ArrayList<Vino>) sendRequest(Operation.GET_ALL_VINO, v);
    }

    public ArrayList<VrstaVina> getAllVrstaVina() throws Exception {
        return (ArrayList<VrstaVina>) sendRequest(Operation.GET_ALL_VRSTA_VINA, null);
    }

    private Object sendRequest(int operation, Object data) throws Exception {
        Request request = new Request(operation, data);

        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(request);

        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response response = (Response) in.readObject();

        if (response.getResponseStatus().equals(ResponseStatus.Error)) {
            throw response.getError();
        } else {
            return response.getData();
        }
    }

}
