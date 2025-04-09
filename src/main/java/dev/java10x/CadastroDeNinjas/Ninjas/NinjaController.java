package dev.java10x.CadastroDeNinjas.Ninjas;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }



    @GetMapping("/boasvindas")
    public String boasVindas(){
       return "Essa é a minha API";
    }

    // Adicionar ninja (create)
    @PostMapping("/criar")
    public String criarNinja(){
        return "Ninja criado";
    }
    // Mostrar todos os ninjas (read)
    @GetMapping("/listar")
    public List<NinjaModel> mostrarNinjas(){
        return ninjaService.listarNinjas();
    }

    // Mostrar Ninja por id (read)
    @GetMapping("/listar/{id}")
    public NinjaModel mostrarNinjasPorId(@PathVariable Long id){
        return ninjaService.listarNinjaId(id);
    }

    // Alterar dados dos ninjas (update)
    @PutMapping("/alterarId")
    public String alterarNinjaPorId(){
        return "Alterado!";
    }

    // Deletar ninja (delete)
    @DeleteMapping("/deletaId")
    public String deletaNinjaPorId(){
        return "Deletado!";
    }

}
