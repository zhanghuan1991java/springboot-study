package com.didispace.monitor;

import com.didispace.mybatisMuti.beans.A0001_UserBehaviorReport;
import com.didispace.mybatisMuti.mapper_mysql.A0001_UserBehaviorReportMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class A0001_UserBehaviorReportController {

    @Autowired
    private A0001_UserBehaviorReportMapper mapper;

    @RequestMapping(value = "/userBehaviorReport", method = RequestMethod.POST)
    public String report(@RequestBody A0001_UserBehaviorReport reportData){
        mapper.insert(reportData);
        return "success";
    }

}
