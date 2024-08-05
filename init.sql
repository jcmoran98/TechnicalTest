CREATE TABLE customer (
        id VARCHAR(50) NOT NULL PRIMARY KEY,
        firstName VARCHAR(20) NOT NULL,
        lastName VARCHAR(20) NOT NULL,
        phone VARCHAR(20) NOT NULL,
        birthdate DATE NOT NULL
);

CREATE PROCEDURE proc_InsertCustomer(
    IN idInput VARCHAR(50),
    IN firstNameInput VARCHAR(20),
    IN lastNameInput VARCHAR(20),
    IN phoneInput VARCHAR(20),
    IN birthdateInput DATE
)
BEGIN
    INSERT INTO customer (id, firstName, lastName, phone, birthdate)
    VALUES (idInput, firstNameInput, lastNameInput, phoneInput, birthdateInput);
END;

CREATE PROCEDURE proc_GetCustomerById(
    IN customerId VARCHAR(50)
)
BEGIN
    SELECT id, firstName, lastName, phone, birthdate
    FROM customer
    WHERE id = customerId;
END;


CREATE PROCEDURE proc_UpdateCustomer(
    IN customerId VARCHAR(50),
    IN newFirstName VARCHAR(20),
    IN newLastName VARCHAR(20),
    IN newPhone VARCHAR(20),
    IN newBirthdate DATE
)
BEGIN
    UPDATE customer
    SET 
        firstName = IFNULL(newFirstName, firstName),
        lastName = IFNULL(newLastName, lastName),
        phone = IFNULL(newPhone, phone),
        birthdate = IFNULL(newBirthdate, birthdate)
    WHERE id = customerId;
END;


CREATE PROCEDURE proc_DeleteCustomer(
    IN customerId VARCHAR(50)
)
BEGIN
    DELETE FROM customer
    WHERE id = customerId;
END;


CREATE PROCEDURE proc_GetAllCustomers(
    IN sortById BOOLEAN,
    IN sortByName BOOLEAN,
    IN sortByBirthdate BOOLEAN
)
BEGIN
    SET @queryCustomer = 'SELECT * FROM customer';
    
    SET @orderBy = '';

    IF sortById THEN
        SET @orderBy = CONCAT(@orderBy, 'id ASC');
    END IF;

    IF sortByName THEN
        IF LENGTH(@orderBy) > 0 THEN
            SET @orderBy = CONCAT(@orderBy, ', ');
        END IF;
        SET @orderBy = CONCAT(@orderBy, 'firstName ASC');
    END IF;

    IF sortByBirthdate THEN
        IF LENGTH(@orderBy) > 0 THEN
            SET @orderBy = CONCAT(@orderBy, ', ');
        END IF;
        SET @orderBy = CONCAT(@orderBy, 'birthdate DESC');
    END IF;

    IF LENGTH(@orderBy) > 0 THEN
        SET @queryCustomer = CONCAT(@queryCustomer, ' ORDER BY ', @orderBy);
    END IF;

    PREPARE stmt FROM @queryCustomer;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END;

