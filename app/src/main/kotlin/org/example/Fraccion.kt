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
     * Operador de multiplicación para fracciones
     * Formula: (a/b) * (c/d) = (a * c) / (b * d)
     */
    operator fun times(otra: Fraccion): Fraccion {
        val nuevoNumerador = this._numerador * otra._numerador
        val nuevoDenominador = this._denominador * otra._denominador
        return Fraccion(nuevoNumerador, nuevoDenominador)
    }

    /**
     * Operador de división para fracciones
     * Formula: (a/b) / (c/d) = (a * d) / (b * c)
     */
    operator fun div(otra: Fraccion): Fraccion {
        if (otra._numerador == 0) {
            throw IllegalArgumentException("No se puede dividir por una fracción con numerador cero")
        }
        val nuevoNumerador = this._numerador * otra._denominador
        val nuevoDenominador = this._denominador * otra._numerador
        return Fraccion(nuevoNumerador, nuevoDenominador)
    }

    /**
     * Método privado para simplificar la fracción
     */
    private fun simplificar() {
        val mcd = calcularMCD(kotlin.math.abs(_numerador), kotlin.math.abs(_denominador))
        if (mcd > 1) {
            _numerador /= mcd
            _denominador /= mcd
        }
    }

    /**
     * Calcula el Máximo Común Divisor utilizando el algoritmo de Euclides
     */
    private fun calcularMCD(a: Int, b: Int): Int {
        return if (b == 0) a else calcularMCD(b, a % b)
    }

    /**
     * Método para convertir la fracción a su representación decimal
     */
    fun aDecimal(): Double = _numerador.toDouble() / _denominador.toDouble()

    /**
     * Método para crear una fracción desde un decimal
     * Utiliza una aproximación simple multiplicando por 10000 para obtener la fracción más cercana
     */
    companion object {
        fun desdeDecimal(decimal: Double): Fraccion {
            val denominador = 10000
            val numerador = (decimal * denominador).toInt()
            return Fraccion(numerador, denominador)
        }
    }

    /**
     * Compara dos fracciones
     * Devuelve un valor negativo si la fracción actual es menor, positivo si es mayor y 0 si son iguales
     */
    operator fun compareTo(otra: Fraccion): Int {
        val thisDecimal = this.aDecimal()
        val otherDecimal = otra.aDecimal()
        return when {
            thisDecimal < otherDecimal -> -1
            thisDecimal > otherDecimal -> 1
            else -> 0
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

    /**
     * Verifica si la fracción actual es mayor que otra
     */
    fun esMayor(otra: Fraccion): Boolean {
        return this.compareTo(otra) > 0
    }

    /**
     * Verifica si la fracción actual es menor que otra
     */
    fun esMenor(otra: Fraccion): Boolean {
        return this.compareTo(otra) < 0
    }

    /**
     * Representación de la fracción en forma de cadena
     */
    override fun toString(): String {
        return if (_denominador == 1) {
            _numerador.toString()
        } else {
            "$_numerador/$_denominador"
        }
    }
    
    /**
     * Obtiene el valor como string (alias de toString)
     */
    fun obtenerValor(): String {
        return toString()
    }
    
    /**
     * Muestra la fracción con formato descriptivo
     */
    fun mostrarFraccion(): String {
        return "$_numerador/$_denominador"
    }
}