# ScottishPower Android Tech Test

This project is my implementation of the ScottishPower Android technical exercise.

It’s a small Kotlin + Compose app that fetches posts from the JSONPlaceholder API, displays them in a list, and allows navigating to a details screen.

## Tech used
- Kotlin
- Jetpack Compose
- ViewModel
- Coroutines
- Retrofit
- Hilt

## Architecture (planned)
The app is intended to follow a simple layered MVVM approach:

- **UI layer (Compose)**  
  Screens observe state from ViewModels and render UI accordingly.
- **ViewModel layer**  
  Holds UI state and coordinates data loading.
- **Data layer**  
  Repository pattern backed by a Retrofit API service.

This keeps concerns separated and makes the core logic easier to test.

## Testing
Basic unit tests are included for the repository and ViewModel to cover:
- Successful data loading
- Error handling
- Simple edge cases

## If I had more time
I would consider adding UI tests, improving error and loading states in the UI, and introducing a separate domain model if the app were to grow beyond a single feature.
