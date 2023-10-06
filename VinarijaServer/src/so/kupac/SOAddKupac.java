/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.kupac;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Kupac;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOAddKupac extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Kupac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Kupac!");
        }
        
        Kupac k = (Kupac) ado;
        
        ArrayList<Kupac> kupci = (ArrayList<Kupac>) (ArrayList<?>) DBBroker.getInstance().select(ado);
        
        for (Kupac kupac : kupci) {
            if(kupac.getPIB().equals(k.getPIB())){
                throw new Exception("Vec postoji kupac s tim PIB-om!");
            }
            if(kupac.getNazivKupca().equals(k.getNazivKupca())){
                throw new Exception("Vec postoji kupac s tim nazivom!");
            }
            if(kupac.getEmail().equals(k.getEmail())){
                throw new Exception("Vec postoji kupac s tim emailom!");
            }
            if(kupac.getAdresa().equals(k.getAdresa())){
                throw new Exception("Vec postoji kupac s tom adresom!");
            }
        }
        
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
