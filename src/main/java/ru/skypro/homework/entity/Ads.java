package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "ads")
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User author;
    private int price;
    private String image;
    private String title;
    private String description;
}