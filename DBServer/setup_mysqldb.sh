#!/bin/bash

echo "Installing MySQL Server .................................."


## variables ##
db_user='fuser'
db_password='flight'
db_name='flight_sys'
db_table='Flights'
###############


##### FUNCTIONS #######
function main(){
    install_db
    configurate_db
    create_table
    insert_data_into_table

    #End of script actionssud
    echo "MySQL user created."
    echo "Username: $db_user"
    echo "Password: $db_password"
    echo "MySQL database and table created."
    echo "Database: $db_name"
    echo "Table: $db_table"
}

function install_db(){
    sudo apt install mysql-server
}

# user: root -> 'root' password
function configurate_db(){
    sudo mysql -uroot -proot<<SCRIPT
    CREATE DATABASE $db_name;
    CREATE USER $db_user IDENTIFIED BY '$db_password';
    GRANT ALL PRIVILEGES ON $db_name.* TO $db_user;
    FLUSH PRIVILEGES;
SCRIPT
}

function create_table(){
    # connect with fuser user
    mysql -u$db_user -p$db_password<<SCRIPT
    USE $db_name;
    CREATE TABLE $db_table (
    fdate DATE NOT NULL,
    ftime TIME NOT NULL,
    departure_airport varchar(35) NOT NULL,
    arrival_airport varchar(35) NOT NULL,
    flight_code varchar(7) NOT NULL,
    seats int NOT NULL,
    price float(6,2) NOT NULL,
    PRIMARY KEY (flight_code)
    );
SCRIPT
}

function insert_data_into_table(){
    # connect with fuser user
    mysql -u$db_user -p$db_password<<SCRIPT
    USE $db_name;
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-16', '07:35:00', 'ATHENS', 'BERLIN', 'ATBR014', 32, 150.50);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-16', '08:00:00', 'ATHENS', 'BERLIN', 'ATBR017', 48, 110.00);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-16', '09:15:00', 'ATHENS', 'BERLIN', 'ATBR045', 50, 87.50);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-16', '10:35:00', 'ATHENS', 'BERLIN', 'ATBR110', 32, 150.50);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-16', '12:00:00', 'ATHENS', 'BERLIN', 'ATBR120', 48, 110.00);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-16', '14:15:00', 'ATHENS', 'BERLIN', 'ATBR129', 50, 87.50);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-16', '16:35:00', 'ATHENS', 'BERLIN', 'ATBR224', 32, 150.50);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-16', '18:00:00', 'ATHENS', 'BERLIN', 'ATBR229', 48, 110.00);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-16', '18:55:00', 'ATHENS', 'BERLIN', 'ATBR356', 50, 87.50);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-16', '20:35:00', 'ATHENS', 'BERLIN', 'ATBR359', 32, 150.50);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-16', '21:00:00', 'ATHENS', 'BERLIN', 'ATBR456', 48, 110.00);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-16', '21:45:00', 'ATHENS', 'BERLIN', 'ATBR476', 50, 87.50);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-16', '22:00:00', 'ATHENS', 'BERLIN', 'ATBR506', 32, 150.50);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-16', '22:30:00', 'ATHENS', 'BERLIN', 'ATBR512', 48, 110.00);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-16', '22:45:00', 'ATHENS', 'BERLIN', 'ATBR525', 50, 87.50);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-16', '23:00:00', 'ATHENS', 'BERLIN', 'ATBR567', 32, 150.50);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-16', '23:30:00', 'ATHENS', 'BERLIN', 'ATBR689', 48, 110.00);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-16', '23:55:00', 'ATHENS', 'BERLIN', 'ATBR600', 50, 87.50);

    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-23', '07:35:00', 'BERLIN', 'ATHENS', 'BRAT014', 32, 150.50);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-23', '08:00:00', 'BERLIN', 'ATHENS', 'BRAT017', 48, 110.00);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-23', '09:15:00', 'BERLIN', 'ATHENS', 'BRAT045', 50, 87.50);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-23', '10:35:00', 'BERLIN', 'ATHENS', 'BRAT110', 32, 150.50);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-23', '12:00:00', 'BERLIN', 'ATHENS', 'BRAT120', 48, 110.00);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-23', '14:15:00', 'BERLIN', 'ATHENS', 'BRAT129', 50, 87.50);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-23', '16:35:00', 'BERLIN', 'ATHENS', 'BRAT224', 32, 150.50);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-23', '18:00:00', 'BERLIN', 'ATHENS', 'BRAT229', 48, 110.00);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-23', '18:55:00', 'BERLIN', 'ATHENS', 'BRAT356', 50, 87.50);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-23', '20:35:00', 'BERLIN', 'ATHENS', 'BRAT359', 32, 150.50);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-23', '21:00:00', 'BERLIN', 'ATHENS', 'BRAT456', 48, 110.00);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-23', '21:45:00', 'BERLIN', 'ATHENS', 'BRAT476', 50, 87.50);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-23', '22:00:00', 'BERLIN', 'ATHENS', 'BRAT506', 32, 150.50);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-23', '22:30:00', 'BERLIN', 'ATHENS', 'BRAT512', 48, 110.00);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-23', '22:45:00', 'BERLIN', 'ATHENS', 'BRAT525', 50, 87.50);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-23', '23:00:00', 'BERLIN', 'ATHENS', 'BRAT567', 32, 150.50);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-23', '23:30:00', 'BERLIN', 'ATHENS', 'BRAT689', 48, 110.00);
    INSERT INTO $db_table (fdate, ftime, departure_airport, arrival_airport, flight_code, seats, price)
    VALUES ('2019-04-23', '23:55:00', 'BERLIN', 'ATHENS', 'BRAT600', 50, 87.50);
SCRIPT
}
######################

main "$@"
