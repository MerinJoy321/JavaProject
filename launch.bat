@echo off
echo Compiling Java sources...
javac --module-path "javafx-sdk-temp/javafx-sdk-24.0.2/lib" --add-modules javafx.controls,javafx.fxml -cp "h2.jar" -d bin -sourcepath src src/com/smartcampus/app/SmartCampusApp.java

if %errorlevel% neq 0 (
    echo Compilation failed.
    pause
    exit /b 1
)

echo Compilation successful.
echo Launching the application...
java --module-path "javafx-sdk-temp/javafx-sdk-24.0.2/lib" --add-modules javafx.controls,javafx.fxml -cp "bin;h2.jar" com.smartcampus.app.SmartCampusApp
