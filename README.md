# 🍽️ Smart Food Stall Billing System

A modern **Spring Boot-based Food Stall Billing System** that helps automate food ordering and billing operations. The application provides separate interfaces for customers and administrators, allowing efficient menu management, order processing, and bill generation.

---

## ✨ Features

- 🍔 Customer Ordering Interface
- 👨‍💼 Admin Dashboard
- 📋 Manage Food Menu
- ➕ Add New Food Items
- ✏️ Update Existing Items
- 🗑️ Delete Food Items
- 🛒 Place Food Orders
- 🧾 Automatic Bill Generation
- 💰 Real-Time Price Calculation
- 🔒 Spring Security Authentication
- 💾 MySQL Database Integration
- 🌱 Automatic Sample Data Seeding

---

## 🛠️ Tech Stack

| Technology | Version |
|------------|---------|
| Java | 21 |
| Spring Boot | 3.5.3 |
| Spring MVC | ✔ |
| Spring Data JPA | ✔ |
| Spring Security | ✔ |
| Hibernate | ✔ |
| Thymeleaf | ✔ |
| MySQL | ✔ |
| Maven | ✔ |
| HTML5 | ✔ |
| CSS3 | ✔ |
| JavaScript | ✔ |

---

## 📂 Project Structure

```
src
 └── main
     ├── java
     │   └── com.smartfoodstall.billing
     │       ├── controller
     │       ├── service
     │       ├── repository
     │       ├── model
     │       ├── dto
     │       └── config
     └── resources
         ├── templates
         ├── static
         └── application.properties
```

---

## ⚙️ Prerequisites

- Java 21
- Maven
- MySQL Server
- IntelliJ IDEA / Eclipse / VS Code

---

## 🚀 Installation

### Clone Repository

```bash
git clone https://github.com/a000afzalkhan/Food-stall-Billing-System.git
```

### Navigate

```bash
cd Food-stall-Billing-System
```

### Configure Database

Update `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/smart_food_stall
spring.datasource.username=root
spring.datasource.password=your_password
```

### Run Application

```bash
mvn spring-boot:run
```

or

Run `SmartFoodStallBillingApplication.java`

---

## 🌐 Application URLs

| Page | URL |
|------|-----|
| Customer Dashboard | http://localhost:8080/ |
| Admin Dashboard | http://localhost:8080/admin |

---

## 📸 Modules

### 👤 Customer Module

- Browse Food Menu
- Select Food Items
- Place Order
- View Generated Bill

### 👨‍💼 Admin Module

- View Menu
- Add Food Item
- Edit Food Item
- Delete Food Item
- Manage Billing

---

## 📦 Dependencies

- Spring Boot Starter Web
- Spring Boot Starter Thymeleaf
- Spring Boot Starter Data JPA
- Spring Boot Starter Security
- Spring Boot Starter Validation
- MySQL Connector
- H2 Database (Development)
- Spring Boot Test

---

## 🎯 Future Enhancements

- Online Payment Integration
- QR Code Billing
- Order History
- PDF Invoice Generation
- Customer Login & Registration
- Email Bill Delivery
- Sales Analytics Dashboard

---

## 👨‍💻 Author

**Afzal Khan**

GitHub: https://github.com/a000afzalkhan

---

## ⭐ Support

If you like this project, please give it a ⭐ on GitHub.

---

## 📄 License

This project is developed for learning, educational, and portfolio purposes.
