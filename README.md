# Downtown Cafe Management System

A premium, full-stack cafe management and online ordering solution. This system allows customers to browse a digital menu and place orders, while providing an administrative dashboard for order tracking and management.

## 🚀 Live Demo
- **Backend:** [Link to your Render URL]
- **Frontend:** [Link to your Vercel/Netlify URL]

## ✨ Features
- **Dynamic Digital Menu:** Beautifully categorized menu with real-time updates.
- **Table-Based Ordering:** Contactless ordering system via a floating cart.
- **Admin Dashboard:** Secure interface for staff to monitor and fulfill orders.
- **Modern UI:** Responsive, glassmorphic design built with React.
- **Full-Stack Integration:** Spring Boot backend with a MySQL database.

## 🛠 Tech Stack
- **Frontend:** React.js, CSS3 (Vanilla), Axios.
- **Backend:** Java 17, Spring Boot, Spring Data JPA.
- **Database:** MySQL.
- **Deployment:** Docker (Render), Vercel/Netlify, Aiven MySQL.

## 📦 Project Structure
```text
/backend  - Spring Boot API & Docker configuration
/frontend - React application & Styling
```

## ⚙️ Local Setup

### 1. Prerequisites
- Java 17 or higher
- Node.js (v18+)
- MySQL Database

### 2. Backend Setup
```bash
cd backend
./mvnw spring-boot:run
```
*Configure your database credentials in `backend/src/main/resources/application.properties`.*

### 3. Frontend Setup
```bash
cd frontend
npm install
npm start
```
*Set `REACT_APP_API_URL` to `http://localhost:8080` for local development.*

## 🌐 Deployment
This project is configured for cloud deployment using:
- **Render** for the Java backend (via Docker).
- **Aiven** for managed MySQL.
- **Vercel/Netlify** for the React frontend.

## 📜 License
This project is for educational and portfolio purposes.
