package com.acme.tour.repository

import com.acme.tour.model.Promocao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface PromocaoRepository: PagingAndSortingRepository<Promocao, Long>{
    @Query(value = "")
    fun findByPrecoMenorQue(@Param(value ="preco") preco: Double,
                            @Param("dias") quantidadeDias: Int): List<Promocao>
    @Query(value = "UPDATE Promocao p set p.preco= :valor WHERE p.local = :local")
    @Transactional @Modifying
    fun updateByLocal(@Param("value") preco: Double, @Param("local") local: String)
}