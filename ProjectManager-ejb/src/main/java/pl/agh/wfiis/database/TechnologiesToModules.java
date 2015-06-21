package pl.agh.wfiis.database;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "TECHNOLOGIES_TO_MODULES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TechnologiesToModules.findAll", query = "SELECT t FROM TechnologiesToModules t"),
    @NamedQuery(name = "TechnologiesToModules.findById", query = "SELECT t FROM TechnologiesToModules t WHERE t.id = :id")})
public class TechnologiesToModules implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "TECHNOLOGYID", referencedColumnName = "TECHNOLOGYID")
    @ManyToOne(optional = false)
    private Technology technologyid;
    @JoinColumn(name = "MODULEID", referencedColumnName = "MODULEID")
    @ManyToOne(optional = false)
    private Module moduleid;

    public TechnologiesToModules() {
    }

    public TechnologiesToModules(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Technology getTechnologyid() {
        return technologyid;
    }

    public void setTechnologyid(Technology technologyid) {
        this.technologyid = technologyid;
    }

    public Module getModuleid() {
        return moduleid;
    }

    public void setModuleid(Module moduleid) {
        this.moduleid = moduleid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TechnologiesToModules)) {
            return false;
        }
        TechnologiesToModules other = (TechnologiesToModules) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.agh.wfiis.database.TechnologiesToModules[ id=" + id + " ]";
    }
    
}
