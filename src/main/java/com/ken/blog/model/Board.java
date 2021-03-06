package com.ken.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // big data
    private String content; // summer note library

    private int count;

    @ManyToOne(fetch = FetchType.EAGER) // Many = Board, User = One -> one user can create many boards
    @JoinColumn(name = "userId")
    private User user; // DB can't store object. FK,JAVA can store object

    // mappedBy means it's not owner(NOT FK) No create db column
    // cascade = CascadeType.REMOVE - when you delete a board all comment will be removed at the same time
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"board"})
    @OrderBy("id desc")
    private List<Reply> replies;

    @CreationTimestamp
    private Timestamp createDate;
}
