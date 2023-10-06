/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Korisnik
 */
public class Kupac extends AbstractDomainObject implements Serializable {
    
    private Long kupacID;
    private String PIB;
    private String nazivKupca;
    private String adresa;
    private String brojTelefona;
    private String email;

    @Override
    public String toString() {
        return nazivKupca;
    }

    public Kupac(Long kupacID, String PIB, String nazivKupca, String adresa, String brojTelefona, String email) {
        this.kupacID = kupacID;
        this.PIB = PIB;
        this.nazivKupca = nazivKupca;
        this.adresa = adresa;
        this.brojTelefona = brojTelefona;
        this.email = email;
    }

    public Kupac() {
    }
    
    @Override
    public String nazivTabele() {
        return " kupac ";
    }

    @Override
    public String alijas() {
        return " k ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Kupac k = new Kupac(rs.getLong("KupacID"),
                    rs.getString("PIB"), rs.getString("NazivKupca"),
                    rs.getString("Adresa"), rs.getString("BrojTelefona"), rs.getString("Email"));

            lista.add(k);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (PIB, NazivKupca, Adresa, BrojTelefona, Email) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " KupacID = " + kupacID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + PIB + "', '" + nazivKupca + "', "
                + "'" + adresa + "', '" + brojTelefona + "', '" + email + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Adresa = '" + adresa + "', BrojTelefona = '" + brojTelefona + "', "
                + "Email = '" + email + "' ";
    }

    @Override
    public String uslov() {
        return "";
    }

    public Long getKupacID() {
        return kupacID;
    }

    public void setKupacID(Long kupacID) {
        this.kupacID = kupacID;
    }

    public String getPIB() {
        return PIB;
    }

    public void setPIB(String PIB) {
        this.PIB = PIB;
    }

    public String getNazivKupca() {
        return nazivKupca;
    }

    public void setNazivKupca(String nazivKupca) {
        this.nazivKupca = nazivKupca;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
