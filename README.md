Tarea2LP
========
Falta 
- Completar HabilityT1.java, HabilityT2.java, BloqueComodin.java 

En Main.java
- Presionando ENTER el programa inicia.
- Pide por pantalla cuántos bloques romper de cada color para finalizar.
- For para llenar tablero (arreglo bidimensional de objetos de tipo Bloque)
- Random para que se vayan insertando bloques de color o comodines al azar.
- 95% Bloques de Color, 5% Bloques Comodín.
- Realiza intercambio de bloques y guarda el tablero nuevo en un arreglo temporal
- Verifica que los 3 bloques aledaños sean iguales y destruye (cambia el tipo de bloque por una X)

- Comodines están incluídos
  - Si alguno de los bloques intercambiados es comodín, ve que los aledaños no lo sean pero sean del mismo color.
  - Si los bloques intercambiados no son comodines, ve que alguno de los aledaños lo sea, o que sea del mismo color que el intercambiado para ejecutar habilidad.

- Por cada jugada, imprime el resultado y los bloques restantes para finalizar el juego
- Llena los espacios libres (bloques destruídos) con bloques random
- Por jugada almacena los cambios en el tablero definitivo, ya que si no se realizaron jugadas, no tiene sentido almacenar el intercambio de bloque en el tablero original. 


LO QUE FALTA:

- Falta que compruebe si quedan jugadas, sino, que remezcle. Quizás en internet haya un algoritmo que haga esa gracia.
- Dejé comentada la función Habilidad en BloqueComodin.java, la hice para tener la carcasa de como debería quedar.
- Consideré que la habilidad de los comodines se ejecuta sólo cuando hay dos bloques color iguales + un comodin, independiente de su posicion.
- Falta ejecutar las habilidades, está señalado en el código dónde debe hacerse esto.
- Falta que, al ejecutar la habilidad que corresponda, se sumen los bloques eliminados a los contadores


Para compilar archivo ANT (build.xml)
ant compile jar run