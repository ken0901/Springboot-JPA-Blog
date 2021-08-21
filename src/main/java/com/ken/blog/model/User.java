package com.ken.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// ORM -> object mapping to table
@Entity // User Class is stored in database
public class User {

    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // follow db numbering which is connected a project
    private int id; //sequence, auto-increment

    @Column(nullable = false, length = 30)
    private String username; // id

    @Column(nullable = false, length = 100) //length is 100 because implement encrypt with hashing
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @ColumnDefault("'user'")
    private String role; // Enum is more accuracy

    @CreationTimestamp // auto insert time
    private Timestamp createDate;
}
