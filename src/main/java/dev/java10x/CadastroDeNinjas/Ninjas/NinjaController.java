package dev.java10x.CadastroDeNinjas.Ninjas;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<NinjaDTO>> mostrarNinjas(){
        List<NinjaDTO>  ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);

    }

    // Mostrar Ninja por id (read)
    // usa interrogação "?" para ser retorno generico, tanto string quanto json etc.
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> mostrarNinjasPorId(@PathVariable Long id){
       NinjaDTO ninja = ninjaService.listarNinjaId(id);

       if (ninja != null){
           return ResponseEntity.ok(ninja);
       }else{
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja não encontrado!");

       }


    }

    // Adicionar ninja (create)
    @PostMapping("/criar")
    //Utiliza o @RequestBody pois as informações serão mandadas no corpo da requisição
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja){

        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja Criado: " + novoNinja.getNome());
    }

    // Alterar dados dos ninjas (update)
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarNinjaPorId(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado){

        if(ninjaService.listarNinjaId(id) != null){
            NinjaDTO ninjaAt = ninjaService.atualizarNinja(id,ninjaAtualizado);
            return ResponseEntity.ok(ninjaAt);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja não encontrado!");
        }


    }

    // Deletar ninja (delete)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletaNinjaPorId(@PathVariable Long id){

        if(ninjaService.listarNinjaId(id) != null){
            ninjaService.deletaNinja(id);
            return ResponseEntity.ok("Ninja Deletado com Sucesso!");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja não encontrado!");
        }

    }




}
