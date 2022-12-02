/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Firda Amalia
 */
@Entity
@Table(name = "kampus1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kampus1.findAll", query = "SELECT k FROM Kampus1 k")
    , @NamedQuery(name = "Kampus1.findByNrp", query = "SELECT k FROM Kampus1 k WHERE k.nrp = :nrp")
    , @NamedQuery(name = "Kampus1.findByNama", query = "SELECT k FROM Kampus1 k WHERE k.nama = :nama")
    , @NamedQuery(name = "Kampus1.findByAngkatan", query = "SELECT k FROM Kampus1 k WHERE k.angkatan = :angkatan")
    , @NamedQuery(name = "Kampus1.findBySekolahAsal", query = "SELECT k FROM Kampus1 k WHERE k.sekolahAsal = :sekolahAsal")})
public class Kampus1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nrp")
    private Integer nrp;
    @Basic(optional = false)
    @Column(name = "nama")
    private String nama;
    @Basic(optional = false)
    @Column(name = "angkatan")
    private String angkatan;
    @Basic(optional = false)
    @Column(name = "sekolah_asal")
    private String sekolahAsal;

    public Kampus1() {
    }

    public Kampus1(Integer nrp) {
        this.nrp = nrp;
    }

    public Kampus1(Integer nrp, String nama, String angkatan, String sekolahAsal) {
        this.nrp = nrp;
        this.nama = nama;
        this.angkatan = angkatan;
        this.sekolahAsal = sekolahAsal;
    }

    public Integer getNrp() {
        return nrp;
    }

    public void setNrp(Integer nrp) {
        this.nrp = nrp;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(String angkatan) {
        this.angkatan = angkatan;
    }

    public String getSekolahAsal() {
        return sekolahAsal;
    }

    public void setSekolahAsal(String sekolahAsal) {
        this.sekolahAsal = sekolahAsal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nrp != null ? nrp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kampus1)) {
            return false;
        }
        Kampus1 other = (Kampus1) object;
        if ((this.nrp == null && other.nrp != null) || (this.nrp != null && !this.nrp.equals(other.nrp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Kampus1[ nrp=" + nrp + " ]";
    }
    
}
