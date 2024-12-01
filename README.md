# API Documentation

This document provides an overview of the API endpoints available in the Task Management System. Each endpoint includes its HTTP method, endpoint URL, request parameters, and response details.

---

## AdminController Endpoints

### **Get User by ID**
- **URL:** `/admin/users/{id}`
- **Method:** `GET`
- **Description:** Retrieve a user by their ID.
- **Path Parameters:**
  - `id` (long): The ID of the user to retrieve.
- **Response:**
  - **200 OK:** User details in JSON format.

### **Get All Users**
- **URL:** `/admin/users`
- **Method:** `GET`
- **Description:** Retrieve a paginated list of all users.
- **Query Parameters:**
  - `page` (int, optional, default: 0): The page number to retrieve.
  - `size` (int, optional, default: 10): The number of users per page.
- **Response:**
  - **200 OK:** A paginated list of users.

### **Delete User by ID**
- **URL:** `/admin/users/{id}`
- **Method:** `DELETE`
- **Description:** Delete a user by their ID.
- **Path Parameters:**
  - `id` (long): The ID of the user to delete.
- **Response:**
  - **200 OK:** Confirmation message.

---

## HistoryController Endpoints

### **Get History**
- **URL:** `/history`
- **Method:** `GET`
- **Description:** Retrieve a list of all history records.
- **Response:**
  - **200 OK:** List of history records in JSON format.

### **Clear History**
- **URL:** `/history/clear`
- **Method:** `DELETE`
- **Description:** Clear all history records.
- **Response:**
  - **200 OK:** Confirmation message.

---

## TaskController Endpoints

### **Create Task**
- **URL:** `/tasks/`
- **Method:** `POST`
- **Description:** Create a new task.
- **Request Body:**
  - TaskDto object containing task details (title, description, priority, etc.).
- **Response:**
  - **201 Created:** The created task details in JSON format.

### **Get Task by ID**
- **URL:** `/tasks/{id}`
- **Method:** `GET`
- **Description:** Retrieve a task by its ID.
- **Path Parameters:**
  - `id` (long): The ID of the task to retrieve.
- **Response:**
  - **200 OK:** Task details in JSON format.

### **Get All Tasks**
- **URL:** `/tasks/`
- **Method:** `GET`
- **Description:** Retrieve a paginated list of all tasks.
- **Query Parameters:**
  - Standard pagination parameters (`page`, `size`).
- **Response:**
  - **200 OK:** A paginated list of tasks.

### **Full Update Task**
- **URL:** `/tasks/{id}`
- **Method:** `PUT`
- **Description:** Fully update a task.
- **Path Parameters:**
  - `id` (long): The ID of the task to update.
- **Request Body:**
  - TaskDto object containing updated task details.
- **Response:**
  - **200 OK:** Updated task details in JSON format.

### **Partial Update Task**
- **URL:** `/tasks/{id}`
- **Method:** `PATCH`
- **Description:** Partially update a task.
- **Path Parameters:**
  - `id` (long): The ID of the task to update.
- **Request Body:**
  - TaskDto object with partial updates.
- **Response:**
  - **200 OK:** Updated task details in JSON format.

### **Delete Task**
- **URL:** `/tasks/{id}`
- **Method:** `DELETE`
- **Description:** Delete a task by its ID.
- **Path Parameters:**
  - `id` (long): The ID of the task to delete.
- **Response:**
  - **200 OK:** Confirmation message.

### **Change Task Status**
- **URL:** `/tasks/{id}/changestatus`
- **Method:** `PUT`
- **Description:** Change the status of a task.
- **Path Parameters:**
  - `id` (long): The ID of the task to update.
- **Request Body:**
  - JSON object containing the new status: `{ "status": "new_status" }`
- **Response:**
  - **200 OK:** Confirmation message.

### **Search Tasks**
- **URL:** `/tasks/search`
- **Method:** `GET`
- **Description:** Search for tasks based on various criteria.
- **Query Parameters:**
  - `title` (String, optional): Search by task title.
  - `description` (String, optional): Search by task description.
  - `status` (String, optional): Search by task status.
  - `dueDate` (Date, optional): Search by due date.
  - `priority` (String, optional): Search by task priority.
- **Response:**
  - **200 OK:** List of tasks matching the criteria.

---

## UserController Endpoints

### **Authenticate User**
- **URL:** `/users/authenticate`
- **Method:** `POST`
- **Description:** Authenticate a user.
- **Request Body:**
  - UserEntity object containing user credentials (username, password).
- **Response:**
  - **200 OK:** Authentication token.

### **Register User**
- **URL:** `/users/register`
- **Method:** `POST`
- **Description:** Register a new user.
- **Request Body:**
  - UserEntity object containing user details.
- **Response:**
  - **201 Created:** Details of the registered user.

### **Get Current User Profile**
- **URL:** `/users/me`
- **Method:** `GET`
- **Description:** Retrieve the current user's profile.
- **Response:**
  - **200 OK:** Current user details in JSON format.

### **Full Update User**
- **URL:** `/users/{id}`
- **Method:** `PUT`
- **Description:** Fully update user details.
- **Path Parameters:**
  - `id` (long): The ID of the user to update.
- **Request Body:**
  - UserDto object with updated details.
- **Response:**
  - **200 OK:** Updated user details in JSON format.

### **Partial Update User**
- **URL:** `/users/{id}`
- **Method:** `PATCH`
- **Description:** Partially update user details.
- **Path Parameters:**
  - `id` (long): The ID of the user to update.
- **Request Body:**
  - UserDto object with partial updates.
- **Response:**
  - **200 OK:** Updated user details in JSON format.

---

