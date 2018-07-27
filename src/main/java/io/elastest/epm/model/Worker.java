package io.elastest.epm.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.elastest.epm.repository.IdGenerator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A worker object for registering a machine where adapters can be deployed.
 */
@ApiModel(description = "A worker object for registering a machine where adapters can be deployed.")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-07-27T13:38:02.648+02:00")
@Entity
public class Worker {
    @JsonProperty("id")
    @Id
    private String id = null;

    @JsonProperty("ip")
    private String ip = null;

    @JsonProperty("user")
    private String user = null;

    @JsonProperty("passphrase")
    private String passphrase = null;

    @JsonProperty("password")
    private String password = null;

    @JsonProperty("epmIp")
    private String epmIp = null;

    @JsonProperty("keyname")
    private String keyname = null;

    @JsonProperty("port")
    private Integer port = null;

    @PrePersist
    public void ensureId() {
        id = IdGenerator.createUUID();
    }

    public Worker id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Identifier for the Adapter.
     *
     * @return id
     **/
    @ApiModelProperty(value = "Identifier for the Adapter.")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Worker ip(String ip) {
        this.ip = ip;
        return this;
    }

    /**
     * The IP where the Worker is reachable. The EPM will try to ssh in to the Worker at this IP.
     *
     * @return ip
     **/
    @ApiModelProperty(required = true, value = "The IP where the Worker is reachable. The EPM will try to ssh in to the Worker at this IP.")
    @NotNull


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Worker user(String user) {
        this.user = user;
        return this;
    }

    /**
     * This is the user, which the EPM will use when trying to ssh in to the Worker.
     *
     * @return user
     **/
    @ApiModelProperty(example = "ubuntu", required = true, value = "This is the user, which the EPM will use when trying to ssh in to the Worker.")
    @NotNull


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Worker passphrase(String passphrase) {
        this.passphrase = passphrase;
        return this;
    }

    /**
     * This is the Passphrase of the Key provided for connecting to the Worker.
     *
     * @return passphrase
     **/
    @ApiModelProperty(required = true, value = "This is the Passphrase of the Key provided for connecting to the Worker.")
    @NotNull


    public String getPassphrase() {
        return passphrase;
    }

    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }

    public Worker password(String password) {
        this.password = password;
        return this;
    }

    /**
     * This is the password of the user, which can be left blank if no password is needed.
     *
     * @return password
     **/
    @ApiModelProperty(value = "This is the password of the user, which can be left blank if no password is needed.")


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Worker epmIp(String epmIp) {
        this.epmIp = epmIp;
        return this;
    }

    /**
     * This is the IP where the EPM is reachable for the Worker. This is needed because the Worker has to be able to reach the EPM for registering adapters.
     *
     * @return epmIp
     **/
    @ApiModelProperty(required = true, value = "This is the IP where the EPM is reachable for the Worker. This is needed because the Worker has to be able to reach the EPM for registering adapters.")
    @NotNull


    public String getEpmIp() {
        return epmIp;
    }

    public void setEpmIp(String epmIp) {
        this.epmIp = epmIp;
    }

    public Worker keyname(String keyname) {
        this.keyname = keyname;
        return this;
    }

    /**
     * The name of the Key, which the EPM will use for ssh in to the Worker. This refers to the name provided when uploading the Key to the EPM.
     *
     * @return keyname
     **/
    @ApiModelProperty(example = "key1", required = true, value = "The name of the Key, which the EPM will use for ssh in to the Worker. This refers to the name provided when uploading the Key to the EPM.")
    @NotNull


    public String getKeyname() {
        return keyname;
    }

    public void setKeyname(String keyname) {
        this.keyname = keyname;
    }

    public Worker port(Integer port) {
        this.port = port;
        return this;
    }

    /**
     * The ssh port of the worker, where the EPM can reach it.
     *
     * @return port
     **/
    @ApiModelProperty(value = "The ssh port of the worker, where the EPM can reach it.")


    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Worker worker = (Worker) o;
        return Objects.equals(this.id, worker.id) &&
                Objects.equals(this.ip, worker.ip) &&
                Objects.equals(this.user, worker.user) &&
                Objects.equals(this.passphrase, worker.passphrase) &&
                Objects.equals(this.password, worker.password) &&
                Objects.equals(this.epmIp, worker.epmIp) &&
                Objects.equals(this.keyname, worker.keyname) &&
                Objects.equals(this.port, worker.port);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ip, user, passphrase, password, epmIp, keyname, port);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Worker {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    ip: ").append(toIndentedString(ip)).append("\n");
        sb.append("    user: ").append(toIndentedString(user)).append("\n");
        sb.append("    passphrase: ").append(toIndentedString(passphrase)).append("\n");
        sb.append("    password: ").append(toIndentedString(password)).append("\n");
        sb.append("    epmIp: ").append(toIndentedString(epmIp)).append("\n");
        sb.append("    keyname: ").append(toIndentedString(keyname)).append("\n");
        sb.append("    port: ").append(toIndentedString(port)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

