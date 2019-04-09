package com.cay.familynote.controller;

import com.alibaba.fastjson.JSONObject;
import com.cay.familynote.bean.FnGroup;
import com.cay.familynote.bean.FnGroupUser;
import com.cay.familynote.bean.FnLoginUser;
import com.cay.familynote.bean.FnPlan;
import com.cay.familynote.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/familynote")
public class FamilynoteController {

    Logger logger = LoggerFactory.getLogger( FamilynoteController.class );

    @Autowired
    FnUserDao fnUserDao;
    @Autowired
    FnGroupDao fnGroupDao;
    @Autowired
    FnLoginUserDao fnLoginUserDao;
    @Autowired
    FnPlanDao fnPlanDao;
    @Autowired
    FnGroupUserDao fnGroupUserDao;
    @Autowired
    FnCommonDao fnCommonDao;


    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String doLogin(@RequestBody String jsonStr) {
        JSONObject jsonObj = JSONObject.parseObject( jsonStr );
        String username = jsonObj.getString( "username" );
        String password = jsonObj.getString( "password" );

        FnLoginUser fnLoginUser_example = new FnLoginUser();
        fnLoginUser_example.setUsername( username );
        fnLoginUser_example.setPassword( password );

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues()
                .withMatcher( "username", new ExampleMatcher.GenericPropertyMatcher().exact() )
                .withMatcher( "password", new ExampleMatcher.GenericPropertyMatcher().exact() );
        Example<FnLoginUser> example = Example.of( fnLoginUser_example, matcher );

        Optional<FnLoginUser> optionalFnLoginUser = fnLoginUserDao.findOne( example );
        if (!optionalFnLoginUser.isPresent()) {
            JSONObject obj = new JSONObject();
            obj.put( "status", "999" );
            obj.put( "message", "用户不存在或者密码错误！" );
            return obj.toJSONString();
        }
        FnLoginUser fnLoginUser = optionalFnLoginUser.get();
        JSONObject obj = new JSONObject();
        obj.put( "status", "0" );
        obj.put( "message", "用户登录成功" );

        JSONObject data = new JSONObject();
        data.put( "userid", fnLoginUser.getUserid() );
        data.put( "username", fnLoginUser.getUsername() );
        data.put( "nickname", fnLoginUser.getNickname() );

        obj.put( "data", data );

        return obj.toJSONString();
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@RequestBody FnLoginUser user) {
        try {
            fnLoginUserDao.save( user );
        } catch (Exception e) {
            JSONObject obj = new JSONObject();
            obj.put( "status", "999" );
            obj.put( "message", "用户注册失败！" );
            return obj.toJSONString();
        }
        JSONObject obj = new JSONObject();
        obj.put( "status", "0" );
        obj.put( "message", "用户注册成功！" );
        return obj.toJSONString();
    }


    @Transactional
    @RequestMapping(value = "/createGroup/{groupname}/{createrid}", method = RequestMethod.POST)
    public String createGroup(@PathVariable("groupname") String groupname, @PathVariable("createrid") long createrid) {
        FnGroup fnGroup = new FnGroup( groupname, createrid );
        fnGroup = fnGroupDao.save( fnGroup );
        FnGroupUser fnGroupUser = new FnGroupUser( fnGroup.getGroupid(), createrid, true );
        fnGroupUserDao.save( fnGroupUser );
        JSONObject obj = new JSONObject();
        obj.put( "status", "0" );
        obj.put( "message", "创建Group成功！" );
        obj.put( "groupid", fnGroup.getGroupid() );
        obj.put( "groupname", fnGroup.getGroupname() );
        return obj.toJSONString();
    }

    @RequestMapping(value = "/getMyGroups/{userid}", method = RequestMethod.GET)
    public String getMyGroups(@PathVariable("userid") long userid) {
        List<FnGroup> lst = fnGroupDao.findGroupByUserid( userid );
        if (lst == null || lst.size() == 0) {
            JSONObject obj = new JSONObject();
            obj.put( "status", "1" );
            obj.put( "message", "Group不存在！" );
            return obj.toJSONString();
        }

        JSONObject obj = new JSONObject();
        obj.put( "status", "0" );
        obj.put( "message", "获取Group成功！" );
        Object g = JSONObject.toJSON( lst );
        obj.put( "groups", g );
        return obj.toJSONString();
    }

    @Transactional
    @RequestMapping(value = "/addUserToGroup", method = RequestMethod.POST)
    public String addUserToGroup(@RequestBody String jsonStr) {

        JSONObject jsonObj = JSONObject.parseObject( jsonStr );
        long groupid = jsonObj.getLong( "groupid" );
        long userid = jsonObj.getLong( "userid" );
        boolean isadmin = jsonObj.getBooleanValue( "isadmin" );

        FnGroupUser fnGroupUser = new FnGroupUser( groupid, userid, isadmin );
        FnGroupUser rtn = fnGroupUserDao.saveAndFlush( fnGroupUser );
        JSONObject obj = new JSONObject();
        obj.put( "status", "0" );
        obj.put( "message", "追加成功！" );
        return obj.toJSONString();
    }
    @RequestMapping(value = "/getGroupUsers/{groupid}", method = RequestMethod.GET)
    public String getGroupUsers(@PathVariable("groupid") Long groupid){
        List<FnLoginUser> lst = fnLoginUserDao.findGroupUsers(groupid);

        JSONObject obj = new JSONObject();
        if (lst == null) {
            obj.put( "status", "999" );
            obj.put( "message", "取得失败！" );
        } else {
            Object users = JSONObject.toJSON( lst );
            obj.put( "status", "0" );
            obj.put( "message", "计划取得成功！" );
            obj.put( "users", users );
        }
        return obj.toJSONString();
    }
    @RequestMapping(value = "/addPlan", method = RequestMethod.POST)
    public String addPlan(@RequestBody FnPlan fnPlan) {
        try {
            fnPlanDao.save( fnPlan );
        } catch (Exception e) {
            JSONObject obj = new JSONObject();
            obj.put( "status", "999" );
            obj.put( "message", "Plan提交失败！" );
            return obj.toJSONString();
        }
        JSONObject obj = new JSONObject();
        obj.put( "status", "0" );
        obj.put( "message", "Plan提交成功！" );
        return obj.toJSONString();
    }

    @RequestMapping(value = "/getPlans/{groupid}", method = RequestMethod.GET)
    public String getPlans(@PathVariable("groupid") Long groupid) {

        FnPlan fnPlan_example = new FnPlan();
        fnPlan_example.setGroupid( groupid );
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues()
                .withMatcher( "groupid", new ExampleMatcher.GenericPropertyMatcher().exact() );
        Example<FnPlan> example = Example.of( fnPlan_example, matcher );
        List<FnPlan> lst = fnPlanDao.findAll( example );

        JSONObject obj = new JSONObject();
        if (lst == null) {
            obj.put( "status", "0" );
            obj.put( "message", "没有计划！" );
        } else {
            Object planobj = JSONObject.toJSON( lst );
            obj.put( "status", "0" );
            obj.put( "message", "计划取得成功！" );
            obj.put( "plans", planobj );
        }
        return obj.toJSONString();
    }
}
