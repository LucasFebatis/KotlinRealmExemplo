package br.com.febatis.kotlinrealmexemplo.data.realm

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration



object RealmUtils {

    /**
     * Inicia o Realm na aplicação, deve ser executado apenas uma vez
     */
    fun initRealm(context: Context) {
        Realm.init(context)
    }

    /**
     * Obtem uma instancia do Realm para poder ser feita transações no banco de dados local
     */
    fun getInstance() : Realm {

        val realmConfig = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("myRealmFile")
                .build()

        Realm.setDefaultConfiguration(realmConfig);

        return Realm.getDefaultInstance()
    }

}