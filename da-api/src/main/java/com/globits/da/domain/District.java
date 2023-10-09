package com.globits.da.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_district")
public class District {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;
    @Column(name = "type")
    private String type;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "province_id",nullable=false,unique=true)
    private Province province;
    @OneToMany(mappedBy = "district", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Commune> communes;
}
