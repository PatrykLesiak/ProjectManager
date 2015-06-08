package pl.agh.wfiis.database;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "TECHNOLOGIES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Technology.findAll", query = "SELECT t FROM Technology t"),
    @NamedQuery(name = "Technology.findByTechnologyid", query = "SELECT t FROM Technology t WHERE t.technologyid = :technologyid"),
    @NamedQuery(name = "Technology.findByName", query = "SELECT t FROM Technology t WHERE t.name = :name")})
public class Technology implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "technologyid")
    private Collection<UsersToTechnologies> usersToTechnologiesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "technologyid")
    private Collection<TechnologiesToModules> technologiesToModulesCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TECHNOLOGYID")
    private Integer technologyid;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @ManyToMany(mappedBy = "technologyCollection")
    private Collection<User> userCollection;
    @JoinTable(name = "TECHNOLOGIES_TO_MODULES", joinColumns = {
        @JoinColumn(name = "TECHNOLOGYID", referencedColumnName = "TECHNOLOGYID")}, inverseJoinColumns = {
        @JoinColumn(name = "MODULEID", referencedColumnName = "MODULEID")})
    @ManyToMany
    private Collection<Module> moduleCollection;

    public Technology() {
    }

    public Technology(Integer technologyid) {
        this.technologyid = technologyid;
    }

    public Integer getTechnologyid() {
        return technologyid;
    }

    public void setTechnologyid(Integer technologyid) {
        this.technologyid = technologyid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @XmlTransient
    public Collection<Module> getModuleCollection() {
        return moduleCollection;
    }

    public void setModuleCollection(Collection<Module> moduleCollection) {
        this.moduleCollection = moduleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (technologyid != null ? technologyid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Technology)) {
            return false;
        }
        Technology other = (Technology) object;
        if ((this.technologyid == null && other.technologyid != null) || (this.technologyid != null && !this.technologyid.equals(other.technologyid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.agh.wfiis.database.Technology[ technologyid=" + technologyid + " ]";
    }

    @XmlTransient
    public Collection<UsersToTechnologies> getUsersToTechnologiesCollection() {
        return usersToTechnologiesCollection;
    }

    public void setUsersToTechnologiesCollection(Collection<UsersToTechnologies> usersToTechnologiesCollection) {
        this.usersToTechnologiesCollection = usersToTechnologiesCollection;
    }

    @XmlTransient
    public Collection<TechnologiesToModules> getTechnologiesToModulesCollection() {
        return technologiesToModulesCollection;
    }

    public void setTechnologiesToModulesCollection(Collection<TechnologiesToModules> technologiesToModulesCollection) {
        this.technologiesToModulesCollection = technologiesToModulesCollection;
    }
    
}
