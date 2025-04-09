package dev.java10x.CadastroDeNinjas.Ninjas;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {


    private NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }

    //Listar todos os ninjas
    public List<NinjaModel> listarNinjas(){
        return ninjaRepository.findAll();

    }

    //Listar ninjas por ID
    public NinjaModel listarNinjaId(Long id){
        // É optional, pois o Ninja pode existir ou não
        Optional<NinjaModel> ninjasPorId = ninjaRepository.findById(id);
        // Abaixo ou ele vai listar o Ninja ou vai retornar nulo
        return ninjasPorId.orElse(null);
    }







}
