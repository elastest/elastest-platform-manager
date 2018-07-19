package io.elastest.epm.pop.model.common;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class NfvEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected String id;

    @Version
    protected Integer hbVersion = 0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getHbVersion() {
        return hbVersion;
    }

    public void setHbVersion(Integer hbVersion) {
        this.hbVersion = hbVersion;
    }

    @Override
    public String toString() {
        return "NfvEntity{" + "id='" + id + '\'' + ", hbVersion=" + hbVersion + '\'' + '}';
    }
}
