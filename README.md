# Simple KMP app to make some notes.
**Livil Assessment task**

## Table of Contents
 - Features
- Build and Run instructions
  - Android
  - Desktop
  - iOS
- Architecture Overview
- Dependencies
- UI/UX
  - Notes
  - Note Editor
  - Video Demo

## Features.
  - The list of notes.
  - Note editor.
  - Persistence storage of notes.

## Build and Run instructions

### Android
To build an Android apk file, open the project root folder and enter this command in terminal:

```.\gradlew assembleDebug``` or ```gradlew assembleDebug``` if you use **Windows** machine.

```./gradlew assembleDebug``` if you use it On **Mac**, **Linux**

When build will be completed you can find the apk file in the folder:

```project_folder\composeApp\build\outputs\apk\debug```

Copy apk to the smartphone/emulator and install it.

### Desktop
To build a desktop app open the project root folder and enter this command in terminal:

```gradlew package``` or ```.\gradlew package```  if you use **Windows** machine.

```./gradlew package``` if you use it On **Mac**, **Linux**

Then check the folder:
 ```project_folder\composeApp\build\compose\binaries\main``` you will have ```msi/deb/dmg``` folder with desktop app.
Exact name of the folder depends on the OS you are running the task on.

### iOS
Since I use a laptop under Windows OS, I was not even able to check iOS app compilation. I can't provide any instructions on how to build it.
Probably, it needs some additional setup for Realm or Koin libraries in iOS flavor.

## Architecture Overview
I used simple MVVM architecture in my project. Since there is no business logic, there is no domain package with use cases.
I used an interface for the repository to have an ability to use different implementations of the repo in the future.
There are 2 default modules:
- composeApp.
- shared.
  
Since I don't have something to share between platforms(I have all the codebase including UI in common flavor) I have not extracted additional modules.
There is a list of additional reasons why I did not extract modules:
- The only one person is working on the project and there are no parallel features to divide it into feature modules.
- The project is too small to have some significant advantages with compilation time.
- Time was very limited for such a task.

## Dependencies
In my app I used only cross platform libraries to unify the code for all three platforms.

[Compose Multiplatform](https://www.jetbrains.com/compose-multiplatform)
Declarative framework for sharing UIs across multiple platforms. Based on Kotlin Multiplatform and Jetpack Compose.

[Koin](https://insert-koin.io) library for Dependency Injection.

[Realm](https://github.com/realm/realm-kotlin) database for persistent storage.

[Voyager](https://voyager.adriel.cafe) for Navigation and state management.

[kotlinx-datetime](https://github.com/Kotlin/kotlinx-datetime) to work with date format.

All the libraries are multiplatform and support at least Android, iOS and JVM(Desktop).

## UI/UX
The app has 2 screens: Notes and Note Editor.

### Notes Screen
Contains Top bar, list of notes and floating action button.
To add new notes the user should tap on the floating action button.
To See/Edit a note from the list user should tap on the note item.
<div align="center">
<img src="https://github.com/user-attachments/assets/ef70e6ac-eb4c-412e-8e6b-8a12a02858de" width="300" height="650" />
&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://github.com/user-attachments/assets/a53c78d8-ff51-4dc5-9406-0e9000763910" width="600" height="450" />
</div>

### Note Editor
Contains Top bar with navigation button, 2 input fields **Title** and **Description**, and **Save** button.
If the user taps on the navigation button, it redirects them to the Notes screen.
If the user taps the **Save** button it saves/updates the note and then redirects the user to Notes screen.
<div align="center">
<img src="https://github.com/user-attachments/assets/9ccca761-c785-4874-a980-811c4504fbd5" width="300" height="650" />
&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://github.com/user-attachments/assets/58ed150d-28f2-4a3f-a759-6ce64be8214f" width="600" height="450" />
</div>

### Video Demo
[demo.webm](https://github.com/user-attachments/assets/db3cfef4-ed39-449d-99dd-9bd462363ff5)

