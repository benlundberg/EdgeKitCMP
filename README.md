# EdgeKitCMP

**EdgeKitCMP** is a modern cross-platform mobile application built with **Kotlin Multiplatform (KMP)** and **Compose Multiplatform**, showcasing production-grade architecture, state management, dependency injection, and local data persistence across **Android** and **iOS**.

---

## 🚀 Key Features

- **Kotlin Multiplatform (KMP):** Code sharing across Android and iOS platforms with clean separation of concerns.
- **Compose Multiplatform & Material 3:** Declarative UI built with Jetpack Compose, Material 3 theming, and Phosphor Icons.
- **Unidirectional Data Flow (UDF):** Powered by a robust [`BaseViewModel`](file:///Users/benjaminlundberg/Projects/Android/EdgeKitCMP/shared/src/commonMain/kotlin/com/app/edgekitcmp/core/ui/BaseViewModel.kt) supporting atomic state updates (`StateFlow`) and one-time UI events (navigation, snackbars).
- **Dependency Injection:** Integrated via **Koin** (`koin-compose-viewmodel`) for seamless multiplatform dependency resolution.
- **Local Persistence:** Type-safe local data persistence using **Multiplatform Settings** and **Kotlinx Serialization**.
- **Modular Architecture:** Organized into feature modules (`app`, `home`, `settings`) with clean domain, data, and presentation layers.

---

## 🛠️ Tech Stack & Libraries

- **Language:** [Kotlin 2.4](https://kotlinlang.org/)
- **UI Framework:** [Compose Multiplatform](https://www.jetbrains.com/compose-multiplatform/) & Material 3
- **Lifecycle & ViewModel:** Jetpack Lifecycle (`lifecycle-viewmodel-compose`, `lifecycle-runtime-compose`)
- **Navigation:** Jetpack Navigation Compose
- **Dependency Injection:** [Koin](https://insert-koin.io/)
- **Persistence:** [Multiplatform Settings](https://github.com/russhwolf/multiplatform-settings) & Kotlinx Serialization
- **Concurrency:** Kotlin Coroutines & `StateFlow`

---

## 📂 Project Structure

```tree
EdgeKitCMP/
├── androidApp/          # Android application module & entry point
├── iosApp/              # iOS application module & SwiftUI entry point
└── shared/              # Shared Kotlin Multiplatform module
    └── src/
        ├── commonMain/  # Cross-platform business logic, UI, ViewModels, DI
        ├── commonTest/  # Shared unit tests (ViewModels, Data Sources)
        ├── androidMain/ # Android-specific implementations & tooling
        └── iosMain/     # iOS-specific implementations
```

---

## 🏃 Getting Started & Running the Apps

### Prerequisites
- **Android Studio** (Koala or newer) with Kotlin Multiplatform plugin.
- **Xcode** (for running the iOS application).
- JDK 17 or higher.

### Running Android
```bash
./gradlew :androidApp:assembleDebug
```
Or run directly from Android Studio using the `androidApp` run configuration.

### Running iOS
1. Open the [`iosApp`](./iosApp) directory in Xcode.
2. Select your target simulator and press **Run** (`Cmd + R`).

---

## 🧪 Running Tests

Run shared multiplatform unit tests via Gradle:

- **Android Host Tests:**
  ```bash
  ./gradlew :shared:testAndroidHostTest
  ```
- **iOS Simulator Tests:**
  ```bash
  ./gradlew :shared:iosSimulatorArm64Test
  ```

---

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.
