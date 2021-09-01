package com.ken.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //auto_increment

    @Column(nullable = false, length = 200)
    private String content;

    @ManyToOne
    @JoinColumn(name = "boardID")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @CreationTimestamp
    private Timestamp createDate;
}
