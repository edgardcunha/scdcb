package edu.ifes.ci.si.les.scdcb.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

@Entity
@Table(name="beneficiados")
@Data
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name="cdUsuario")
public class Beneficiado extends Usuario {

    private static final long serialVersionUID = 1L;
    
    @Min(value = 0L, message = "Número de Pessoas da Família do Beneficiado deve ser preenchido com um valor inteiro")
    private Integer npf;
    
    @Min(value = 0L, message = "Número da Casa do Beneficiado deve ser preenchido com um valor inteiro")
    private Integer numCasa;
    
    @Column(length = 50)
    @NotBlank(message = "Rua da Beneficiado deve ser preenchido")
    @Size(min = 2, max = 50, message = "Rua do Beneficiado deve ter entre 2 e 50 letras")
    private String rua;

    @NotNull(message = "O Bairro do Beneficiado deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "cdBairro")
    private Bairro bairro;

    @Builder
    public Beneficiado(Integer npf, Integer numCasa, String rua, Bairro bairro, String nome, String login, String senha) {
    	super(null, nome, login, senha, null);
        this.npf = npf;
        this.numCasa = numCasa;
        this.rua = rua;
        this.bairro = bairro;
    }

}
