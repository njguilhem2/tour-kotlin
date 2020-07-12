package com.acme.tour.model

import javax.persistence.*

@Entity
data class Promocao (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val descricao: String,
        val local: String,
        val isAllInclusive: Boolean,
        val qtdDias: Int,
        val preco: Double
){
    constructor(){

    }

    constructor(id: Long) : this()
}
