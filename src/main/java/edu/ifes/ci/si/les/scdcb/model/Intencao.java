package edu.ifes.ci.si.les.scdcb.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;
import edu.ifes.ci.si.les.scdcb.model.enums.StatusIntencao;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.RandomStringUtils;

@Entity
@Table(name="intencoes")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"cdIntencao"})

public class Intencao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cdIntencao;

    @Column(nullable = true, length = 8, unique = true, updatable = false)
    private String codigo;

    @Column(updatable = false)
    @NotNull(message = "Data da Intenção de Doar deve ser preenchida")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dtIntencao;

    @NotNull(message = "Quantidade de Cestas da Intenção de Doar deve ser preenchido")
    @Min(value = 0L, message = "Quantidade de Cestas da Intenção de Doar deve ser maior que zero")
    private Integer qtdCestas;

    @NotNull(message = "Doador deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "cdDoador")
    private Doador doador;
    
    @NotNull(message = "Tipo de Cesta deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "tpCesta")
    private TipoDeCesta tpCesta;
    
    @NotNull(message = "Status da Intenção de Doar deve ser preenchido")
    @Min(value = 0L, message = "Status da Intenção de Doar deve ser preenchido com um valor inteiro")
    private Integer status;

    @NotNull(message = "Instituição deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "cdInstituicao")
    private Instituicao instituicao;

    @Builder
    public Intencao(LocalDate dtIntencao, Integer qtdCestas, Doador doador, TipoDeCesta tpCesta, Instituicao instituicao, StatusIntencao status) {
    	super();
        this.codigo = gerarCodigo();
        this.dtIntencao = dtIntencao;
        this.qtdCestas = qtdCestas;
        this.doador = doador;
        this.tpCesta = tpCesta;
        this.instituicao = instituicao;
        this.status = (status == null) ? null : status.getCod();
    }
    
    public StatusIntencao getStatus() {
		return StatusIntencao.toEnum(status);
	}

	public void setStatus(StatusIntencao status) {
		this.status = status.getCod();
	}

    public String gerarCodigo() {
        return (this.codigo == null) ? RandomStringUtils.randomAlphanumeric(8) : null;
    }

}
