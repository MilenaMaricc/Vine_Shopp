/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.vinarija;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Vinarija;
import domain.Vino;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOAddVinarija extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Vinarija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Vinarija!");
        }
        
        Vinarija v = (Vinarija) ado;
        
        if(v.getVinaVinarije().size() < 2){
            throw new Exception("Vinarija mora imati barem dva vina!");
        }
        
        ArrayList<Vinarija> vinarije = (ArrayList<Vinarija>)(ArrayList<?>) DBBroker.getInstance().select(ado);
        
        for (Vinarija vinarija : vinarije) {
            if(vinarija.getNazivVinarije().equals(v.getNazivVinarije())){
                throw new Exception("Vec postoji vinarija sa tim nazivom!");
            }
        }
        
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        
        
        PreparedStatement ps = DBBroker.getInstance().insert(ado);
       
        
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long vinarijaID = tableKeys.getLong(1);
        
        
        Vinarija v = (Vinarija) ado;
        v.setVinarijaID(vinarijaID);
        
        
        for (Vino vino : v.getVinaVinarije()) {
            vino.setVinarija(v);
            DBBroker.getInstance().insert(vino);
        }
        
    }

}
