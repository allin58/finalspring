package by.training.cryptomarket.daojdbctemplate.transaction;

import by.training.cryptomarket.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * The base class for all transactions.
 * @author Nikita Karchahin
 * @version 1.0
 */
@Component
public abstract class DataBaseTransaction {

    /**
     * The field for storage a logger.
     */
    static final Logger LOGGER = LogManager.getLogger("by.training.final.DataBaseLogger");

 /*   *//**
     * The field for storage a connection.
     *//*
    protected Connection connection;*/


   /* @Autowired
    DriverManagerDataSource driverManagerDataSource;*/

    @Autowired
    protected JdbcOperations jdbcOperations;


  /*  @PostConstruct
    public void init() {
        try {
            this.connection = driverManagerDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/




    /**
     * The constructor without a parameter.
         */
    public DataBaseTransaction() {

    }

    /**
     * The transaction abstract method.
     * @throws PersistentException PersistentException
     */
    abstract void commit() throws PersistentException;

   /* *//**
     * The transaction rollback method.
     * @throws PersistentException PersistentException
     *//*
    abstract void rollback() throws PersistentException;*/
}
