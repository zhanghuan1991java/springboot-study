package com.didispace.mybatisMuti.mapper_mysql;

import com.didispace.mybatisMuti.beans.UserOperate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserOperateMapper {

    List<UserOperate> getAllUserOperate();
}
