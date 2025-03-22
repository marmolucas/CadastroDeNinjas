package dev.java10x.CadastroDeNinjas.Ninjas;


import dev.java10x.CadastroDeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="tb_cadastro_de_ninjas")
@Data //Adiciona Getter e Setters
@NoArgsConstructor
@AllArgsConstructor
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(unique = true)
    private String email;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "idade")
    private int idade;

    //Um ninja só pode atuar em uma única missão.
    @ManyToOne
    @JoinColumn(name = "missoes_id") // Foreing Key ou Chave Estrangeira
    private MissoesModel missoes;


}
