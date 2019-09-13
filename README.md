# Teckers Android App

## About us and the project
### What is teckers?
Teckers is a school dedicated to girls and boys between nine and nineteen years old, to help them develop and strengthen the skills and knowledge necessary to create technological solutions to social problems in their community.

### What do we want do?
In this project we do not give the task of creating a functional mobile application for the android system. The android app of "teckers" plans to facilitate the handling and communication between students and teckers in a faster and more precise way.

## Technology
The app will contain different tools, such as messaging and attendance control management for students. We work with the version 1.0 (the first version) of the app, currently under development.

### Development
We work with different development technologies that you must acquire for the correct operation of the application, which are the following: 

| Technology        | Version           | Type  |
| ----------------- |:-----------------:| -----:|
| Android Studio    |        3.2.2      |  IDE  |
| Kotlin            |  1.3.30           |   Programming language |

#### Android Studio
Android Studio is the official integrated development environment (IDE) for Google's Android operating system, built on JetBrains' IntelliJ IDEA software and designed specifically for Android development. It is available for download on Windows, macOS and Linux based operating systems. It is a replacement for the Eclipse Android Development Tools (ADT) as the primary IDE for native Android application development. 

If you do not have the Android Studio IDE installed you can follow these steps:

##### Windows
Go to [https://developer.android.com/studio] and download the installation file.
If you download .exe file(recommended), double click on it to start it.
If you downloaded a .zip file, extract it and copy the android-studio folder to your Program Files folder. Next, open the android-studio> bin folder and then start studio64.exe(for 64-bit machines) or studio.exe (for 32-bit machines).
Follow the steps in the setup wizard in Android Studio and make sure you install the recommended SDK packages.

In the following video, each step of the setup procedure is shown when using the recommended `.exe` download. [https://youtu.be/1-k5KMj8IJQ]
##### MAC
Go to [https://developer.android.com/studio] and download the installation file.
Run the DMG file from Android Studio.
Drag and drop Android Studio into the "Applications" folder and then launch it.
Select if you want to import previous configuration options from Android Studio, and then click OK.
The Android Studio setup wizard will guide you through the rest of the setup, which includes downloading the Android SDK components that are needed for development.

The following video shows each step of the recommended configuration procedure. [https://youtu.be/MeSnxWv9T9I]
##### LINUX
Go to [https://developer.android.com/studio] and download the installation file.
Extract the .zip file you downloaded in a location suitable for your applications; for example, within /usr/local/ for your user profile or /opt/ for shared users.
To start Android Studio, open a terminal, navigate to the android-studio /bin/ directory and run studio.sh.
Select whether you want to import previous configuration options from Android Studio or not, and then click OK.
The Android Studio setup wizard will guide you through the rest of the setup, which includes downloading the Android SDK components that are needed for development.

The following video shows each step of the recommended configuration procedure. [https://youtu.be/7YNaxsUPwZc]
### Kotlin
Kotlin is a cross-platform, statically typed, general-purpose programming language with type inference. Kotlin is designed to interoperate fully with Java, and the JVM version of its standard library depends on the Java Class Library, but type inference allows its syntax to be more concise. Kotlin mainly targets the JVM, but also compiles to JavaScript or native code (via LLVM). Kotlin is sponsored by JetBrains and Google through the Kotlin Foundation. 

From Android Studio you can enable Kotlin like this:

1. Open the Settings window through the File Menu> Settings.
2. Enter the Plugins option.
3. Click on Browse repositories ...
4. Find the Kotlin plugin and click on the Install button.
5. We restart Android Studio.


### Android configuration
When you clone the project, you need to make some configurations before you can execute it ... those are:

1. Make sure your Android app satisfies these conditions:

* It is oriented to API level 16 (Jelly Bean) or higher.
* Use Gradle 4.1 or a more recent version.

2. Configure a device or emulator to run the app.
* Emulators must use an emulator image with Google Play.

3. Access Firebase with your Google Account. (For this step, you need a google firebase account and this may require asking administrators to give access to the firebase project).

4.  Add the Firebase Android configuration file to your app:

*  Click Download google-services.json to obtain your Firebase Android config file (google-services.json).
    *  You can download your [Firebase Android config file](https://support.google.com/firebase/answer/7015592) again at any time. 
    * Make sure the config file is not appended with additional characters, like (2).
* Move your config file into the module (app-level) directory of your app.

5.  To enable Firebase products in your app, add the [google-services plugin](https://developers.google.com/android/guides/google-services-plugin) to your Gradle files. 
*  In your root-level (project-level) Gradle file  `(build.gradle)` , add rules to include the Google Services plugin. Check that you have Google's Maven repository, as well.
 ```javascript
buildscript {

  repositories {
    // Check that you have the following line (if not, add it):
    google()  // Google's Maven repository
  }

  dependencies {
    // ...

    // Add the following line:
    classpath 'com.google.gms:google-services:4.3.2'  // Google Services plugin
  }
}

allprojects {
  // ...

  repositories {
    // Check that you have the following line (if not, add it):
    google()  // Google's Maven repository
    // ...
  }
}
```

* In your module (app-level) Gradle file (usually app/build.gradle), add a line to the bottom of the file.

```javascript
apply plugin: 'com.android.application'

android {
  // ...
}

// Add the following line to the bottom of the file:
apply plugin: 'com.google.gms.google-services'  // Google Play services Gradle plugin

```
6. Add Firebase SDKs to your app
* To your module (app-level) Gradle file `(usually app/build.gradle)`, add the dependencies for the Firebase products that you want to use in your app.

 ```javascript
dependencies {
  // ...

  // Add the Firebase SDK for Google Analytics
  implementation 'com.google.firebase:firebase-analytics:17.2.0'

  // Add the SDKs for any other Firebase products you want to use in your app
  // For example, to use Firebase Authentication and Cloud Firestore
  implementation 'com.google.firebase:firebase-auth:19.0.0'
  implementation 'com.google.firebase:firebase-firestore:21.1.0'

  // Getting a "Could not find" error? Make sure that you've added
  // Google's Maven repository to your root-level build.gradle file
}
```
* Sync your app to ensure that all dependencies have the necessary versions.
* If you added Analytics, run your app to send verification to Firebase that you've successfully integrated Firebase. Otherwise, you can skip the verification step.
* Your device logs will display the Firebase verification that initialization is complete. If you ran your app on an emulator that has network access, the Firebase console notifies you that your app connection is complete.

The following link shows in more detail the steps to follow: 
 [https://firebase.google.com/docs/android/setup#next_steps]
 
 
## Collaborators
* Ana I Morales Hunter 
* Silvia B Castillo Mejia 
* Ivan Esparza Bernal
