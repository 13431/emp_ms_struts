package com.nf.empst.action;

public class LoginAction {

    private String name;

    public String signup() throws Exception {
        System.out.println(name);
        return "success";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
