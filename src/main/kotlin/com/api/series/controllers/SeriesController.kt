package com.api.series.controllers

import com.api.series.dtos.SerieDto
import com.api.series.models.Series
import com.api.series.repositories.SeriesRepository

import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import com.api.series.response.Response
import java.util.*

@RestController
@RequestMapping("/api/series")
class SeriesController(var seriesRepository: SeriesRepository) {

    @GetMapping
    fun list(): List<Series>{
        return  seriesRepository.findAll().toList()
    }

    @PostMapping
    fun adicionar(@Valid  @RequestBody  serie: Series, result : BindingResult) : ResponseEntity<*>{
        val response: Response = Response()
        var newSerie : Series
        if(result.hasErrors()){
            for(erro in result.allErrors) response.erros.add(erro.defaultMessage!!)

            return ResponseEntity.badRequest().body(response)
        }
        newSerie = seriesRepository.save(serie)
        return ResponseEntity.ok(
            newSerie
        )

    }

    @GetMapping("{id}")
    fun listarSerie(@PathVariable("id") id : Long)
    : ResponseEntity<*>{


        val searchedSerie :  Optional<Series> = seriesRepository.findById(id)
        if(searchedSerie.isEmpty){
            return ResponseEntity.status(404).body("Nenhuma serie encontrada")
        }
        var serieDto : SerieDto = SerieDto(searchedSerie.get().title)
        return  ResponseEntity.status(200).body(serieDto)
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<*>{
        if(seriesRepository.existsById(id)){
             seriesRepository.deleteById(id)
            return ResponseEntity.status(200).body("Deletado com sucesso")
            }
        return ResponseEntity.status(404).body("Nenhuma serie com esse id")

    }


    @PutMapping("{id}")
    fun edit(@PathVariable id: Long, @RequestBody serie: Series) : ResponseEntity<*>{
        if(seriesRepository.existsById(id)){
            val safeSerie = serie.copy(id)
            seriesRepository.save(safeSerie)
            return ResponseEntity.ok(safeSerie)
        }
        return ResponseEntity.status(404).body("Nenhuma serie encontrada")
    }

}