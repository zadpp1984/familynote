package com.cay.familynote.dao;

import com.cay.familynote.bean.FnLoginUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FnLoginUserDaoTest {

    Logger logger = LoggerFactory.getLogger( FnLoginUserDaoTest.class );
    @Autowired
    FnLoginUserDao fnLoginUserDao;

    @Test
    public void selectByUserName() {

        String username = "aaaaa";
        String password = "bbbbbb";

        FnLoginUser fnLoginUser_example = new FnLoginUser();
        fnLoginUser_example.setUsername( username );
        fnLoginUser_example.setPassword( password );

        ExampleMatcher matcher = ExampleMatcher.matching() .withIgnoreNullValues()
                .withMatcher( "username", new ExampleMatcher.GenericPropertyMatcher().exact() );

        Example<FnLoginUser> example = Example.of( fnLoginUser_example, matcher );

        Optional<FnLoginUser> optionalFnLoginUser = fnLoginUserDao.findOne( example );

        assertEquals( optionalFnLoginUser.isPresent(), true );
    }
}