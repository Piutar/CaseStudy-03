package com.dbc;

import com.dbc.InF.UserDBCInF;
import com.jdbc.JDBCConnection;
import com.model.User;
import com.service.CategoryService;
import com.service.InF.CategoryInF;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDBC extends JDBCConnection implements UserDBCInF{


    @Override
    public void insert(User user) {
        int roleId=0;
        String sql = "INSERT INTO user(email, username, password,avatar,role_id) VALUES (?,?,?,?,?);";
        Connection con = super.getJDBCConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getAvatar());
            try {
                if(user.getRoleId()==1) {
                    roleId=1;
                }else {
                    roleId=2;
                }

            } catch (Exception e) {
                roleId=2;
            }
            ps.setInt(5, roleId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(User user) {
        String sql = "UPDATE user SET email = ? , username = ?, password = ?, avatar = ?, role_id = ? WHERE id = ?;";
        Connection con = super.getJDBCConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getAvatar());
            ps.setInt(5, user.getRoleId());
            ps.setInt(6, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM user WHERE id = ?;";
        Connection con = super.getJDBCConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User get(String username) {
        String sql = "SELECT * FROM user WHERE username = ? ;";
        Connection con = super.getJDBCConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();

                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAvatar(rs.getString("avatar"));
                user.setRoleId(Integer.parseInt(rs.getString("role_id")));

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User get(int id) {
        String sql = "SELECT * FROM user WHERE id = ? ;";
        Connection con = super.getJDBCConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();

                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAvatar(rs.getString("avatar"));
                user.setRoleId(Integer.parseInt(rs.getString("role_id")));

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM user;";
        Connection conn = super.getJDBCConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();

                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAvatar(rs.getString("avatar"));
                user.setRoleId(Integer.parseInt(rs.getString("role_id")));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public List<User> search(String keyword) {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM user WHERE name LIKE ? ;";
        Connection conn = super.getJDBCConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();

                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAvatar(rs.getString("avatar"));
                user.setRoleId(Integer.parseInt(rs.getString("role_id")));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public boolean checkExistEmail(String email) {
        boolean checked = false;
        Connection conn = JDBCConnection.getJDBCConnection();
        try {
            String query = "select * from user where email = ?;";

            PreparedStatement psmt = conn.prepareStatement(query);

            psmt.setString(1, email);

            ResultSet resultSet = psmt.executeQuery();

            if (resultSet.next()) {
                checked = true;
            }
            psmt.close();
            conn.close();
        } catch (SQLException ex) {
        }
        return checked;
    }

    @Override
    public boolean checkExistUsername(String username) {
        boolean checked = false;
        Connection conn = JDBCConnection.getJDBCConnection();
        try {
            String query = "select * from user where username = ?;";

            PreparedStatement psmt = conn.prepareStatement(query);

            psmt.setString(1, username);

            ResultSet resultSet = psmt.executeQuery();

            if (resultSet.next()) {
                checked = true;
            }
            psmt.close();
            conn.close();
        } catch (SQLException ex) {
        }
        return checked;
    }
}
