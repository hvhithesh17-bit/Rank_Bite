package com.rankbite.dao;

import com.rankbite.model.Owner;
import com.rankbite.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OwnerDAO {

    public boolean register(Owner owner) {

        String sql = """
                INSERT INTO owners (name, email, password, phone)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, owner.getName());
            ps.setString(2, owner.getEmail());
            ps.setString(3, owner.getPassword());
            ps.setString(4, owner.getPhone());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Owner login(String email, String password) {

        String sql = """
                SELECT * FROM owners
                WHERE email = ? AND password = ?
                """;

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Owner owner = new Owner();

                owner.setOwnerId(rs.getInt("owner_id"));
                owner.setName(rs.getString("name"));
                owner.setEmail(rs.getString("email"));
                owner.setPassword(rs.getString("password"));
                owner.setPhone(rs.getString("phone"));

                return owner;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}