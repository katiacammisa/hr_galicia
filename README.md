# hr_galicia

Comentarios:

Usé Hilt porque es super simple y este caso no ameritaba nada extremadamente complejo
Elegí Compose porque es lo que más me gusta usar y funcionaba perfecto en este caso
Elegí guardar en Room para poder guardar el usuario entero ya que no podía pedir usuarios por su ID
El codigo de paginación es raro porque la API solo trae un usuario por vez y algunos datos vienen corruptos
Faltaría settear el theme bien, tendría que usar una color palette y typography para que quede mejor la UI
Hubiese navegado a details sin pasar todo el objeto sino solo el ID, pero acá no puedo pedir cosas por ID y no quería guardar la lista en un singleton

La paginación no funciona bien pero tiene que ver con la respuesta de la API.
Si pudiese reemplazar la API con una que funcione distinto debería ser perfecta la impl.

La UI no tiene nada de amor, lo puedo hacer mucho mejor :)

Estructura:
- api: Tiene el modelo de datos que viene de la API
- apiManager: El service con el endpoint y la implementación de la llamada
- data: DAO y entidades
- navigation: Bottom bar, screens y nav host
- screens: Todas las pantallas con sus view models y UIs
- storage: Inicio de DB
- util: Codigo reutilizable