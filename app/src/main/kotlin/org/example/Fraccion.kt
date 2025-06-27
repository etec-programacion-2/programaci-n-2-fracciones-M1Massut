package org.example

class Fraccion(
    numerador: Int,
    denominador: Int
) {
    private var _numerador: Int = numerador
    private var _denominador: Int = denominador
    
    init {
        if (denominador == 0) throw IllegalArgumentException("El denominador no puede ser cero")
        
        // Normalizar el signo (mover el signo negativo al numerador)
        if (_denominador < 0) {
            _numerador = -_numerador
            _denominador = -_denominador
        }
        
        // Simplificar la fracción al crearla
        simplificar()
    }
    
    var numerador: Int
        get() = _numerador
        set(value) { 
            _numerador = value
            simplificar()
        }
    
    var denominador: Int
        get() = _denominador
        set(value) {
            if (value == 0) throw IllegalArgumentException("El denominador no puede ser cero")
            _denominador = value
            // Normalizar el signo si es necesario
            if (_denominador < 0) {
                _numerador = -_numerador
                _denominador = -_denominador
            }
            simplificar()
        }
    
    /**
     * Operador de suma para fracciones
     * Formula: (a/b) + (c/d) = (a*d + b*c)/(b*d)
     */
    operator fun plus(otra: Fraccion): Fraccion {
        val nuevoNumerador = this._numerador * otra._denominador + this._denominador * otra._numerador
        val nuevoDenominador = this._denominador * otra._denominador
        return Fraccion(nuevoNumerador, nuevoDenominador)
    }
    
    /**
     * Operador de resta para fracciones
     * Formula: (a/b) - (c/d) = (a*d - b*c)/(b*d)
     */
    operator fun minus(otra: Fraccion): Fraccion {
        val nuevoNumerador = this._numerador * otra._denominador - this._denominador * otra._numerador
        val nuevoDenominador = this._denominador * otra._denominador
        return Fraccion(nuevoNumerador, nuevoDenominador)
    }
    
    /**
     * Método privado para simplificar la fracción usando el MCD
     */
    private fun simplificar() {
        val mcd = calcularMCD(kotlin.math.abs(_numerador), kotlin.math.abs(_denominador))
        if (mcd > 1) {
            _numerador /= mcd
            _denominador /= mcd
        }
    }
    
    /**
     * Calcula el Máximo Común Divisor usando el algoritmo de Euclides
     */
    private fun calcularMCD(a: Int, b: Int): Int {
        return if (b == 0) a else calcularMCD(b, a % b)
    }
    
    fun obtenerValor(): String {
        return toString()
    }
    
    fun mostrarFraccion(): String {
        return "$_numerador / $_denominador es una fracción"
    }
    
    /**
     * Convierte la fracción a decimal
     */
    fun toDecimal(): Double = _numerador.toDouble() / _denominador.toDouble()
    
    override fun toString(): String {
        return if (_denominador == 1) {
            _numerador.toString()
        } else {
            "$_numerador/$_denominador"
        }
    }
    
    /**
     * Verifica si dos fracciones son iguales
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Fraccion) return false
        return _numerador == other._numerador && _denominador == other._denominador
    }
    
    override fun hashCode(): Int {
        return 31 * _numerador + _denominador
    }
}