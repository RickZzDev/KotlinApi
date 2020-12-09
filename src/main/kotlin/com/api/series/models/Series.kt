package com.api.series.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size
import kotlin.math.min

@Entity
data class Series(

    @Id
    @GeneratedValue
    val id: Long = 0L,

//    @NotEmpty(message = )
    @field: NotEmpty(message = "O campo nao deve ser vazio")
    @field: NotBlank
    val title : String,

    @NotEmpty(message = "O campo description nao deve ser nulo")
    @Size(min = 5, max = 85,message = "O campo deve conter entre 5  e 85 caracteres")
    val sinopse : String = ""
)