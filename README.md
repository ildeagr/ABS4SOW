## Proyeto de abstracción, síntesis e integración de información para el análisis del flujo de trabajo social
## Objetivo:
Realizar una aplicación movil para crear campañas de marketing en centros comerciales, donde los consumidores realicen actividades en tiendas o localizaciones basadas en juegos.

## Sobre mi participacion y Logros:
Participé en este proyecto junto a otros compañeros durante más de un año. Además conseguí los siguientes logros personales:

1. Aprender conocimiento sobre la programación Java y aplicaciones móviles con Android Studio.
2. Desarrollé la parte del frontend de la aplicación.
3. Me encargué de programar el guardado y compresión de los archivos generados, el código de control de los botones y textos mostrados tanto estáticos como dinámicos a traves de Toast.
5. Participé en realizar las pruebas en un entorno real.

## Funcionamiento del frontend:
En este proyecto se diseñó una aplicacion móvil en la cual se recopilan los datos de todos los sensores y ubicación del dispositivo guardandolos en archivos .txt. Al mismo tiempo la interfaz diseñada, tal como se muestra en la imagen, tiene varias opciones para indicar una localización y una actividad.

![Captura de pantalla 2024-10-15 a las 14 18 30](https://github.com/user-attachments/assets/f3a4c080-3d48-47d6-8f6a-4b814e2380d9)


Para generar la informacion correctamente debemos ir indicando que zona y actividad realizamos e iniciarla con el botón de "Start". Al realizar un cambio de actividad pulsaremos el botón de "Stop" para indicar el final de la actividad iniciada.

Por ejemplo:
Iniciámos el recorrido en una cafetería.
1. Indicamos las opciones a seleccionar: Place: Cafetito; Zona: Restaurants; Activity: Eating. Y también el nombre del archivo en el campo "Name File". En la parte inferior se muestran las opciones seleccionadas antes de iniciar la actividad y habilitar todo.

![Captura de pantalla 2024-10-15 a las 14 17 40](https://github.com/user-attachments/assets/d9b42f75-d0dc-441e-9301-b1ac661278be)


2. Pulsamos "Start" para iniciar. Se habilita la localización de Google y se comienza a recoger datos hasta que finalicemos la actividad.
   
![Captura de pantalla 2024-10-15 a las 14 16 18](https://github.com/user-attachments/assets/e798e706-a6db-4362-ae2d-714b4c0949ec)

3. Al terminar en la cafetería pulsamos "Stop".


Cambiamos de actividad y damos un paseo por los pasillos del centro comercial.
1. Opciones a seleccionar: Place: No place ; Zona: Shops ; Activity: Walking.
2. Pulsamos "Start" para iniciar.
3. Al terminar en la cafetería pulsamos "Stop".

Y así sucesivamente hasta completar un recorrido a través de diferentes sitios y actividades.

## Guardado de datos:
Una vez finalizado el recorrido planificado, pulsámos en el boton "Save". Ahora la aplicación unificará todos los archivos .txt en un archivo compromido más facil de manejar y enviar para su posterior tratamiento.

## Que sucede con la informacion obtenida:
Toda esta informacion se almacena para realizar un tratamiento posterior e intentar conocer, si es posible, un patrón de para ofrecer una experiencia en productos e intereses a los consumidores en centros comerciales, y de esta manera ofertar campañas de marketing a traves de actividades. Este patrón se analiza fuera de la aplicación móvil con otros medios de procesamiento de datos.
