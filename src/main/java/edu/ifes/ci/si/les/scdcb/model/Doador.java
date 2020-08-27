package edu.ifes.ci.si.les.scdcb.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Entity
@Table(name="doadores")
@Data
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name="cdUsuario")
public class Doador extends Usuario {

    private static final long serialVersionUID = 1L;
    
    @Column(length = 50)
    @NotBlank(message = "CPF do Doador deve ser preenchido")
    @Size(min = 2, max = 50, message = "CPF do Doador deve ter entre 2 e 50 letras")
    @Pattern(regexp="\\d{3}.\\d{3}.\\d{3}-\\d{2}", message = "CPF do Doador deve seguir o padrão NNN.NNN.NNN-NN")
    private String cpf;
    
    @NotNull(message = "Nascimento do Doador deve ser preenchido")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dtNascimento;
    
    @Column(length = 50)
    @NotBlank(message = "Rua do Doador deve ser preenchido")
    @Size(min = 2, max = 50, message = "Rua do Doador deve ter entre 2 e 50 letras")
    private String rua;

    @Min(value = 0L, message = "Número da Casa do Doador deve ser preenchido com um valor inteiro")
    private Integer numCasa;

    @NotNull(message = "Bairro do Doador deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "cdBairro")
    private Bairro bairro;

    @Builder
    public Doador(final String cpf, final Date dtNascimento, final Integer numCasa, final String rua,
            final Bairro bairro, final String nome, final String login, final String senha) {
    	super(null, nome, login, senha, null);
    	this.cpf = cpf;
    	this.dtNascimento = dtNascimento;
    	this.rua = rua;
    	this.numCasa = numCasa;
    	this.bairro = bairro;
    }

}
