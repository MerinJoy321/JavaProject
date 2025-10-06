# Smart Campus Navigation & Notification System - Setup Guide

## Prerequisites
- Java 17 or higher (check with `java -version`)
- Git (for cloning the repository)

## Project Structure
- `src/`: Source code
- `bin/`: Compiled classes (generated)
- `javafx-sdk-temp/javafx-sdk-24.0.2/`: JavaFX SDK (included)
- `h2.jar`: H2 database JAR (included)
- `launch.bat`: Script to compile and run the app

## Setup Steps

1. **Clone the Repository**
   ```
   git clone https://github.com/MerinJoy321/JavaProject.git
   cd JavaProject
   ```

2. **Checkout the `merin` Branch**
   ```
   git checkout merin
   ```

3. **Ensure Dependencies are Present**
   - The project includes `javafx-sdk-temp/javafx-sdk-24.0.2/` and `h2.jar`
   - If missing, download JavaFX SDK 24.0.2 and place it in `javafx-sdk-temp/javafx-sdk-24.0.2/`
   - Download H2 database JAR and place it as `h2.jar`

4. **Compile and Run**
   - Double-click `launch.bat` (Windows)
   - Or run in command prompt: `launch.bat`

   This will:
   - Compile the Java sources
   - Launch the JavaFX application

## Manual Commands (if needed)
- Compile: `javac --module-path "javafx-sdk-temp/javafx-sdk-24.0.2/lib" --add-modules javafx.controls,javafx.fxml -cp "h2.jar" -d bin -sourcepath src src/com/smartcampus/app/SmartCampusApp.java`
- Run: `java --module-path "javafx-sdk-temp/javafx-sdk-24.0.2/lib" --add-modules javafx.controls,javafx.fxml -cp "bin;h2.jar" com.smartcampus.app.SmartCampusApp`

## Features
- Login/Signup with role-based access (Student, Faculty, Admin)
- Campus map navigation
- Event geotagging and notifications
- Database integration with H2

## Troubleshooting
- Ensure JavaFX SDK path is correct
- Check Java version compatibility
- If compilation fails, ensure all source files are present
