package ru.sapteh.entity;
//POJO

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "gender")
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private char Code;

    @Column
    private String Name;

    @Override
    public String toString() {
        return "Gender{" +
                "Code=" + Code + '\'' +
                '}';
    }

}
