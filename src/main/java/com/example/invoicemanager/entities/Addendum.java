package com.example.invoicemanager.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "addendum")
public class Addendum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", referencedColumnName = "id")
    private Contract contract;

    @Enumerated(EnumType.STRING)
    @Column(name = "barrel_type")
    private BarrelType barrelType;

    @Column(name = "unit_price")
    private Long unitPrice;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Addendum addendum = (Addendum) o;
        return id != null && Objects.equals(id, addendum.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

