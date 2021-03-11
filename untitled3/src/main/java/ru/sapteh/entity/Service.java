package ru.sapteh.entity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

//POJO

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;
    @Column
    private double cost;
    @Column
    private int durationInSeconds;
    @Column
    private String description;
    @Column
    private double discount;
    @Column
    private String mainImagePath;

    @OneToMany(mappedBy = "service", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ClientService> clientServiceSet;

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                ", durationInSeconds=" + durationInSeconds +
                ", description='" + description + '\'' +
                ", discount=" + discount +
                ", mainImagePath='" + mainImagePath + '\'' +
                '}';
    }
}
