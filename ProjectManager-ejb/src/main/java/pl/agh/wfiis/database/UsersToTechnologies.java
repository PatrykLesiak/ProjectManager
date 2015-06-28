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
@Table(name = "USERS_TO_TECHNOLOGIES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersToTechnologies.findAll", query = "SELECT u FROM UsersToTechnologies u"),
    @NamedQuery(name = "UsersToTechnologies.findById", query = "SELECT u FROM UsersToTechnologies u WHERE u.id = :id")})
public class UsersToTechnologies implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    @ManyToOne(optional = false)
    private User userid;
    @JoinColumn(name = "TECHNOLOGYID", referencedColumnName = "TECHNOLOGYID")
    @ManyToOne(optional = false)
    private Technology technologyid;

    public UsersToTechnologies() {
    }

    public UsersToTechnologies(Integer id) {
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

    public Technology getTechnologyid() {
        return technologyid;
    }

    public void setTechnologyid(Technology technologyid) {
        this.technologyid = technologyid;
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
        if (!(object instanceof UsersToTechnologies)) {
            return false;
        }
        UsersToTechnologies other = (UsersToTechnologies) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.agh.wfiis.database.UsersToTechnologies[ id=" + id + " ]";
    }
    
}
