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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "PROJECTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p"),
    @NamedQuery(name = "Project.findByProjectid", query = "SELECT p FROM Project p WHERE p.projectid = :projectid"),
    @NamedQuery(name = "Project.findByTitle", query = "SELECT p FROM Project p WHERE p.title = :title"),
    @NamedQuery(name = "Project.findByDescription", query = "SELECT p FROM Project p WHERE p.description = :description"),
    @NamedQuery(name = "Project.findByRreadmelink", query = "SELECT p FROM Project p WHERE p.rreadmelink = :rreadmelink"),
    @NamedQuery(name = "Project.findByContactandlinks", query = "SELECT p FROM Project p WHERE p.contactandlinks = :contactandlinks"),
    @NamedQuery(name = "Project.findByPicturelink", query = "SELECT p FROM Project p WHERE p.picturelink = :picturelink")})
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PROJECTID")
    private Integer projectid;
    @Size(max = 255)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 1024)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 255)
    @Column(name = "RREADMELINK")
    private String rreadmelink;
    @Size(max = 1024)
    @Column(name = "CONTACTANDLINKS")
    private String contactandlinks;
    @Size(max = 255)
    @Column(name = "PICTURELINK")
    private String picturelink;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectid")
    private Collection<Module> moduleCollection;
    @JoinColumn(name = "LEADERID", referencedColumnName = "USERID")
    @ManyToOne(optional = false)
    private User leaderid;
    @JoinColumn(name = "MODERATORID", referencedColumnName = "USERID")
    @ManyToOne(optional = false)
    private User moderatorid;

    public Project() {
    }

    public Project(Integer projectid) {
        this.projectid = projectid;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
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

    public String getRreadmelink() {
        return rreadmelink;
    }

    public void setRreadmelink(String rreadmelink) {
        this.rreadmelink = rreadmelink;
    }

    public String getContactandlinks() {
        return contactandlinks;
    }

    public void setContactandlinks(String contactandlinks) {
        this.contactandlinks = contactandlinks;
    }

    public String getPicturelink() {
        return picturelink;
    }

    public void setPicturelink(String picturelink) {
        this.picturelink = picturelink;
    }

    @XmlTransient
    public Collection<Module> getModuleCollection() {
        return moduleCollection;
    }

    public void setModuleCollection(Collection<Module> moduleCollection) {
        this.moduleCollection = moduleCollection;
    }

    public User getLeaderid() {
        return leaderid;
    }

    public void setLeaderid(User leaderid) {
        this.leaderid = leaderid;
    }

    public User getModeratorid() {
        return moderatorid;
    }

    public void setModeratorid(User moderatorid) {
        this.moderatorid = moderatorid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectid != null ? projectid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.projectid == null && other.projectid != null) || (this.projectid != null && !this.projectid.equals(other.projectid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.agh.wfiis.database.Project[ projectid=" + projectid + " ]";
    }
    
}
