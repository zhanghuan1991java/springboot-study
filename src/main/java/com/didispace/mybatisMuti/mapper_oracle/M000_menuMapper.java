package com.didispace.mybatisMuti.mapper_oracle;

import com.didispace.web.M000_beans.Menu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface M000_menuMapper {

    /**
     * 查询二级菜单
     * @param parentMenuId
     * @return
     */
    @Select("select * from a_menu m where m.parent_menu_id = #{parentMenuId}")
    public List<Menu> selectSubMenu(@Param("parentMenuId") String parentMenuId);

    /**
     * 查询root菜单
     * @return
     */
    @Select("select * from a_menu a where a.parent_menu_id = 'root' and a.is_enable = 'YES' order by a.menu_order asc")
    public List<Menu> selectRootMenu();
}
