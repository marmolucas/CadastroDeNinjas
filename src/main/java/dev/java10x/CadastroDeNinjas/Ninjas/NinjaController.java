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

    // Adicionar ninja (create)
    @PostMapping("/criar")
    //Utiliza o @RequestBody pois as informações serão mandadas no corpo da requisição
    public NinjaDTO criarNinja(@RequestBody NinjaDTO ninja){
        return ninjaService.criarNinja(ninja);
    }

    // Alterar dados dos ninjas (update)
    @PutMapping("/alterar/{id}")
    public NinjaModel alterarNinjaPorId(@PathVariable Long id, @RequestBody NinjaModel ninjaAtualizado){
        return ninjaService.atualizarNinja(id,ninjaAtualizado);
    }

    // Deletar ninja (delete)
    @DeleteMapping("/deletar/{id}")
    public void deletaNinjaPorId(@PathVariable Long id){
        ninjaService.deletaNinja(id);
    }




}
