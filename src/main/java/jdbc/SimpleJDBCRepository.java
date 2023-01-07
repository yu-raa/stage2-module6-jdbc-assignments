package jdbc;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleJDBCRepository {

    private Connection connection = null;
    private PreparedStatement ps = null;
    private Statement st = null;

    private static final String createUserSQL = "insert into myusers values (";
    private static final String updateUserSQL = "update table myusers set ";
    private static final String deleteUser = "delete from myusers where id=";
    private static final String findUserByIdSQL = "select * from myusers where id=";
    private static final String findUserByNameSQL = "select * from myusers where firstname='";
    private static final String findAllUserSQL = "select * from myusers";

    public Long createUser(User user) {
        connection = new CustomConnector().getConnection(CustomDataSource.getInstance().getUrl(), CustomDataSource.getInstance().getName(), CustomDataSource.getInstance().getPassword());
        try {
            st = connection.createStatement();
            st.execute(createUserSQL + user.getId() + ", '" + user.getFirstName() + "', '" + user.getLastName() + "', " + user.getAge() + ")");
            return user.getId();
        } catch (SQLException e) {
            return null;
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException e) {

            }
        }
    }

    public User findUserById(Long userId) {
        connection = new CustomConnector().getConnection(CustomDataSource.getInstance().getUrl(), CustomDataSource.getInstance().getName(), CustomDataSource.getInstance().getPassword());
        try {
            st = connection.createStatement();
            ResultSet result = st.executeQuery(findUserByIdSQL + userId);
            User user = new User();
            user.setId(userId);
            user.setFirstName(result.getString(2));
            user.setLastName(result.getString(3));
            user.setAge(result.getInt(4));
            return user;
        }
        catch (SQLException e) {
            return null;
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException e) {

            }
        }
    }

    public User findUserByName(String userName) {
        connection = new CustomConnector().getConnection(CustomDataSource.getInstance().getUrl(), CustomDataSource.getInstance().getName(), CustomDataSource.getInstance().getPassword());
        try {
            st = connection.createStatement();
            ResultSet result = st.executeQuery(findUserByNameSQL + userName + "'");
            User user = new User();
            user.setId(result.getLong(1));
            user.setFirstName(userName);
            user.setLastName(result.getString(3));
            user.setAge(result.getInt(4));
            return user;
        }
        catch (SQLException e) {
            return null;
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException e) {

            }
        }
    }

    public List<User> findAllUser() {
        connection = new CustomConnector().getConnection(CustomDataSource.getInstance().getUrl(), CustomDataSource.getInstance().getName(), CustomDataSource.getInstance().getPassword());
        try {
            st = connection.createStatement();
            ResultSet result = st.executeQuery(findAllUserSQL);
            List<User> users = new ArrayList<>();
            while (result.next()) {
                User user = new User();
                user.setId(result.getLong(1));
                user.setFirstName(result.getString(2));
                user.setLastName(result.getString(3));
                user.setAge(result.getInt(4));
                users.add(user);
            }
            return users;
        }
        catch (SQLException e) {
            return null;
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException e) {

            }
        }
    }

    public User updateUser(User user) {
        connection = new CustomConnector().getConnection(CustomDataSource.getInstance().getUrl(), CustomDataSource.getInstance().getName(), CustomDataSource.getInstance().getPassword());
        try {
            st = connection.createStatement();
            ResultSet result = st.executeQuery(updateUserSQL + "id=" + user.getId() + ", firstname='" + user.getFirstName() + "', lastname='" + user.getLastName() + "', age=" + user.getAge());
            return user;
        }
        catch (SQLException e) {
            return null;
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException e) {

            }
        }
    }

    public void deleteUser(Long userId) {
        connection = new CustomConnector().getConnection(CustomDataSource.getInstance().getUrl(), CustomDataSource.getInstance().getName(), CustomDataSource.getInstance().getPassword());
        try {
            st = connection.createStatement();
            st.executeQuery(deleteUser + userId);
        }
        catch (SQLException e) {
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException e) {

            }
        }
    }
}
