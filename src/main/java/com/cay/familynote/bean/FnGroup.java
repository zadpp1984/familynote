package com.cay.familynote.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "FnGroup")
@Getter
@Setter
public class FnGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long groupid;

    @Column
    String groupname;

    @Column
    long creater;

    //    @Column(length = 11)
//    int root_user;
    public FnGroup() {

    }

    public FnGroup(String groupname, long creater) {
        this.groupname = groupname;
        this.creater = creater;
    }

    public FnGroup(Long groupid, String groupname, long creater) {
        this.groupid = groupid;
        this.groupname = groupname;
        this.creater = creater;
    }
}
