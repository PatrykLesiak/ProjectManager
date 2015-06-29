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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Patryk
 */
@Entity
@Table(name = "USERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserid", query = "SELECT u FROM User u WHERE u.userid = :userid"),
    @NamedQuery(name = "User.findByLastname", query = "SELECT u FROM User u WHERE u.lastname = :lastname"),
    @NamedQuery(name = "User.findByFirstname", query = "SELECT u FROM User u WHERE u.firstname = :firstname"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByAvatarlink", query = "SELECT u FROM User u WHERE u.avatarlink = :avatarlink"),
    @NamedQuery(name = "User.findByReadmelink", query = "SELECT u FROM User u WHERE u.readmelink = :readmelink")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USERID")
    private Integer userid;
    @Size(max = 255)
    @Column(name = "LASTNAME")
    private String lastname;
    @Size(max = 255)
    @Column(name = "FIRSTNAME")
    private String firstname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "PASSWORD")
    private String password;
    @Size(max = 255)
    @Column(name = "AVATARLINK")
    private String avatarlink;
    @Size(max = 255)
    @Column(name = "READMELINK")
    private String readmelink;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<UsersToTechnologies> usersToTechnologiesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<Invitestoprojects> invitestoprojectsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<Asksforcollaboration> asksforcollaborationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<UsersToModules> usersToModulesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "leaderid")
    private Collection<Project> projectCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<UsersGroups> usersGroupsCollection;

    public User() {
    }

    public User(Integer userid) {
        this.userid = userid;
    }

    public User(Integer userid, String email, String password) {
        this.userid = userid;
        this.email = email;
        this.password = password;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarlink() {
        return avatarlink;
    }

    public void setAvatarlink(String avatarlink) {
        this.avatarlink = avatarlink;
    }

    public String getReadmelink() {
        return readmelink;
    }

    public void setReadmelink(String readmelink) {
        this.readmelink = readmelink;
    }

    @XmlTransient
    public Collection<UsersToTechnologies> getUsersToTechnologiesCollection() {
        return usersToTechnologiesCollection;
    }

    public void setUsersToTechnologiesCollection(Collection<UsersToTechnologies> usersToTechnologiesCollection) {
        this.usersToTechnologiesCollection = usersToTechnologiesCollection;
    }

    @XmlTransient
    public Collection<Invitestoprojects> getInvitestoprojectsCollection() {
        return invitestoprojectsCollection;
    }

    public void setInvitestoprojectsCollection(Collection<Invitestoprojects> invitestoprojectsCollection) {
        this.invitestoprojectsCollection = invitestoprojectsCollection;
    }

    @XmlTransient
    public Collection<Asksforcollaboration> getAsksforcollaborationCollection() {
        return asksforcollaborationCollection;
    }

    public void setAsksforcollaborationCollection(Collection<Asksforcollaboration> asksforcollaborationCollection) {
        this.asksforcollaborationCollection = asksforcollaborationCollection;
    }

    @XmlTransient
    public Collection<UsersToModules> getUsersToModulesCollection() {
        return usersToModulesCollection;
    }

    public void setUsersToModulesCollection(Collection<UsersToModules> usersToModulesCollection) {
        this.usersToModulesCollection = usersToModulesCollection;
    }

    @XmlTransient
    public Collection<Project> getProjectCollection() {
        return projectCollection;
    }

    public void setProjectCollection(Collection<Project> projectCollection) {
        this.projectCollection = projectCollection;
    }

    @XmlTransient
    public Collection<UsersGroups> getUsersGroupsCollection() {
        return usersGroupsCollection;
    }

    public void setUsersGroupsCollection(Collection<UsersGroups> usersGroupsCollection) {
        this.usersGroupsCollection = usersGroupsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.agh.wfiis.database.User[ userid=" + userid + " ]";
    }
    
}
