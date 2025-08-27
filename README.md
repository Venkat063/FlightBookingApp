# FlightBookingApp ✈️

A modern Online Flight Booking web application built with **Java Servlets**, **JSP**, **JDBC (MySQL)**, and custom CSS. Features user authentication, flight search, booking, and an admin dashboard.

---

## 🚀 Features

- User **Registration & Login** (secure SHA-256 password hashing)
- **Flight Search** (by From / To / Date)
- **Book Flights** and **View My Bookings**
- **Admin Panel** for adding new flights
- Production-ready Java folder structure with DAO, Models, Servlets, and JSP for views

---
<img width="1919" height="1022" alt="Screenshot 2025-08-25 105220" src="https://github.com/user-attachments/assets/dd23e1c7-a0e2-4236-b7ab-38982a4fd4c7" />

## 🗂️ Project Structure

FlightBookingApp/
├─ pom.xml
├─ src/
│ ├─ main/java/com/flightapp/
│ │ ├─ model/
│ │ ├─ dao/
│ │ └─ servlet/
│ └─ main/webapp/
│ ├─ META-INF/
│ ├─ WEB-INF/
│ │ ├─ web.xml
│ │ └─ views/
│ ├─ assets/css/
│ └─ favicon.ico (optional)
└─ README.md




---

## 🛠️ Tech Stack

- **Java 11+**
- **Jakarta Servlet API** (5.x)
- **JSP / JSTL**
- **MySQL 8+**
- **Tomcat 9+**
- **Maven** (WAR)
- **HTML5 / CSS3** (modern, responsive, no bootstrap)

---

## ⚡ Quick Start

### 1. **Database Setup**

Run these SQL commands in MySQL to create the DB and initial data:


### 2. **Configure DB Credentials**

Open `src/main/java/com/flightapp/dao/DBUtil.java`  
Edit these lines with your local MySQL credentials:


---

### 3. **Build and Run**

- **Import** the project in Eclipse/IntelliJ (as Maven Project)
- Run `mvn clean install` to build the `.war`
- **Deploy** WAR to Tomcat 9+ (`webapps` folder or use IDE integration)
- Access via [http://localhost:8080/FlightBookingApp/](http://localhost:8080/FlightBookingApp/)

---

## 🌟 Usage

- **Home & Flight Search:** `/`
- **Login:** `/login`
- **Register:** `/register`
- **Book Flight:** via Search page
- **My Bookings:** `/my-bookings`
- **Admin Add Flight:** `/admin/add-flight`  
  (login as `admin@flightapp.com` / `admin123`)

---

## 🚧 Customization & Next Steps

- Show booking details by joining flight data
- Cancel booking (increment seats back)
- Add input validation and error UI
- Dropdowns for city/airline in search/admin forms
- Pagination on flight searches
- Use BCrypt/Argon2 for password hashing in production

---

## 📄 License

MIT — for educational and personal/professional portfolio use.

---

## 👤 Author

[YOUR NAME]  
[LinkedIn or Portfolio URL]  
[Email Address]

---

