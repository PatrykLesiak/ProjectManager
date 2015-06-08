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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "MODULES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Module.findAll", query = "SELECT m FROM Module m"),
    @NamedQuery(name = "Module.findByModuleid", query = "SELECT m FROM Module m WHERE m.moduleid = :moduleid"),
    @NamedQuery(name = "Module.findByTitle", query = "SELECT m FROM Module m WHERE m.title = :title"),
    @NamedQuery(name = "Module.findByDescription", query = "SELECT m FROM Module m WHERE m.description = :description"),
    @NamedQuery(name = "Module.findByReadmelink", query = "SELECT m FROM Module m WHERE m.readmelink = :readmelink"),
    @NamedQuery(name = "Module.findByRecruting", query = "SELECT m FROM Module m WHERE m.recruting = :recruting")})
public class Module implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "moduleid")
    private Collection<UsersToModules> usersToModulesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "moduleid")
    private Collection<TechnologiesToModules> technologiesToModulesCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MODULEID")
    private Integer moduleid;
    @Size(max = 255)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 1024)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 255)
    @Column(name = "READMELINK")
    private String readmelink;
    @Column(name = "RECRUTING")
    private Boolean recruting;
    @ManyToMany(mappedBy = "moduleCollection")
    private Collection<User> userCollection;
    @ManyToMany(mappedBy = "moduleCollection")
    private Collection<Technology> technologyCollection;
    @JoinColumn(name = "PROJECTID", referencedColumnName = "PROJECTID")
    @ManyToOne(optional = false)
    private Project projectid;

    public Module() {
    }

    public Module(Integer moduleid) {
        this.moduleid = moduleid;
    }

    public Integer getModuleid() {
        return moduleid;
    }

    public void setModuleid(Integer moduleid) {
        this.moduleid = moduleid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReadmelink() {
        return readmelink;
    }

    public void setReadmelink(String readmelink) {
        this.readmelink = readmelink;
    }

    public Boolean getRecruting() {
        return recruting;
    }

    public void setRecruting(Boolean recruting) {
        this.recruting = recruting;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @XmlTransient
    public Collection<Technology> getTechnologyCollection() {
        return technologyCollection;
    }

    public void setTechnologyCollection(Collection<Technology> technologyCollection) {
        this.technologyCollection = technologyCollection;
    }

    public Project getProjectid() {
        return projectid;
    }

    public void setProjectid(Project projectid) {
        this.projectid = projectid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (moduleid != null ? moduleid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Module)) {
            return false;
        }
        Module other = (Module) object;
        if ((this.moduleid == null && other.moduleid != null) || (this.moduleid != null && !this.moduleid.equals(other.moduleid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.agh.wfiis.database.Module[ moduleid=" + moduleid + " ]";
    }

    @XmlTransient
    public Collection<UsersToModules> getUsersToModulesCollection() {
        return usersToModulesCollection;
    }

    public void setUsersToModulesCollection(Collection<UsersToModules> usersToModulesCollection) {
        this.usersToModulesCollection = usersToModulesCollection;
    }

    @XmlTransient
    public Collection<TechnologiesToModules> getTechnologiesToModulesCollection() {
        return technologiesToModulesCollection;
    }

    public void setTechnologiesToModulesCollection(Collection<TechnologiesToModules> technologiesToModulesCollection) {
        this.technologiesToModulesCollection = technologiesToModulesCollection;
    }
    
}
