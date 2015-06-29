package pl.agh.wfiis.database;

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
import javax.validation.constraints.NotNull;
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
    @NamedQuery(name = "Invitestoprojects.findById", query = "SELECT i FROM Invitestoprojects i WHERE i.id = :id"),
    @NamedQuery(name = "Invitestoprojects.findByUserid", query = "SELECT i FROM Invitestoprojects i WHERE i.userid = :userid"),
    @NamedQuery(name = "Invitestoprojects.findByModuleid", query = "SELECT i FROM Invitestoprojects i WHERE i.moduleid = :moduleid")})
public class Invitestoprojects implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USERID")
    private int userid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MODULEID")
    private int moduleid;

    public Invitestoprojects() {
    }

    public Invitestoprojects(Integer id) {
        this.id = id;
    }

    public Invitestoprojects(Integer id, int userid, int moduleid) {
        this.id = id;
        this.userid = userid;
        this.moduleid = moduleid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getModuleid() {
        return moduleid;
    }

    public void setModuleid(int moduleid) {
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
