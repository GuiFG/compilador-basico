package br.com.isilanguage.compiladorapi.controller;

import br.com.isilanguage.compiladorapi.model.Codigo;
import br.com.isilanguage.compiladorapi.model.Resposta;
import br.com.isilanguage.datastructures.Tuple;
import br.com.isilanguage.main.MainClass;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.antlr.v4.runtime.CharStreams;
import java.util.Arrays;

@RestController
@RequestMapping("/compilador")
public class CompiladorController {
    
    @PostMapping
    public Resposta compilar(@RequestBody Codigo codigo)
    {
        try
        {
            String code = codigo.codigo;
        
            Tuple<String, ArrayList<String>> resultado = MainClass.run(CharStreams.fromString(code));

            Resposta resposta = new Resposta(resultado.getE1(), resultado.getE2());

            return resposta;
        }
        catch (Exception ex)
        {
            System.out.println(Arrays.toString(ex.getStackTrace()));
            return new Resposta(ex.getMessage());
        }
    }
}
