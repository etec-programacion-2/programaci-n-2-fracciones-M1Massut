package org.example

import java.util.Scanner
import java.util.InputMismatchException

fun mostrarMenu() {
    println("=== CALCULADORA DE FRACCIONES ===")
    println("1. Sumar fracciones")
    println("2. Restar fracciones")
    println("3. Multiplicar fracciones")
    println("4. Dividir fracciones")
    println("5. Comparar fracciones")
    println("6. Convertir fracción a decimal")
    println("7. Crear fracción desde decimal")
    println("8. Ejemplos predefinidos")
    println("0. Salir")
    print("Ingrese su opción: ")
}

fun main() {
fun leerFraccion(scanner: Scanner, mensaje: String): Fraccion? {
    return try {
        println(mensaje)
        print("Numerador: ")
        val numerador = scanner.nextInt()
        print("Denominador: ")
        val denominador = scanner.nextInt()
        Fraccion(numerador, denominador)
    } catch (e: InputMismatchException) {
        println("Error: Debe ingresar números enteros válidos.")
        scanner.nextLine() // Limpiar buffer
        null
    } catch (e: IllegalArgumentException) {
        println("Error: ${e.message}")
        null
    }
}

fun realizarSuma(scanner: Scanner) {
    println("\n--- SUMA DE FRACCIONES ---")
    val f1 = leerFraccion(scanner, "Ingrese la primera fracción:")
    val f2 = leerFraccion(scanner, "Ingrese la segunda fracción:")
    
    if (f1 != null && f2 != null) {
        val resultado = f1 + f2
        println("Resultado: $f1 + $f2 = $resultado")
    } else {
        println("No se pudo realizar la operación debido a errores en la entrada.")
    }
}

fun realizarResta(scanner: Scanner) {
    println("\n--- RESTA DE FRACCIONES ---")
    val f1 = leerFraccion(scanner, "Ingrese la primera fracción:")
    val f2 = leerFraccion(scanner, "Ingrese la segunda fracción:")
    
    if (f1 != null && f2 != null) {
        val resultado = f1 - f2
        println("Resultado: $f1 - $f2 = $resultado")
    } else {
        println("No se pudo realizar la operación debido a errores en la entrada.")
    }
}

fun realizarMultiplicacion(scanner: Scanner) {
    println("\n--- MULTIPLICACIÓN DE FRACCIONES ---")
    val f1 = leerFraccion(scanner, "Ingrese la primera fracción:")
    val f2 = leerFraccion(scanner, "Ingrese la segunda fracción:")
    
    if (f1 != null && f2 != null) {
        val resultado = f1 * f2
        println("Resultado: $f1 * $f2 = $resultado")
    } else {
        println("No se pudo realizar la operación debido a errores en la entrada.")
    }
}

fun realizarDivision(scanner: Scanner) {
    println("\n--- DIVISIÓN DE FRACCIONES ---")
    val f1 = leerFraccion(scanner, "Ingrese la primera fracción:")
    val f2 = leerFraccion(scanner, "Ingrese la segunda fracción:")
    
    if (f1 != null && f2 != null) {
        try {
            val resultado = f1 / f2
            println("Resultado: $f1 / $f2 = $resultado")
        } catch (e: IllegalArgumentException) {
            println("Error: ${e.message}")
        }
    } else {
        println("No se pudo realizar la operación debido a errores en la entrada.")
    }
}

fun realizarComparacion(scanner: Scanner) {
    println("\n--- COMPARACIÓN DE FRACCIONES ---")
    val f1 = leerFraccion(scanner, "Ingrese la primera fracción:")
    val f2 = leerFraccion(scanner, "Ingrese la segunda fracción:")
    
    if (f1 != null && f2 != null) {
        println("Comparación entre $f1 y $f2:")
        when {
            f1.esMayor(f2) -> println("$f1 es mayor que $f2")
            f1.esMenor(f2) -> println("$f1 es menor que $f2")
            else -> println("$f1 es igual a $f2")
        }
    } else {
        println("No se pudo realizar la operación debido a errores en la entrada.")
    }
}

fun convertirADecimal(scanner: Scanner) {
    println("\n--- CONVERSIÓN A DECIMAL ---")
    val fraccion = leerFraccion(scanner, "Ingrese la fracción a convertir:")
    
    if (fraccion != null) {
        val decimal = fraccion.aDecimal()
        println("$fraccion en decimal = $decimal")
    } else {
        println("No se pudo realizar la conversión debido a errores en la entrada.")
    }
}

fun crearDesdeDecimal(scanner: Scanner) {
    println("\n--- CREAR FRACCIÓN DESDE DECIMAL ---")
    try {
        print("Ingrese el número decimal: ")
        val decimal = scanner.nextDouble()
        val fraccion = Fraccion.desdeDecimal(decimal)
        println("$decimal como fracción = $fraccion")
        println("Verificación: $fraccion = ${fraccion.aDecimal()}")
    } catch (e: InputMismatchException) {
        println("Error: Debe ingresar un número decimal válido.")
        scanner.nextLine() // Limpiar buffer
    }
}

fun mostrarEjemplos() {
    println("\n=== EJEMPLOS PREDEFINIDOS ===")
    
    val f1 = Fraccion(1, 2)  // 1/2
    val f2 = Fraccion(1, 3)  // 1/3
    
    println("Fracción 1: $f1")
    println("Fracción 2: $f2")
    println("Suma: $f1 + $f2 = ${f1 + f2}")
    println("Resta: $f1 - $f2 = ${f1 - f2}")
    println("Multiplicación: $f1 * $f2 = ${f1 * f2}")
    println("División: $f1 / $f2 = ${f1 / f2}")
    println("¿$f1 > $f2? ${f1.esMayor(f2)}")
    println("$f1 en decimal: ${f1.aDecimal()}")
    
    println("\n--- Más ejemplos ---")
    val f3 = Fraccion(3, 4)  // 3/4
    val f4 = Fraccion(2, 8)  // 2/8 = 1/4
    
    println("Fracción 3: $f3")
    println("Fracción 4: $f4 (simplificada automáticamente)")
    println("Suma: $f3 + $f4 = ${f3 + f4}")
    println("¿$f3 == $f4? ${f3.equals(f4)}")
    
    // Ejemplo con decimal
    val decimal = 0.75
    val fraccionDesdeDecimal = Fraccion.desdeDecimal(decimal)
    println("\nDesde decimal: $decimal = $fraccionDesdeDecimal")
}

    val scanner = Scanner(System.`in`)
    var opcion: Int
    
    do {
        try {
            mostrarMenu()
            opcion = scanner.nextInt()
            
            when (opcion) {
                1 -> realizarSuma(scanner)
                2 -> realizarResta(scanner)
                3 -> realizarMultiplicacion(scanner)
                4 -> realizarDivision(scanner)
                5 -> realizarComparacion(scanner)
                6 -> convertirADecimal(scanner)
                7 -> crearDesdeDecimal(scanner)
                8 -> mostrarEjemplos()
                0 -> println("¡Hasta luego!")
                else -> println("Opción inválida. Intente de nuevo.")
            }
            
            if (opcion != 0) {
                println("\nPresione Enter para continuar...")
                scanner.nextLine() // Limpiar buffer
                scanner.nextLine() // Esperar Enter
            }
        } catch (e: InputMismatchException) {
            println("Error: Debe ingresar un número válido para la opción.")
            scanner.nextLine() // Limpiar buffer
            opcion = -1 // Continuar el bucle
        }
    } while (opcion != 0)
    
    scanner.close()
}