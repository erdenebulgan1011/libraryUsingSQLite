Here’s an improved and complete `README.md` for your **libraryUsingSQLite** Android project — written professionally and fully aligned with your Java files (`MainActivity`, `DetailsActivity`, `DetailsItemActivity`, and `DbHandler`).

You can copy this directly into your GitHub repo root.

---

```markdown
# 📚 LibraryUsingSQLite

**LibraryUsingSQLite** is an Android application built using **Java** and **SQLite** that demonstrates how to manage a simple local database.  
Users can add, view, update, and delete book records — each containing a **name**, **author**, and **date** — while also receiving a notification upon adding a new entry.

---

## ✨ Features

- ➕ **Add new books** to the local SQLite database  
- 📋 **View all stored books** in a ListView  
- ✏️ **Edit or update** existing book details  
- ❌ **Delete** selected books  
- 🔔 **Notifications** triggered when new data is added  
- 📱 **User-friendly interface** with simple navigation between screens  

---

## 🧩 App Structure
app/
├── java/com/example/lab10_2/
│   ├── MainActivity.java           # Entry point – handles input and saving book data
│   ├── DetailsActivity.java        # Displays all stored books in a ListView
│   ├── DetailsItemActivity.java    # Shows selected book and allows update/delete
│   ├── DbHandler.java              # SQLiteOpenHelper class (CRUD logic)
│   └── model/Book.java             # Optional model class for data handling
│
├── res/layout/
│   ├── activity_main.xml           # Main UI for adding new books
│   ├── details.xml                 # Displays all books
│   ├── details_layout.xml          # UI for editing or deleting a specific book
│   ├── list_row.xml                # Template for each book row
│
├── AndroidManifest.xml
└── build.gradle


## 🧱 Key Classes

### 🏠 `MainActivity.java`
- Accepts user input (book name, author, and date).  
- Saves new entries to the SQLite database via `DbHandler`.  
- Displays a toast message and triggers a notification when a book is added.  
- Navigates to the **DetailsActivity** to view stored records.

### 📖 `DetailsActivity.java`
- Retrieves all book records from the database using `DbHandler.GetUsers()`.  
- Displays them in a **ListView** via a `SimpleAdapter`.  
- Opens **DetailsItemActivity** when an item is clicked.

### ✏️ `DetailsItemActivity.java`
- Receives book details via `Intent`.  
- Allows users to:
  - **Update** author or date information.  
  - **Delete** a selected book.  
- Updates the local database using `DbHandler.updateBook()` and `DbHandler.deleteBook()`.

### 💾 `DbHandler.java`
- Extends `SQLiteOpenHelper`.  
- Handles database creation, upgrades, and all CRUD operations:
  - `insertBookDetails(String name, String author, String date)`
  - `GetUsers()` – returns all stored records
  - `updateBook(String name, String newAuthor, String newDate)`
  - `deleteBook(String name)`

---

## 🧰 Technologies Used

| Component | Description |
|------------|-------------|
| **Language** | Java |
| **Database** | SQLite |
| **IDE** | Android Studio |
| **Min SDK** | 24 |
| **Target SDK** | 33 |
| **Build System** | Gradle |
| **UI Libraries** | AndroidX, Material Design |
| **Notification API** | Android NotificationManager |

---

## ⚙️ Setup and Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/erdenebulgan1011/libraryUsingSQLite.git
````

2. **Open in Android Studio:**

   * Select **File → Open** → choose the cloned folder.

3. **Sync Gradle:**

   * Wait for dependencies to finish downloading.

4. **Run the app:**

   * Use an emulator or connect a real Android device.
   * Click **Run ▶️**.

---

## 🧪 How It Works

1. On the **main screen**, enter:

   * Book name
   * Author
   * Date

2. Click **Save** →

   * Data is inserted into the SQLite database.
   * A system notification appears confirming the addition.

3. Click **View** →

   * Displays all books in a list (DetailsActivity).

4. Tap any book →

   * Opens **DetailsItemActivity**.
   * You can update or delete the record.

---

## 📱 User Interface Overview

| Screen                  | Description                                                    |
| ----------------------- | -------------------------------------------------------------- |
| **Main Screen**         | Input fields for name, author, date, and “Save”/“View” buttons |
| **Details Screen**      | List of stored books from SQLite database                      |
| **Details Item Screen** | Selected book info, with update and delete options             |

---

## 🧠 Learning Outcomes

This project demonstrates:

* SQLite database integration in Android
* Performing CRUD operations via `SQLiteOpenHelper`
* Passing data between activities using `Intent` and `Bundle`
* Displaying data in a `ListView` using `SimpleAdapter`
* Using Android’s `NotificationChannel` and `NotificationManager`

---

## 🧾 License

This project was created for educational purposes as part of the **Лабораторийн ажил 10 – Өгөгдлийн сантай ажиллах** coursework.
You may use or modify it for your own learning.

---

## 👨‍💻 Author

**Ж. Эрдэнэбулган**
Software Engineering Student
[GitHub Profile](https://github.com/erdenebulgan1011)
