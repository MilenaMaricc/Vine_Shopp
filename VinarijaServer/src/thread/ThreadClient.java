/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.ServerController;
import domain.Administrator;

import domain.Kupac;
import domain.Vinarija;
import domain.Vino;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

/**
 *
 * @author PC
 */
public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();
                Response response = handleRequest(request);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response(null, null, ResponseStatus.Success);
        try {
            switch (request.getOperation()) {
                case Operation.ADD_KUPAC:
                    ServerController.getInstance().addKupac((Kupac) request.getData());
                    break;
                  
                case Operation.ADD_VINARIJA:
                    ServerController.getInstance().addVinarija((Vinarija) request.getData());
                    break;
                case Operation.DELETE_KUPAC:
                    ServerController.getInstance().deleteKupac((Kupac) request.getData());
                    break;
              
                case Operation.DELETE_VINARIJA:
                    ServerController.getInstance().deleteVinarija((Vinarija) request.getData());
                    break;
                case Operation.UPDATE_VINARIJA:
                    ServerController.getInstance().updateVinarija((Vinarija) request.getData());
                    break;
                    
                case Operation.UPDATE_KUPAC:
                    ServerController.getInstance().updateKupac((Kupac) request.getData());
                    break;
                case Operation.GET_ALL_ADMINISTRATOR:
                    response.setData(ServerController.getInstance().getAllAdministrator());
                    break;
                case Operation.GET_ALL_KUPAC:
                    response.setData(ServerController.getInstance().getAllKupac());
                    break;
                 
                case Operation.GET_ALL_VINARIJA:
                    response.setData(ServerController.getInstance().getAllVinarija());
                    break;
                case Operation.GET_ALL_VINO:
                    response.setData(ServerController.getInstance().getAllVino((Vinarija) request.getData()));
                    break;
                case Operation.GET_ALL_VRSTA_VINA:
                    response.setData(ServerController.getInstance().getAllVrstaVina());
                    break;
                case Operation.LOGIN:
                    Administrator administrator = (Administrator) request.getData();
                    response.setData(ServerController.getInstance().login(administrator));
                    break;
                default:
                    return null;
            }
        } catch (Exception e) {
            response.setError(e);
            response.setResponseStatus(ResponseStatus.Error);
        }
        return response;
    }

}
