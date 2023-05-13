package com.example.invoicemanager.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contracts")
public class Contract  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contract_number")
    private String contractNumber;

    @Column(name = "contract_description")
    private String contractDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "unit_price")
    private Long unitPrice;

    @Column(name = "quantity")
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "barrel_type_id")
    private BarrelType barrelType;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "advance_payment")
    private Long advancePayment;

    @Column(name = "performance_bond")
    private Long performanceBond;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    private List<Addendum> addendums = new ArrayList<>();

    // جمع بشکه های تعهد شده در قرارداد و الحاقیه ها
    public Long getTotalBarrels() {
        int total = 0;
        if (addendums != null) {
            for (Addendum addendum : addendums) {
                total += addendum.getQuantity();
            }
        }
        return total + quantity;
    }
    //جمع قیمت قرارداد و الحاقیه ها
    public Long getTotalAmount() {
        long totalAmount = 0L;
        if (quantity != null && unitPrice != null) {
            totalAmount = quantity * unitPrice;
        }
        if (addendums != null) {
            for (Addendum addendum : addendums) {
                if (addendum.getQuantity() != null && addendum.getUnitPrice() != null) {
                    totalAmount += addendum.getQuantity() * addendum.getUnitPrice();
                }
            }
        }
        return totalAmount;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Contract contract = (Contract) o;
        return id != null && Objects.equals(id, contract.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
