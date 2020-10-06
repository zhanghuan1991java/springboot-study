package com.didispace.mybatisMuti.mapper_mysql;

import com.didispace.mybatisMuti.beans.UserOperate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper
public interface UserOperateMapper {

    List<UserOperate> getAllUserOperate();
}
