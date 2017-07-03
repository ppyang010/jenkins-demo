package org.movie.model;

import java.util.Date;

public class AccessLog {
    private Integer id;

    private Integer portaltype;

    private Date accesstime;

    private Date livetime;

    private String sessionid;

    private String ip;

    private String agent;

    private String uri;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPortaltype() {
        return portaltype;
    }

    public void setPortaltype(Integer portaltype) {
        this.portaltype = portaltype;
    }

    public Date getAccesstime() {
        return accesstime;
    }

    public void setAccesstime(Date accesstime) {
        this.accesstime = accesstime;
    }

    public Date getLivetime() {
        return livetime;
    }

    public void setLivetime(Date livetime) {
        this.livetime = livetime;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid == null ? null : sessionid.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent == null ? null : agent.trim();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
    }
}