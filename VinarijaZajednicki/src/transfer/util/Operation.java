/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author PC
 */
public interface Operation {

    public static final int LOGIN = 1;

    public static final int GET_ALL_ADMINISTRATOR = 2;

    public static final int ADD_KUPAC = 3;
    public static final int DELETE_KUPAC = 4;
    public static final int UPDATE_KUPAC = 5;
    public static final int GET_ALL_KUPAC = 6;

    public static final int ADD_VINARIJA = 8;
    public static final int DELETE_VINARIJA = 9;
    public static final int UPDATE_VINARIJA = 10;
    public static final int GET_ALL_VINARIJA = 11;

    public static final int GET_ALL_VINO = 14;

    public static final int ADD_VRSTA_VINA = 19;
    public static final int DELETE_VRSTA_VINA = 20;
    public static final int UPDATE_VRSTA_VINA = 21;
    public static final int GET_ALL_VRSTA_VINA = 22;
    
    
}
