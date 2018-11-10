// To parse the JSON, install Klaxon and do:
//
//   val welcome = Welcome.fromJson(jsonString)

package ro.bartis.ghiduriPrimAjutor.DetailsModule.Entity

import com.beust.klaxon.Klaxon


private val klaxon = Klaxon()

data class Guide (
        val texts: List<String>,
        val sections: List<Section>,
        val video: String
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<Guide>(json)
    }
}

data class Section (
        val title: String,
        val steps: List<Step>
)

data class Step (
        val title: String
)