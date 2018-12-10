package br.com.febatis.kotlinrealmexemplo.data.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class Usuario : RealmObject() {

    @PrimaryKey
    var id = 0

    @Required
    var nome = ""

    @Required
    var email = ""

    @Required
    var senha = ""

}