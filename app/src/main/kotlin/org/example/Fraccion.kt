package org.example

class Fraccion(
    numerador: Int,
    denominador: Int
) {
    private var _numerador: Int = numerador
    private var _denominador: Int = denominador
    
    init {
        if (denominador == 0) throw IllegalArgumentException("El denominador no puede ser cero")
    }
    
    var numerador: Int
        get() = _numerador
        set(value) { _numerador = value }
    
    var denominador: Int
        get() = _denominador
        set(value) {
            if (value == 0) throw IllegalArgumentException("El denominador no puede ser cero")
            _denominador = value
        }
    
    fun obtenerValor(): String {
        return toString()
    }
    
    fun mostrarFraccion(): String {
        return "$_numerador / $_denominador es una fracción"
    }
    
    override fun toString(): String {
        return "$_numerador/$_denominador"
    }
}