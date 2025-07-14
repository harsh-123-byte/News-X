


# 📰 News X

**News X** is a powerful Android application built using Kotlin that delivers real-time news from all over the world. Users can search for news by keywords, view detailed articles, and save their favorite news for offline reading.

---

## 🚀 Features

- 🔍 Search news articles by keywords
- 🌍 Fetches real-time news globally
- ❤️ Save and manage favorite articles
- 📴 Offline access to saved/favorite news
- 📱 Built using MVVM architecture with clean separation of concerns

---

## 🛠️ Built With

- **Kotlin** – Primary language
- **MVVM Architecture** – For clean architecture and testability
- **Retrofit** – For REST API calls to NewsAPI
- **Room Database** – For offline local storage of favorites
- **Navigation Component** – Smooth screen-to-screen transitions
- **Fragments** – Modular and reusable UI components

---

## 📦 Installation

Follow these steps to run the app locally:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/NewsX.git
   ```

2. **Open the project in Android Studio**.

3. **Add your NewsAPI key**:
   - Register at [https://newsapi.org/](https://newsapi.org/) to get a free API key.
   - Open the file where the API key is defined (e.g., `Constants.kt` or inside Retrofit builder) and replace:
     ```kotlin
     const val API_KEY = "YOUR_API_KEY"
     ```

4. **Run the app** on a real device or emulator with internet access.

---

## 📸 Screenshots

<!-- Upload images to the `screenshots/` folder in your GitHub repo and reference them like below -->

<!--
![Home Screen](screenshots/home.png)
![Search Feature](screenshots/search.png)
![Favorites Section](screenshots/favorites.png)
-->

---

## 🔗 API Used

- [NewsAPI.org](https://newsapi.org/)  
  A REST API for real-time news data, allowing keyword-based search and top headlines.

> Note: You must generate your own API key from [newsapi.org](https://newsapi.org/) and insert it into the project.

---

## 📁 Project Structure

```
NewsX/
│
├── data/               # Data layer (Room DB, API Service)
│   ├── api/            # Retrofit interface
│   └── db/             # Room database and DAO
│
├── ui/                 # Fragments for UI screens
│
├── viewmodel/          # ViewModels to manage UI-related data
│
├── repository/         # Repository pattern to manage data sources
│
├── utils/              # Utility classes and helpers
│
└── MainActivity.kt     # Host activity for Navigation Component
```

---

## 📌 Requirements

- Android Studio Giraffe or newer
- Android SDK version 33 or above
- Internet connection for fetching news
- A valid News API key from [https://newsapi.org](https://newsapi.org)

---

## 👤 Author

**Kumar Harsh**  
GitHub: [@harsh-123-byte](https://github.com/harsh-123-byte)  
Email: harshsrivastava4002@gmail.com




