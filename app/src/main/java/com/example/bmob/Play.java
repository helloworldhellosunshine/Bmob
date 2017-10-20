package com.example.bmob;

import cn.bmob.v3.BmobObject;

/**
 * Created by 王帝 on 2017/10/14.
 */

public class Play extends BmobObject {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String name;
    private String password;

    public Play(String name, String password) {
        this.name = name;
        this.password = password;
    }


}
