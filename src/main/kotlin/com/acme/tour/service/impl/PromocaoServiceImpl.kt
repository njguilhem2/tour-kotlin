package com.acme.tour.service.impl

import com.acme.tour.model.Promocao
import com.acme.tour.repository.PromocaoRepository
import com.acme.tour.service.PromocaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.util.concurrent.ConcurrentHashMap
import javax.print.attribute.standard.PageRanges

@Component
class PromocaoServiceImpl(val promocaoRepository: PromocaoRepository): PromocaoService {

    override fun create(promocao: Promocao) {
        this.promocaoRepository.save(promocao)
    }

    override fun getById(id: Long): Promocao? {
        return this.promocaoRepository.findById(id).orElseGet(null)
    }
    override fun delete(id: Long) {
        this.promocaoRepository.delete(Promocao(id= id))
    }

    override fun update(id: Long, promocao: Promocao) {
        create(promocao)
    }

    override fun searchByLocal(local: String): List<Promocao> =
       listOf()

    override fun getAll(start: Int, size: Int): List<Promocao> {
        val pages = PageRequest.of(start,size, Sort.by("local").ascending())
        return this.promocaoRepository.findAll(pages).toList()
    }

    override fun getAllBySortedByLocal(): List<Promocao> {
        return this.promocaoRepository.findAll(Sort.by("local").descending()).toList()
    }

}
