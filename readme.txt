----Link del video: https://drive.google.com/file/d/1qUP8dThOnT7N4rpA1drGvu99mi97LJgF/view?usp=sharing

Aplicación de mensajería grupal en Java que utiliza sockets multicast para permitir 
a los usuarios enviar y recibir mensajes en un grupo multicast. 
Cada mensaje contiene información sobre el remitente, la marca de tiempo y el contenido. 
----El alcance de la aplicación incluye:

- Configuración de un servidor que escucha y muestra mensajes entrantes en la consola.
- Implementación de un cliente que permite a los usuarios enviar mensajes al grupo multicast.
- Serialización de objetos `Mensaje` para la transmisión de datos.
- Formateo de la marca de tiempo para mostrarla en un formato legible.

----Limitaciones

- Puede haber problemas pérdida de paquetes en una red multicast, lo que podría afectar la entrega oportuna de los mensajes.


----Obstáculos
- La implementación de la lógica de serialización y deserialización de objetos `Mensaje` fue un desafío inicial. Se resolvió utilizando la interfaz `Serializable` y `ObjectInputStream`/`ObjectOutputStream`.
- La actualización para mostrar la marca de tiempo en un formato legible en el servidor requería el uso de `SimpleDateFormat` y el formateo adecuado de la fecha.
