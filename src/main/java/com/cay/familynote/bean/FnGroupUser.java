package com.cay.familynote.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "FnGroupUser")
@Getter
@Setter
public class FnGroupUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    Long groupid;

    @Column
    Long userid;

    @Column
    boolean isadmin;

    public FnGroupUser(long groupid, long userid, boolean isadmin) {
        this.groupid = groupid;
        this.userid = userid;
        this.isadmin = isadmin;
    }
}
