package org.cinvestav.locator.dao;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Héctor Guillermo Rodríguez Fuentes <hector.rodgz@facturasi.com>
 */
@Entity
@Table(name = "agent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agent.findAll", query = "SELECT a FROM Agent a"),
    @NamedQuery(name = "Agent.findByIdagente", query = "SELECT a FROM Agent a WHERE a.idagente = :idagente"),
    @NamedQuery(name = "Agent.findByName", query = "SELECT a FROM Agent a WHERE a.name = :name"),
    @NamedQuery(name = "Agent.findByDevice", query = "SELECT a FROM Agent a WHERE a.device = :device"),
    @NamedQuery(name = "Agent.findByLastLogin", query = "SELECT a FROM Agent a WHERE a.lastLogin = :lastLogin"),
    @NamedQuery(name = "Agent.findByType", query = "SELECT a FROM Agent a WHERE a.typeIdtype.type = :type"),
})
public class Agent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idagente", nullable = false)
    private Integer idagente;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @Column(name = "device", length = 45)
    private String device;
    @Column(name = "last_login", length = 45)
    private String lastLogin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agenteIdagente")
    private Collection<Location> locationCollection;
    @JoinColumn(name = "type_idtype", referencedColumnName = "idtype", nullable = false)
    @ManyToOne(optional = false)
    private Type typeIdtype;

    public Agent() {
    }

    public Agent(Integer idagente) {
        this.idagente = idagente;
    }

    public Agent(Integer idagente, String name) {
        this.idagente = idagente;
        this.name = name;
    }

    public Integer getIdagente() {
        return idagente;
    }

    public void setIdagente(Integer idagente) {
        this.idagente = idagente;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    @XmlTransient
    public Collection<Location> getLocationCollection() {
        return locationCollection;
    }

    public void setLocationCollection(Collection<Location> locationCollection) {
        this.locationCollection = locationCollection;
    }

    public Type getTypeIdtype() {
        return typeIdtype;
    }

    public void setTypeIdtype(Type typeIdtype) {
        this.typeIdtype = typeIdtype;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idagente != null ? idagente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agent)) {
            return false;
        }
        Agent other = (Agent) object;
        if ((this.idagente == null && other.idagente != null) || (this.idagente != null && !this.idagente.equals(other.idagente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cinvestav.locator.dao.Agent[ idagente=" + idagente + " ]";
    }
    
}
