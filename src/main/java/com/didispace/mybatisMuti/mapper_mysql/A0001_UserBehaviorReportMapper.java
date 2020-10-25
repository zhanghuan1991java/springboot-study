package com.didispace.mybatisMuti.mapper_mysql;

import com.didispace.mybatisMuti.beans.A0001_UserBehaviorReport;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface A0001_UserBehaviorReportMapper {

    @Insert("insert into a0001_user_behavior_report(user_id,operate_time,page,operate) values(#{user_id},#{operate_time},#{page},#{operate})")
    public int insert(A0001_UserBehaviorReport userBehaviorReport);



}
