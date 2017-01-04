# picoyplaca
Aplicación en java que verifica si las placas obtenidas desde un archivo de texto con placas de vehículos pueden o no circular en Quito de acuerdo a una fecha y hora dadas.

## Requisitos
- Windows 7 o superior
- Tener instalada una versión igual o superior del jre 1.7
- Consola de comandos (cmd), PowerShell (ps)

## Pasos para ejecución
### Paso 1
Descarga el proyecto
`git clone https://github.com/eptene/picoyplaca.git`

### Paso 2 (Ya no se requiere en esta version)
Modifica el archivo PlacasConsole (contiene el void main que se ejecutará primero) con las condiciones para tu entorno.
`cd picoyplaca\src\com\placa\view\PlacasConsole.java`

Ejemplo:
Ruta al archivo `String path = "C:\\ruta\\al\\archivo\\de\\placas.txt";`
Horario de restriccion inicio mañana `horaTemp1.setHours(7);`
Horario de restriccion final mañana `horaTemp1.setMinutes(30);` ... etc.

### Paso 3 (Modificado para esta version)
Entra al siguiente directorio
`cd picoyplaca\src`

Compila el proyecto mediante el archivo main:
`javac -Xlint com\placa\view\PlacasConsole.java`

Ejecuta el archivo compilado con los argumentos (ruta y nombre del fichero, fecha en formato dd-mm-yyyy,
hora en formato hh:mm) y listo!:
`java com.placa.view.PlacasConsole C:\\ruta\\al\\archivo\\de\\placas.txt 12-01-2017 12:58`




