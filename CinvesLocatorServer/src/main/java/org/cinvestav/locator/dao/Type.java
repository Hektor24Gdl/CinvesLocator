package org.cinvestav.locator.dao;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Type.findAll", query = "SELECT t FROM Type t"),
    @NamedQuery(name = "Type.findByIdtype", query = "SELECT t FROM Type t WHERE t.idtype = :idtype"),
    @NamedQuery(name = "Type.findByType", query = "SELECT t FROM Type t WHERE t.type = :type")})
public class Type implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idtype", nullable = false)
    private Integer idtype;
    @Basic(optional = false)
    @Column(name = "type", nullable = false, length = 45)
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeIdtype")
    private Collection<Agent> agentCollection;

    public Type() {
    }

    public Type(Integer idtype) {
        this.idtype = idtype;
    }

    public Type(Integer idtype, String type) {
        this.idtype = idtype;
        this.type = type;
    }

    public Integer getIdtype() {
        return idtype;
    }

    public void setIdtype(Integer idtype) {
        this.idtype = idtype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public Collection<Agent> getAgentCollection() {
        return agentCollection;
    }

    public void setAgentCollection(Collection<Agent> agentCollection) {
        this.agentCollection = agentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtype != null ? idtype.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Type)) {
            return false;
        }
        Type other = (Type) object;
        if ((this.idtype == null && other.idtype != null) || (this.idtype != null && !this.idtype.equals(other.idtype))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cinvestav.locator.dao.Type[ idtype=" + idtype + " ]";
    }
    
}
