package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "school")
public class School {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "schol_number")
    private int schoolNumber;

    public School() {
    }

    public School(int schoolNumber) {
        this.schoolNumber = schoolNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSchoolNumber() {
        return schoolNumber;
    }

    public void setSchoolNumber(int schoolNumber) {
        this.schoolNumber = schoolNumber;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", schoolNumber=" + schoolNumber +
                '}';
    }
}
