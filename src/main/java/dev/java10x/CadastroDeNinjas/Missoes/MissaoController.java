package dev.java10x.CadastroDeNinjas.Missoes;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missoes")
public class MissaoController {

    @PostMapping("/criar")
    public String criarMissao(){
        return "Missao Criada com sucesso";
    }

    @PutMapping("/alterar")
    public String alterarMissao(){
        return "Missao Alterada com sucesso";
    }

    @GetMapping("/listar")
    public String listarMissoes(){
        return "As missões!";
    }

    @DeleteMapping("/deletaId")
    public String deletaMissaoId(){
        return "Missao deletada com sucesso";
    }



}
