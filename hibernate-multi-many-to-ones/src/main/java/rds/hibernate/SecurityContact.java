package rds.hibernate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 8/1/11
 * Time: 10:56 AM
 */
@Entity
public class SecurityContact implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "securityContact_id")
    private Long securityContactId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "agent_id", referencedColumnName = "contact_id")
    private AgentContact agentContact;

    @ManyToOne
    @JoinColumn(name = "audit_id", referencedColumnName = "contact_id")
    private AgentContact auditContact;

    public SecurityContact() {
    }

    public Long getSecurityContactId() {
        return this.securityContactId;
    }

    public void setSecurityContactId(Long securityContactId) {
        this.securityContactId = securityContactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AgentContact getAgentContact() {
        return this.agentContact;
    }

    public void setAgentContact(AgentContact agentContact) {
        this.agentContact = agentContact;
    }

    public AgentContact getAuditContact() {
        return this.auditContact;
    }

    public void setAuditContact(AgentContact auditContact) {
        this.auditContact = auditContact;
    }

    @Override
    public String toString() {
        return "SecurityContact{" +
                       "name='" + name + '\'' +
                       ", agentContact=" + agentContact +
                       ", auditContact=" + auditContact +
                       '}';
    }
}