<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Weather Monitoring System</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="app">

    <!-- SIDEBAR -->
    <div class="sidebar">
        <h3>Dashboard</h3>
        <a href="#weather">Weather Search</a>
        <a href="#logs">Search Logs</a>
        <a href="#system">System Info</a>
    </div>

    <!-- CONTENT -->
    <div class="content">

        <!-- WEATHER -->
        <div id="weather" class="card">
            <div class="page-title">Weather Search</div>

            <form action="weather" method="post" class="weather-form">
                <label>City Name</label>
                <input type="text" name="city" placeholder="Enter city name" required>
                <br>
                <input type="submit" value="Get Weather">
            </form>

            <%
                String city = (String) request.getAttribute("city");
                String weather = (String) request.getAttribute("weather");

                if (city != null && weather != null) {
            %>
            <div class="weather-result">
                <p><b>City:</b> <%= city %></p>
                <p><b>Weather:</b> <%= weather %></p>
            </div>
            <%
                }
            %>
        </div>

        <!-- SEARCH LOGS -->
        <div id="logs" class="card">
            <div class="page-title">Search Logs</div>
            <ul>
                <%
                    java.util.List<String> recentCities =
                        (java.util.List<String>) request.getAttribute("recentCities");

                    if (recentCities != null && !recentCities.isEmpty()) {
                        for (String c : recentCities) {
                %>
                    <li><%= c %></li>
                <%
                        }
                    } else {
                %>
                    <li>No searches yet</li>
                <%
                    }
                %>
            </ul>
        </div>

        <!-- SYSTEM INFO -->
        <div id="system" class="card">
            <div class="page-title">System Info</div>
            <ul>
                <li>Java Servlets</li>
                <li>Apache Tomcat</li>
                <li>MySQL</li>
                <li>OpenWeather API</li>
            </ul>
        </div>

    </div>
</div>

</body>
</html>
