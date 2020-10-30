package com.didispace.mybatisMuti.mapper_oracle;

import com.didispace.web.M000_beans.Menu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface M000_menuMapper {

    @Select("select m.menu_id,m.menu_text,m.menu_href,m.menu_order from a_menu m where m.parent_menu_id = #{parentMenuId}")
    public List<Menu> selectSubMenu(@Param("parentMenuId") String parentMenuId);

}
