## pingbus

       
`docker run --name mysql_pingbus -d -p 3306:3306 -v /srv/mysql:/var/lib/mysql -e MYSQL_USER=pingbus -e MYSQL_PASSWORD=root -e MYSQL_DATABASE=pingbus_db  -e MYSQL_ROOT_PASSWORD=root mysql:latest`
            
            
REST RESP:
200 — Successful;
401 — Not Authorized;
404 — Not Found;
500 — Server error during operation.

           