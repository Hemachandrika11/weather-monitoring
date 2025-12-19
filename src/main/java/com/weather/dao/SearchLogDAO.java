 package com.weather.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchLogDAO {

    public static void saveSearch(String city) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps =
                con.prepareStatement("INSERT INTO search_logs(city) VALUES (?)");
            ps.setString(1, city);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> getRecentSearches() {
        List<String> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            ResultSet rs =
                con.prepareStatement(
                    "SELECT city FROM search_logs ORDER BY id DESC LIMIT 5"
                ).executeQuery();
            while (rs.next()) list.add(rs.getString("city"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

