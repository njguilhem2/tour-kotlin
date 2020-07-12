package com.acme.tour.controller

import com.acme.tour.exception.PromocaoNotFoundException
import com.acme.tour.model.Promocao
import com.acme.tour.model.RespostaJson
import com.acme.tour.service.PromocaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import javax.xml.crypto.Data

@RestController
@RequestMapping("/promocoes")
class PromocaoController {
    @Autowired
    lateinit var promocoesService: PromocaoService

    @GetMapping("/{id}")
    fun getByIdPromocao(@PathVariable id:Long): ResponseEntity<Promocao?> {
        var promocao = this.promocoesService.getById(id) ?:
                throw PromocaoNotFoundException("promocao ${id} não localizado")
        return ResponseEntity(promocao,HttpStatus.OK)
    }
    @PostMapping()
    fun create(@RequestBody promocao: Promocao): ResponseEntity<RespostaJson>{
        this.promocoesService.create(promocao)
        val map = mapOf("message" to "ok")
        val repostaJson = RespostaJson("ok", Date())
        return ResponseEntity(repostaJson,HttpStatus.CREATED)
    }
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit>{
        var status = HttpStatus.NOT_FOUND
        if(this.promocoesService.delete(id) != null){
            status = HttpStatus.NO_CONTENT
            this.promocoesService.delete(id)
        }
        return ResponseEntity(Unit,status)
    }
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody promocao:Promocao): ResponseEntity<Unit>{
        var status = HttpStatus.NOT_FOUND
        if(this.promocoesService.getById(id) != null){
            status = HttpStatus.OK
            this.promocoesService.update(id,promocao)
        }
        return ResponseEntity(Unit,status)
    }
    @GetMapping()
    fun getAll(@RequestParam(required = false, defaultValue = "0") start: Int,
               @RequestParam(required = false, defaultValue = "3") size: Int):
    ResponseEntity<List<Promocao>>{
        var listaPromocoes = this.promocoesService.getAll(start,size)
        var status = if(listaPromocoes.size == 0) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(listaPromocoes, status)
    }
    @GetMapping("/count")
    fun ordenacao() = this.promocoesService.getAllBySortedByLocal()
}