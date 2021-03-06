package by.training.cryptomarket.daojdbctemplate.sql;



import by.training.cryptomarket.daojdbctemplate.OrderDao;
import by.training.cryptomarket.entity.Order;
import by.training.cryptomarket.entity.User;
import by.training.cryptomarket.exception.PersistentException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class of OrderDao implamentation.
 * @author Nikita Karchahin
 * @version 1.0
 */
@Repository
public class OrderDaoImpl extends BaseDao implements OrderDao {



    /**
     * The field to store the sql create request.
     */
    private static  String createSql = "INSERT INTO orders (user_id,pair,amount,price,type,state) VALUES (?, ?::currencyPairs, ?, ?, ?::typesoforder, ?::statesOfOrder)";

    /**
     * The field to store the sql readList request.
     */
    private static  String readListSql = "SELECT identity,user_id,pair,amount,price,type, state FROM orders";

    /**
     * The field to store the sql read request.
     */
    private static  String readSql = "SELECT user_id,pair,amount,price,type,state FROM orders WHERE identity = ?";

    /**
     * The field to store the sql update request.
     */
    private static  String updateSql = "UPDATE orders SET user_id = ?, pair = ?::currencyPairs, amount = ?, price = ?, type = ?::typesoforder, state = ?::statesOfOrder WHERE identity = ?";

    /**
     * The field to store the sql delete request.
     */
    private static  String deleteSql = "DELETE FROM orders WHERE identity = ?";



    /**
     * The method that returns collection of orders.
     * @return collection of orders
     * @throws PersistentException
     */
    @Override
    public List<Order> read()  {

        List<Order> orders = jdbcOperations.query(readListSql, new RowMapper<Order>() {
            @Override
            public Order mapRow(ResultSet resultSet, int i) throws SQLException {
                Order order = new Order();
                order.setIdentity(resultSet.getInt("identity"));
                order.setUserId(resultSet.getInt("user_id"));
                order.setPair((resultSet.getString("pair")));
                order.setAmount((resultSet.getBigDecimal("amount")).doubleValue());
                order.setPrice((resultSet.getBigDecimal("price")).doubleValue());
                order.setType((resultSet.getString("type")));
                order.setState((resultSet.getString("state")));

                return order;
            }
        });
        return orders;



    }


    /**
     * The method that creates a new record.
     * @param order order
     * @return number of record
     * @throws PersistentException
     */
    @Override
    public Integer create(final Order order)  {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                PreparedStatement ps =
                        connection.prepareStatement(createSql,Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, order.getUserId());
                ps.setString(2, order.getPair());
                ps.setBigDecimal(3, new BigDecimal(order.getAmount()));
                ps.setBigDecimal(4, new BigDecimal(order.getPrice()));
                ps.setString(5, order.getType());
                ps.setString(6, order.getState());
                return ps;
            }
        }, keyHolder);

        return (Integer) keyHolder.getKeys().get("identity");


     }

    /**
     * The method that returns order by id.
     * @param identity
     * @return order
     * @throws PersistentException
     */
    @Override
    public Order read(final Integer identity)  {
        Order order = jdbcOperations.queryForObject(readSql, new Object[]{identity}, new RowMapper<Order>() {
            @Override
            public Order mapRow(ResultSet resultSet, int i) throws SQLException {
                Order order = new Order();
                order.setIdentity(identity);
                order.setUserId(resultSet.getInt("user_id"));
                order.setPair(resultSet.getString("pair"));
                order.setAmount(resultSet.getBigDecimal("amount").doubleValue());
                order.setPrice(resultSet.getBigDecimal("price").doubleValue());
                order.setType(resultSet.getString("type"));
                order.setState(resultSet.getString("state"));
                return order;
            }
        });
        return order;
    }


    /**
     * The method that updates order.
     * @param order order
     * @throws PersistentException PersistentException
     */
    @Override
    public void update(final Order order) {
             jdbcOperations.update(updateSql,order.getUserId(),order.getPair(),order.getAmount(),order.getPrice(),order.getType(),order.getState(),order.getIdentity());
    }

    /**
     *  The method that deletes order by id.
     * @param identity identity
     * @throws PersistentException
     */
    @Override
    public void delete(final Integer identity) {

        jdbcOperations.update(deleteSql, identity );
    }
}
