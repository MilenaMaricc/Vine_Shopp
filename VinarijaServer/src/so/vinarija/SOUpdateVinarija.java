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
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOUpdateVinarija extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Vinarija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Vinarija!");
        }
        
        Vinarija v = (Vinarija) ado;
        
        if(v.getVinaVinarije().size() < 2){
           throw new Exception("Vinarija mora imati barem dva vina!");
        }
        
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        
        
        DBBroker.getInstance().update(ado);
        
        Vinarija v = (Vinarija) ado;
        
        Vino vino = v.getVinaVinarije().get(0);
        
        
        DBBroker.getInstance().delete(vino);
        
    
        for (Vino vinoVinarije : v.getVinaVinarije()) {
            DBBroker.getInstance().insert(vinoVinarije);
        }
        
    }

}
