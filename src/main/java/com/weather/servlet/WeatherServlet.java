package com.weather.servlet;

import com.weather.dao.SearchLogDAO;
import com.weather.dao.WeatherDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/weather")   // ✅ KEEP THIS
public class WeatherServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String city = req.getParameter("city");

        String weather = WeatherDAO.getWeather(city);

        SearchLogDAO.saveSearch(city);

        req.setAttribute("city", city);
        req.setAttribute("weather", weather);
        req.setAttribute("recentCities", SearchLogDAO.getRecentSearches());

        req.getRequestDispatcher("index.jsp").forward(req, res);
    }
}
