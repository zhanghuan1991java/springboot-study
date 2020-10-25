package com.didispace.web.M000_beans;

import cn.hutool.json.JSONUtil;
import lombok.Data;

@Data
public class Menu {
    private String menu_id;
    private String menu_text;
    private String menu_href;
    private String parent_menu_id;
    private int menu_order;
    private String is_enable;

    @Override
    public String toString() {
        return JSONUtil.parseObj(this).toString();
    }

    public String toStringPretty() {
        return JSONUtil.parseObj(this).toStringPretty();
    }
}
