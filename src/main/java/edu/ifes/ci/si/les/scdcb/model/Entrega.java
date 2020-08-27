package edu.ifes.ci.si.les.scdcb.model;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.*;
import lombok.*;

@Entity
@Table(name="entregas")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"cdEntrega"})
public class Entrega implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cdEntrega;

    @NotNull(message = "Data e Hora da Entrega deve ser preenchido")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="America/Sao_Paulo")
    private Instant dtEntrega;
    
    @Min(value = 0L, message = "Quantidade de Cestas deve ser no mínimo 1")
    private Integer qtdCestas;
    
    @NotNull(message = "Documento do Beneficiado deve ser preenchido")
    @Size(min = 3, max = 150, message = "Documento do Beneficiado deve ter entre 3 e 150 letras")
    private String documento;
    
    @NotNull(message = "Solicitação deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "cdSolicitacao")
    private Solicitacao solicitacao;
    
    @Builder
    public Entrega(Instant dtEntrega, Integer qtdCestas, String documento, Solicitacao solicitacao) {
    	super();
        this.dtEntrega = dtEntrega;
    	this.qtdCestas = qtdCestas;
    	this.documento = documento;
    	this.solicitacao = solicitacao;
    }

}
