<div class="image-div" style="position: relative; width: 60%;margin-left:20%;margin-bottom:1.2em;">
![](https://confluence.atsistemas.com/download/attachments/155070739/mvvmKoin%20%281%29.png?version=1&modificationDate=1601551905000&api=v2)
</div>

Las diferentes tendencias en el desarrollo de aplicaciones móviles, nos brindan cada cierto tiempo nuevas funcionalidades, nuevos patrones arquitectónicos y nuevas técnicas cada vez más eficientes para el desarrollo e implementación de tareas cotidianas en el desarrollo software.

Sabemos que alguna de esas tendencias llegan para quedarse, como fue el caso de los principios S.O.L.I.D. de la mano de Robert C Martin a principios de la década de los 2000.

<div class="image-div" style="position: relative; width: 60%;margin-left:20%;margin-bottom:1.2em;">
![](https://devx.blog/uploads/programming/intro.png)
</div>

En este artículo, vamos a girar entorno a uno de estos principios, sin intención de desmerecer al resto de ellos. En particular, hablamos del principio de **Inversión de dependencias**.

<br/>
<h3>Principio de Inversión de Dependencias</h3>

Si bien es cierto que este principio nos indica que debemos depender de abstracciones, no de clases concretas, sabemos que el objetivo del mismo es evitar el acoplamiento entre clases, consiguiendo módulos de código totalmente independientes.

Diseñar la arquitectura de nuestras aplicaciones, así como organizar los posteriores desarrollos, teniendo presente este principio, nos permitirá optimizar los recursos técnicos de los que disponemos, disminuyendo el esfuerzo el trabajo de proyecto entre desarrolladores y equipos de desarrollo.

<br/>
<h3>Inyección de Dependencias - Koin </h3>
<div class="image-div" style="position: relative; width: 60%;margin-left:20%;margin-bottom:1.2em;">
![](https://insert-koin.io/assets/images/koin_2.0.jpg)
</div>
Uno de las primeras decisiones a las que debemos enfrentarnos a la hora de plantear la arquitectura de nuestra nueva aplicación, es la de usar Inyección de dependencias para respetar la separación entre los diferentes módulos de nuestra aplicación y garantizar el desacoplamiento.

Así mismo, una vez tomada la decisión de aplicar inyección de dependencias en nuestro proyecto, nos encontramos con una interesante variedad de herramientas que nos permiten alcanzar este objetivo. En este artículo, nos vamos a centrar en una de las herramientas de Inyección de Dependencias más utilizadas para proyectos Android, desarrollados principalmente en Kotlin; **[Koin](https://insert-koin.io/)**

<h3>MvvM</h3>
En el caso de ejemplo que nos ocupa, hemos implementado uno de los patrones de diseño más utilizado para el desarrollo de aplicaciones móviles: MVVM. 

No vamos a entrar en el detalle de las características y propiedades de este patrón de diseño, pero nos interesa recordar sus diferentes componentes así como las reglas de comunicación entre ellos:

El patrón **MVVM** a tiende a las siglas:
<ul>
<li> <strong>M</strong>odel: modelo de datos definido para representar el modelo de negocio
</li>
<li>
<strong>V</strong>iew: representa la vista que nos ofrecerá la interface de comunicación con el usuario, mostrando la información de nuestro sistema y gestionando la interacción del usuario
</li>
<li>
<strong>V</strong>iew<strong>M</strong>odel: se trata de una clase que nos permite crear una representación del modelo de datos.
</li>
</ul>

<div class="image-div" style="position: relative; width: 60%;margin-left:20%;margin-bottom:1.2em;">
![](https://confluence.atsistemas.com/download/attachments/155070739/mvvm.png?version=1&modificationDate=1601534586000&api=v2)
</div>

Como vemos en el diagrama de componentes, la vista siempre tendrá acceso al ViewModel, y el ViewModel al Modelo, por lo tanto, la vista podrá conocer también los detalles del Modelo, pero el ViewModel nunca deberá tener dependencias sobre la Vista.

En el proyecto implementado, hemos llevado la separación de componentes al extremo, definiendo un módulo por cada uno de los elementos de esta arquitectura en particular. Esta separación, nos va a permitir garantizar la separación de responsabilidades y eliminar el acoplamiento entre componentes. No obstante, necesitamos satisfacer la comunicación entre los diferentes componentes, para ello, vamos a utilizar las herramientas que nos provee **Koin**.

La foto inicial de nuestro proyecto debería ser la siguiente:
<div class="image-div" style="position: relative; width: 60%;margin-left:20%;margin-bottom:1.2em;">
![](https://confluence.atsistemas.com/download/thumbnails/155070739/Screenshot%202020-09-30%20at%2016.25.48.png?version=1&modificationDate=1601551639000&api=v2)
</div>

Una vez definidos los diferentes módulos, debemos aplicar las reglas de dependencias introducidas como parte del patrón MvvM. Para ellos, declararemos en el archivo de configuración de gradle del módulo de vistas la dependencia sobre el módulo de viewmodels:

<div class="image-div" style="position: relative; width: 60%;margin-left:20%;margin-bottom:1.2em;">
![](https://confluence.atsistemas.com/download/attachments/155070739/image2020-10-1_8-46-23.png?version=1&modificationDate=1601534783000&api=v2)
</div>

A su vez, desde el módulo de ViewModels, deberemos definir la dependencia sobre el modelo:

<div class="image-div" style="position: relative; width: 60%;margin-left:20%;margin-bottom:1.2em;">
![](https://confluence.atsistemas.com/download/attachments/155070739/image2020-10-1_8-49-8.png?version=1&modificationDate=1601534948000&api=v2)
</div>

<h5>Integración de librerías </h5>
Tras la definición de los diferentes módulos, vamos a integrar las librerias de Koin en todos archivos de gradle de módulo:
<ul>
<li>
build.gradle (Module: Model), build.gralde (Module: Views), build.gradle (Module: ViewModels):
</li>
</ul>
```
    //DI Koin 
    // Koin AndroidX Scope feature
    implementation "org.koin:koin-androidx-scope:2.1.6" 
    // Koin AndroidX ViewModel feature 
    implementation "org.koin:koin-androidx-viewmodel:2.1.6"
    // Koin AndroidX Fragment Factory
    implementation "org.koin:koin-androidx-fragment:2.1.6" 
```
<h5>Definición de Módulos Koin</h5>
Tras esto, vamos a definir una carpeta por módulo con las siglas DI, siguiendo el estándar para referirnos a la inyección de dependencias:

Dentro de cada una de las cuales, vamos a definir el módulo correspondiente a cada una de ellas:

<table style="width:100%">
  <tr>
    <th/>
    <th>Model</th>
    <th>View</th>
    <th>ViewModel</th>
  </tr>
  <tr>
    <th style="text-align:center">Estructura<br/>de<br/>directorios</th>
    <td><div>
![](https://confluence.atsistemas.com/download/thumbnails/155070739/image2020-10-1_8-54-35.png?version=1&modificationDate=1601535275000&api=v2)
</div></td>
    <td><div>
![](https://confluence.atsistemas.com/download/thumbnails/155070739/Screenshot%202020-10-01%20at%2013.28.42.png?version=1&modificationDate=1601551740000&api=v2)
</div></td>
    <td><div>
![](https://confluence.atsistemas.com/download/thumbnails/155070739/Screenshot%202020-10-01%20at%2013.29.28.png?version=1&modificationDate=1601551782000&api=v2)
</div></td>
  </tr>
  <tr>
    <th style="text-align:center">Definición<br/>de<br/>módulos</th>
    <td><div>
![](https://confluence.atsistemas.com/download/thumbnails/155070739/image2020-10-1_9-2-37.png?version=1&modificationDate=1601535757000&api=v2)
</div></td>
    <td>
<div>
![](https://confluence.atsistemas.com/download/thumbnails/155070739/image2020-10-1_9-3-15.png?version=1&modificationDate=1601535795000&api=v2)
</div></td>
<td>
<div>
![](https://confluence.atsistemas.com/download/thumbnails/155070739/image2020-10-1_9-3-40.png?version=1&modificationDate=1601535820000&api=v2)
</div>
</td>
  </tr>
</table>

Un módulo Koin es básicamente un contenedor donde podemos realizar la definición de nuestros objetos, que harán las veces de instancias o proveedores de instancias.

<h5>Iniciación de Koin</h5>
Una vez definidos los diferentes módulos, vamos a definir nuestra propia clase application. Para ello, dentro del módulo View, creamos una nueva clase App extendiendo de la clase nativa Application:

<div class="image-div" style="position: relative; width: 60%;margin-left:20%;margin-bottom:1.2em;">
![](https://confluence.atsistemas.com/download/attachments/155070739/image2020-10-1_9-0-42.png?version=1&modificationDate=1601535642000&api=v2)
</div>

Cuando hayamos definido nuestra clase application propia, debemos incluirla en el manifest de nuestra aplicación:

<div class="image-div" style="position: relative; width: 60%;margin-left:20%;margin-bottom:1.2em;">
![](https://confluence.atsistemas.com/download/attachments/155070739/image2020-10-1_8-59-20.png?version=1&modificationDate=1601535561000&api=v2)
</div>

Este paso es necesario para integrar Koin en nuestra aplicación. 


Siguiendo la documentación oficial del software de inyección de dependencias, iniciaremos la librería desde nuestra clase App, donde indicaremos así mismo los diferentes módulos definidos:
<div class="image-div" style="position: relative; width: 60%;margin-left:20%;margin-bottom:1.2em;">
![](https://confluence.atsistemas.com/download/attachments/155070739/image2020-10-1_9-0-24.png?version=1&modificationDate=1601535624000&api=v2)
</div>

Con todos los módulos definidos y vinculados, podemos pasar a la definición de nuestras primeras vistas!

Vamos a crear una aplicación con el extendido flujo: Splash → Home. En el mismo, nos encontraremos dos actividades con sus respectivos ViewModels, y dos fragmentos, utilizados por la HomeActivitiy, con sus respectivos ViewModels.

<h5>Inyección de ViewModels</h5>
Siguiendo las guías de desarrollo de Android, sabemos que nuestras actividades extenderán de AppCompatActivity, y nuestros viewmodels de la clase ViewModel, convirtiendoles así en elementos propietarios de ciclo de vida propio.

Llegó el momento de inyectar nuestros ViewModels en nuestras actividades. Podremos hacerlo en la propia definición de la propiedad gracias a Koin:

<div class="image-div" style="position: relative; width: 60%;margin-left:20%;margin-bottom:1.2em;">
![](https://confluence.atsistemas.com/download/attachments/155070739/image2020-10-1_9-8-1.png?version=1&modificationDate=1601536081000&api=v2)
</div>

Koin nos ofrece el tipo de notación especial "**viewModel**" para la definición de este tipo de componentes. 

Internamente, el software de inyección de dependencias se encargará de instanciar el viewmodel correspondiente y asociarlo al ciclo de vida de la actividad / fragmento correspondiente


<h5>Inyección de ViewModels Compartidos</h5>

Queda pendiente no obstante, resolver un caso de uso particular, en el cual dos o más vistas comparten el mismo ViewModel. Para lo cual, necesitamos obtener la misma instancia. Para este escenario, Koin nos ofrece una notación específica: "sharedViewModel". Utilizando esta notación, podremos obtener la misma instancia de un viewmodel asociado al ciclo de vida de otro elemento, pudiendo utilizar este recurso para compartir datos entre vistas, por ejemplo en casos clásicos de vistas Maestro-Detalle.

Para implementar un ejemplo de esta funcionalidad, hemos decidido implementar la vista Maestro-Detalle de dos formas distintas:
<ul>
<li><strong>Escenario 1:</strong> Dos fragmentos distintos (Maestro - Detalle), solicitan instancias distintas de un viewmodel. Gestionamos la comunicación entre fragmentos a través de una variable singleton inyectada al viewmodel.</li>
<li><strong>Escenario 2:</strong>
Dos fragmentos distintos (Maestro - Detalle), solicitan la misma instancia de un viewmodel. Gestionamos la comunicación entre fragmentos a través del viewmodel compartido
</li>
</ul>

<div class="image-div" style="position: relative; width: 100%;margin-bottom:1.2em;">
![](/content/images/2020/10/Screenshot-2020-10-15-at-15-31-19.png)
</div>

<h5>Inyección de dependencias</h5>

Por último, sabemos que a lo largo de los diferentes desarrollos de nuestra aplicación necesitaremos inyectar no solo viewmodels si no otras clases o interfaces, bien de instancias concretas de elementos compartidos, o bien satisfaciendo dependencias sobre casos de uso o repositorios. En estos casos, siempre debemos recordar el principio de inversión de dependencias, y depender de abstracciones, no de clases concretas.

En el siguiente ejemplo vemos como podríamos resolver esta inyección de dependencias, siguiendo la inyección a través del constructor como recomiendan las buenas prácticas para facilitar la implementación de metodologías de testing en nuestros proyectos: 

<table>
<tr>
   <th>Declaracción de la dependencia</th>
   <th>Provider de la instancia/interface</th>
   <th>Inyección de la dependencia</th>
</tr>
<tr>
   <td>
<div class="image-div" style="position: relative; width: 100%;margin-bottom:1.2em;">
![](https://confluence.atsistemas.com/download/attachments/155070739/image2020-10-1_12-44-7.png?version=1&modificationDate=1601549047000&api=v2)
</div>
   </td>
   <td>
<div class="image-div" style="position: relative; width: 100%;margin-bottom:1.2em;">
![](https://confluence.atsistemas.com/download/attachments/155070739/image2020-10-1_12-43-19.png?version=1&modificationDate=1601548999000&api=v2)
</div>
   </td>
   <td>
<div class="image-div" style="position: relative; width: 100%;margin-bottom:1.2em;">
![](https://confluence.atsistemas.com/download/attachments/155070739/image2020-10-1_12-44-39.png?version=1&modificationDate=1601549079000&api=v2)
</div>
   </td>
</tr>
</table>

Como podemos ver, Koin no solo nos permite inyectar clases concretas sino interfaces, lo que nos va a permitir mantener la abstracción de tipos y respetar de igual forma el postulado del principio de Inversión de Dependencias. Igualmente, Koin nos permite nombrar las propiedades inyectadas, de forma que evitemos conflictos ante la inyección de tipos iguales o interfaces iguales con diferentes instancias.

Siguiendo este principio, hemos desarrollado otro caso de ejemplo en el cual vamos a importar la definición de una interface, para el acceso a datos.

<h6>Creación de un repositorio</h6>
Para el acceso a datos, vamos a crear un nuevo módulo que nos permita recubrir el acceso a datos de forma que evitemos incluir una dependencia con software específico para la implementación del acceso.

Tras importar las librerías de Koin al nuevo archivo de configuración de Gradle, y actualizar los archivos restantes de las módulos de Views y ViewModels, vamos a poder incluir el nuevo módulo en el listado de módulos de nuestra aplicación Koin:
<div class="image-div" style="position: relative; width: 60%;margin-left:20%;margin-bottom:1.2em;">
![](https://confluence.atsistemas.com/download/attachments/155070739/image2020-10-1_14-18-29.png?version=1&modificationDate=1601554709000&api=v2)
</div>

El nuevo módulo va a contener nuestras clases de acceso a datos, para lo cual vamos a definir la interface de acceso a datos correspondiente:

<div class="image-div" style="position: relative; width: 60%;margin-left:20%;margin-bottom:1.2em;">
![](https://confluence.atsistemas.com/download/thumbnails/155070739/image2020-10-1_14-19-55.png?version=1&modificationDate=1601554795000&api=v2)
</div>

<h6>Definición del módulo Koin</h6>

Con nuestro repositorio definido y su correspondiente interface, podemos definir la inyección de dependencias dentro del archivo correspondiente:

<ul>
<li>data/di/DATAModule:</li>
</ul>

<div class="image-div" style="position: relative; width: 60%;margin-left:20%;margin-bottom:1.2em;">
![](https://confluence.atsistemas.com/download/attachments/155070739/image2020-10-1_14-21-25.png?version=1&modificationDate=1601554885000&api=v2)
</div>

Solo nos queda definir la dependencia donde sea necesario y proceder a inyectarla. Para ello, vamos a suponer que será el viewmodel de la actividad Home, el encargado de obtener los datos de nuestra aplicación y gestionar la comunicación con el repositorio. 

<h6>Declaración de dependencias</h6>
De esta forma, definimos a través del constructor la dependencia sobre la interface correspondiente:

<div class="image-div" style="position: relative; width: 60%;margin-left:20%;margin-bottom:1.2em;">
![](https://confluence.atsistemas.com/download/attachments/155070739/image2020-10-1_14-26-13.png?version=1&modificationDate=1601555173000&api=v2)
</div>

<h6>Inyección de dependencias</h6>
Por último, debemos resolver la dependencia en el módulo encargado de proveer la instancia del viewmodel:

<div class="image-div" style="position: relative; width: 60%;margin-left:20%;margin-bottom:1.2em;">
![](https://confluence.atsistemas.com/download/attachments/155070739/image2020-10-1_14-27-17.png?version=1&modificationDate=1601555237000&api=v2)
</div>

Como podemos ver en el ejemplo anterior, hemos logrado respetar el principio de inversión de dependencias, definiendo una dependencia sobre una abstracción (IDataAccessRepository), en lugar de hacerlo sobre clases concretas, lo que nos permitirá sustituir las implementaciones realizadas sin que el resto de módulos que dependen sobre la interface sufran ningún impacto.

<h3>LiveData</h3>
Si bien durante todo el artículo hemos girado en torno a la inyección de dependencias, no podemos olvidar los objetos LiveData para implementar la comunicación entre Vista y ViewModel una vez hemos resuelto todas las dependencias e inyección de las mismas.

Estos objetos, nos va a permitir observar los cambios de estado del ViewModel, pudiendo así mantener sincronizadas las vistas en todo momento.

Combinados con la inyección de dependencias, nos van a permitir optimizar la comunicación entre componentes dentro de nuestras aplicaciones, olvidándonos así del tradicional y limitado método de serialización a través de bundles que nos permitía comunicar información entre transiciones de actividades y fragmentos.

En el ejemplo utilizado, hemos utilizado una propiedad LiveData compartida entre diferentes viewmodels para informar al Detalle de que objeto representar, pero las posibilidades que nos ofrece este mecanismo son innumerables.

<h3>Ventajas de Koin</h3>
Como hemos visto, Koin es un software muy ligero para la implementación de un sistema de inyección de dependencias

El propio software nos permite definir diferentes scopes para la definición de los diferentes elementos que inyectaremos

La documentación del software es muy completa y eficiente

<h3>Conclusiones</h3>

Como hemos podido ver, con apenas 5 clases y 40 líneas de código (si, las hemos contado), vamos a poder configurar la inyección de dependencias y diseñar una arquitectura MvvM optimizada y sin dependencias entre módulos, garantizando el desacoplamiento y permitiendo así el trabajo entre equipos y desarrolladores.

Os dejamos el [**repositorio**](https://git.atsistemas.com/users/dhernandez/repos/mvvmkoin) de código para que podáis empezar a jugar con este pequeño boilerplate.
