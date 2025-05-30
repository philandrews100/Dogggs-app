# 🐶 Dogggs App

Dogggs is an Android application built with **Jetpack Compose**, **Koin**, and a **Clean Architecture** approach. It uses the [Dog CEO API](https://dog.ceo/dog-api/) to fetch and display dog breeds and breed-specific images in a responsive, grid UI.

## ✨ Features

- Browse a list of all dog breeds.
- Search breeds using the search bar.
- View breed images in a responsive grid.
- Click any image to view a full picture.
- Smooth navigation between screens with **Jetpack Navigation**.
- Adaptive **dark/light theme** support with preview annotations.
- View state management with **Kotlin Flows**.
- Error handling with retry support.
- Fully tested ViewModels, Use Cases and Repository.

## 🧱 Architecture

This app follows a **Clean Architecture** structure:

```
com.p.andrews
├── core
│   ├── domain
│   │   ├── model
│   │   ├── repository
│   │   └── usecase
│   └── ui
├── data
│   └── remote (API service)
├── di (Koin modules)
├── feature
│   ├── list (breed list screen + ViewModel)
│   └── image (breed image screen + ViewModel)
└── dogggs (Main app + nav graph)
```

- **Domain Layer**: Use cases and data models.
- **Data Layer**: Remote API access (`DogApiService`) and repository implementation (`DogRepositoryImpl`).
- **Presentation Layer**: Composables and ViewModels using `StateFlow`.

## 🛠 Tech Stack

- **Kotlin + Jetpack Compose**
- **Koin** (DI)
- **Retrofit** (Networking)
- **Coil** (Image Loading)
- **Kotlin Coroutines + Flows**
- **Jetpack Navigation Compose**
- **Material Design Components**
- **JUnit + Compose Test + Mockk** (Testing)

## 🔑 API

- This app uses the [Dog CEO API](https://dog.ceo/dog-api/), which is free and doesn't require authentication.

Endpoints used:
- `GET /breeds/list/all` — list all breeds
- `GET /breed/{breed}/images/random/10` — get 10 images for a breed
- `GET /breed/{breed}/images/random` — get a single image

## 🧪 Unit Tests

- ViewModels, Use Cases, and Repositories are covered with `JUnit` and `Mockk`.

## 🖼 Previews

All composables are previewed with support for:
- Light/Dark theme using `@MultiThemePreview`
- Custom `AppTheme`


## 👨‍💻 Author
**Philip Andrews** 