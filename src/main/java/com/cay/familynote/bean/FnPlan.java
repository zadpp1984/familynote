package com.cay.familynote.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "FnPlan")
@Getter
@Setter
@ToString
public class FnPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long planid;

    @Column(nullable = false)
    Long groupid;

    @Column(nullable = false)
    Long createrid;

    @Column
    Long executerid;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String content;

    @Column
    Boolean importance;

    @Column
    Boolean remind;

    //    @Column(nullable = false)
    @Column
    Time createtimestamp;

    @Column
    Time remindtimestamp;
}
