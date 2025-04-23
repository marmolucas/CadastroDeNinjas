package dev.java10x.CadastroDeNinjas.Ninjas;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Mensagem de boas vindas", description = "Essa rota da uma msg de boas vindas")
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
    @Operation(summary = "Lista o ninja por ID", description = "Rota para listar um ninja por ID")

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado")
    })
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
    @Operation(summary = "Cria um novo ninja", description = "Rota cria um novo ninja e insere no banco de dados")

    @ApiResponses(value= {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação do Ninja.")
    })

    //Utiliza o @RequestBody pois as informações serão mandadas no corpo da requisição
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja){

        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja Criado: " + novoNinja.getNome());
    }

    // Alterar dados dos ninjas (update)
    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera o ninja por ID", description = "Rota Altera um ninja por ID")

    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado, não foi possível alterar")
    })
    public ResponseEntity<?> alterarNinjaPorId(@Parameter(description = "Usuário manda o ID no caminhoda requisição") @PathVariable Long id, @Parameter(description = "Usuário manda os dados do novo ninja, no corpo da requisição") @RequestBody NinjaDTO ninjaAtualizado){

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
