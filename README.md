# 🍔 Foodies — Smart Food Delivery Platform

Foodies is a modern full-stack food delivery system built to provide a seamless experience for both customers and administrators.  
The project consists of three main components: a **customer web app**, an **admin dashboard**, and a **backend REST API**.

---
## ⚙️ Tech Stack

### 🌐 Frontend
- **React.js** + React Router
- **Axios** for API communication
- **React Context API** for state management
- **React Toastify** for notifications

### 🧠 Backend
- **Java 17** / **Spring Boot**
- **Spring Data JPA**, **Hibernate**
- **MongoDB**
- **AWS S3**
- **Lombok**
- **JWT Authentication**


---

## 🚀 Features

✅ User registration and authentication  
✅ Browse food items and categories  
✅ Add/remove items from cart  
✅ Quantity management  
✅ Admin panel for managing foods and categories  
✅ AWS S3-based image storage  
✅ Token-based session handling  
✅ RESTful API architecture  

🕒 Coming soon:
- 🛍️ Ordering system  
- 💳 Payment integration  
- ❤️ Charity system  

---

## 🗄️ Database Design

The backend uses a relational model that includes:
- **users**
- **carts**
- **foods**
- **orders**
- **categories**
---

## 🧩 API Endpoints (Examples)

| Method | Endpoint | Description |
|--------|-----------|-------------|
| `POST` | `/api/register` | Register new user |
| `POST` | `/api/login` | Login user |
| `GET`  | `/api/foods` | Get all food items |
| `POST` | `/api/cart` | Add item to cart |
| `GET`  | `/api/cart` | Get cart contents |

`... and more`

---

### 1. Clone the repository
```bash
git clone https://github.com/senizdegen/foodies-project.git
```
### 2. Run backend
```bash
cd foodies-api
./mvnw spring-boot:run
```
fill out the application.properties

### 3. Run frontend (user app)
```bash
cd ../foodies-front
npm install
npm start
```
### 4. Run admin panel
```bash
cd ../adminpanel-front
npm install
npm start
```
