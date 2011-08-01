package rds.hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 8/1/11
 * Time: 10:56 AM
 */
@Entity
public class AgentContact implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_id")
    private Long contactId;

    private String name;

    @OneToMany(mappedBy = "agentContact")
    private Set<SecurityContact> securityContacts = new HashSet<SecurityContact>();

    public AgentContact() {
    }

    public Long getContactId() {
        return this.contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SecurityContact> getSecurityContacts() {
        return this.securityContacts;
    }

    public void setSecurityContacts(Set<SecurityContact> securityContacts) {
        this.securityContacts = securityContacts;
    }

    @Override
    public String toString() {
        return "AgentContact{" +
                       "name='" + name + '\'' +
                       '}';
    }
}
