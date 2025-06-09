## La base de datos

* En las opciones de red de nuestra máquina virtual, seleccionamos "adaptador puente" para que el host pueda conectarse fácilmente a la base de datos y la máquina virtual tenga una IP visible en la red local.
* Para que el servidor acepte conexiones externas por IP debes editar el archivo postgresql.conf y hacer que escuche conexiones en todas las interfaces de red, no solo en localhost.
Para ello, accede a él:

````
sudo nano /etc/postgresql/[versión instalada, en mi caso 15]/main/postgresql.conf
````

y cambia
````
#listen_addresses = 'localhost'
````
por

````
listen_addresses = '*'
````

* Ahora debes definir qué usuarios pueden conectarse, desde qué IPs, y con qué método de autenticación. Para ello, edita el archivo de configuración de acceso (de nuevo):
````
sudo nano /etc/postgresql/[versión]/main/pg_hba.conf
````
y añade esta línea al final:
````
host    all    all    0.0.0.0/0    md5
````
Esto permite conexiones desde cualquier dirección IP del mundo (0.0.0.0/0), a cualquier base de datos (all), por cualquier usuario (all), siempre que tenga contraseña (md5).
* Luego, reinicia PostgreSQL para aplicar los cambios:
````
sudo systemctl restart postgresql
````

* Conéctate al cliente interactivo de línea de comandos de PostgreSQL con usuario postgres y crea el usuario que desees con la contraseña que desees para la aplicación:

![Imagen](images/im1.png)

* Crea la base de datos que desees para la aplicación:

![Imagen](images/im2.png)

* Crea la tabla correspondiente dentro de la base de datos que creaste (con usuario postgres) y dale permisos al usuario que creaste:

![Imagen](images/im3.png)

* Tras seleccionar "Guardar en base de datos" comprueba el resultado con un select de la tabla:

![Imagen](images/im4.png)