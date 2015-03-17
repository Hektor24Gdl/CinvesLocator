package org.cinvestav.locator.dao;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Héctor Guillermo Rodríguez Fuentes <hector.rodgz@facturasi.com>
 */
@Entity
@Table(name = "location")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l"),
    @NamedQuery(name = "Location.findByIdlocation", query = "SELECT l FROM Location l WHERE l.idlocation = :idlocation"),
    @NamedQuery(name = "Location.findByTimestamp", query = "SELECT l FROM Location l WHERE l.timestamp = :timestamp"),
    @NamedQuery(name = "Location.findByCoordinates", query = "SELECT l FROM Location l WHERE l.coordinates = :coordinates")})
public class Location implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idlocation", nullable = false)
    private Integer idlocation;
    @Column(name = "timestamp", length = 45)
    private String timestamp;
    @Basic(optional = false)
    @Column(name = "coordinates", nullable = false, length = 45)
    private String coordinates;
    @JoinColumn(name = "agente_idagente", referencedColumnName = "idagente", nullable = false)
    @ManyToOne(optional = false)
    private Agent agenteIdagente;

    public Location() {
    }

    public Location(Integer idlocation) {
        this.idlocation = idlocation;
    }

    public Location(Integer idlocation, String coordinates) {
        this.idlocation = idlocation;
        this.coordinates = coordinates;
    }

    public Integer getIdlocation() {
        return idlocation;
    }

    public void setIdlocation(Integer idlocation) {
        this.idlocation = idlocation;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public Agent getAgenteIdagente() {
        return agenteIdagente;
    }

    public void setAgenteIdagente(Agent agenteIdagente) {
        this.agenteIdagente = agenteIdagente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlocation != null ? idlocation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        if ((this.idlocation == null && other.idlocation != null) || (this.idlocation != null && !this.idlocation.equals(other.idlocation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cinvestav.locator.dao.Location[ idlocation=" + idlocation + " ]";
    }
    
}
