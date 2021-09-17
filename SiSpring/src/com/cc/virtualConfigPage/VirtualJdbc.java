package com.cc.virtualConfigPage;

public class VirtualJdbc extends VirtualJdbcFather{
    private String url;
    private String username;
    private String password;
    private String table;

    public VirtualJdbc() {
    }

    public void start(){
        System.out.println(this.toString());
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return "VirtualJdbc{" +
                "url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", table='" + table + '\'' +
                '}';
    }
}
