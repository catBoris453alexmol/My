package ru.sapteh.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "clientService")
public class ClientService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "clientID")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "serviceID")
    private Service service;

    @Column
    private Date startTime = new Date();

    @Column
    private String comment;


    @Override
    public String toString() {
        return "ClientService{" +
                "id=" + id +
                ", client=" + client +
                ", service=" + service +
                ", dateTime=" + startTime +
                ", comment='" + comment + '\'' +
                '}';
    }
}
