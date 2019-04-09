package com.cay.familynote.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

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

    @Column
    Long createrid;

    @Column
    String creatername;

    @Column
    Long executerid;

    @Column
    String executername;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String content;

    @Column
    Boolean importance;

    @Column
    Boolean remind;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    @Column
    Timestamp createtimestamp;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    @Column
    Timestamp remindtimestamp;

    @Column
    String state;
}
