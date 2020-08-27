package edu.ifes.ci.si.les.scdcb.model;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.*;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Entity
@Table(name = "recebimentos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"cdRecebimento"})
public class Recebimento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cdRecebimento;

    @Column(updatable = false)
    @NotNull(message = "Data e Hora do Recebimento deve ser preenchida")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="America/Sao_Paulo")
    private Instant dtRecebimento;

    @NotNull(message = "Quantidade de Cestas do Recebimento deve ser preenchida")
    @Min(value = 0L, message = "Quantidade de Cestas do Recebimento deve ser preenchida com um valor inteiro")
    private Integer qtdCestas;

    @Column(length = 150)
    @Size(min = 3, max = 150, message = "NF da Cesta do Recebimento deve ter entre 3 e 50 caracteres alfanuméricos")
    private String nfCesta;

    @NotNull(message = "Intenção deve ser preenchido")
    @OneToOne
    @JoinColumn(name = "cdIntencao")
    private Intencao intencao;

}
