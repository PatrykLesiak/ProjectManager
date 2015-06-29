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

/**
 *
 * @author Patryk
 */
@Entity
@Table(name = "INVITESTOPROJECTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invitestoprojects.findAll", query = "SELECT i FROM Invitestoprojects i"),
    @NamedQuery(name = "Invitestoprojects.findById", query = "SELECT i FROM Invitestoprojects i WHERE i.id = :id")})
public class Invitestoprojects implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    @ManyToOne(optional = false)
    private User userid;
    @JoinColumn(name = "MODULEID", referencedColumnName = "MODULEID")
    @ManyToOne(optional = false)
    private Module moduleid;

    public Invitestoprojects() {
    }

    public Invitestoprojects(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
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
        if (!(object instanceof Invitestoprojects)) {
            return false;
        }
        Invitestoprojects other = (Invitestoprojects) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.agh.wfiis.database.Invitestoprojects[ id=" + id + " ]";
    }
    
}
