package com.didispace.mybatisMuti.mapper_mysql;

import com.didispace.mybatisMuti.beans.UserOperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOperateLogMapper {

    @Insert("insert into a_user_operate_log(id,user_id,oper_log) values(#{id},#{user_id},#{oper_log})")
    int insertUserOperateLog(UserOperateLog userOperateLog);
}
