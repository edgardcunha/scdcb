package edu.ifes.ci.si.les.scdcb.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "tipos_cesta")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class TipoDeCesta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    @NotBlank(message = "Nome do Tipo de Cesta deve ser preenchido")
    @Size(min = 2, max = 50, message = "Nome do Tipo de Cesta deve ter entre 2 e 50 letras")
    private String nome;

    @NotNull(message = "Descrição do Tipo de Cesta deve ser preenchido")
    @Size(min = 2, max = 150, message = "Descrição do Tipo de Cesta deve ter entre 2 e 150 letras")
    private String descricao;
    
}
