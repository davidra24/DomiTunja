package dmt.appsolution.co.dmt.services.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Martin on 8/01/2018.
 */
class TipoLugar {
    @SerializedName("0")
    @Expose
    var _0: String? = null
    @SerializedName("idTipo_lugar")
    @Expose
    var idTipoLugar: String? = null
    @SerializedName("1")
    @Expose
    var _1: String? = null
    @SerializedName("tipo_lugar")
    @Expose
    var tipoLugar: String? = null

    constructor(_0: String, idTipoLugar: String, _1: String, tipoLugar: String){
        this._0 = _0
        this.idTipoLugar = idTipoLugar
        this._1 = _1
        this.tipoLugar = tipoLugar
    }
}