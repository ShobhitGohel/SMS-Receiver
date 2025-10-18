# SMS Alert App 📱

A real-time SMS monitoring Android application built with Kotlin that alerts users when new SMS messages are received.

## 📋 Table of Contents

- [Features](#features)
- [Screenshots](#screenshots)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Architecture](#architecture)
- [Code Structure](#code-structure)
- [Permissions](#permissions)
- [API Reference](#api-reference)
- [Testing](#testing)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [License](#license)

## ✨ Features

- **Real-time SMS Monitoring**: Automatically detects incoming SMS messages
- **Instant Alerts**: Shows immediate notifications for new messages
- **Background Operation**: Works even when the app is closed
- **Permission Management**: Handles SMS permissions gracefully
- **Clean UI**: Simple, user-friendly interface
- **Toast Notifications**: Brief system notifications for SMS alerts
- **Dialog Alerts**: Detailed message display with sender information

## 📱 Screenshots

```
┌─────────────────────────┐
│     SMS Alert App       │
│                         │
│  Waiting for incoming   │
│     messages...         │
│                         │
│  [📩 New Message Alert] │
│  From: +1234567890      │
│  Message: Hello World!  │
│  [OK]                   │
└─────────────────────────┘
```

## 🔧 Prerequisites

- **Android Studio**: Arctic Fox (2020.3.1) or later
- **Android SDK**: API Level 24 (Android 7.0) or higher
- **Kotlin**: Version 1.9.0 or later
- **Gradle**: Version 8.1.4 or later
- **Target Device**: Android 7.0+ with SMS capability

## 📦 Installation

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/sms-alert-app.git
cd sms-alert-app
```

### 2. Open in Android Studio
1. Launch Android Studio
2. Select "Open an existing project"
3. Navigate to the project directory
4. Click "OK"

### 3. Build the Project
```bash
./gradlew build
```

### 4. Install on Device
```bash
./gradlew installDebug
```

## 🚀 Usage

### Initial Setup
1. **Launch the App**: Open SMS Alert App from your device
2. **Grant Permissions**: Allow SMS permissions when prompted
3. **Start Monitoring**: The app will automatically start monitoring SMS

### Receiving SMS Alerts
1. **Automatic Detection**: App detects incoming SMS messages
2. **Toast Notification**: Brief system notification appears
3. **Alert Dialog**: Detailed message display with sender info
4. **Background Operation**: Works even when app is minimized

### Permission Requirements
The app requires the following permissions:
- `RECEIVE_SMS`: To intercept incoming SMS messages
- `READ_SMS`: To read SMS content from the device

## 🏗️ Architecture

### System Architecture
```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   SMS System    │───▶│  SmsReceiver    │───▶│  MainActivity   │
│                 │    │ (Broadcast)     │    │   (UI Layer)    │
└─────────────────┘    └─────────────────┘    └─────────────────┘
                                │
                                ▼
                        ┌─────────────────┐
                        │   AlertDialog   │
                        │  (User Alert)   │
                        └─────────────────┘
```

### Component Flow
1. **SMS Received** → System broadcasts `SMS_RECEIVED` intent
2. **SmsReceiver** → Intercepts broadcast and parses SMS data
3. **Permission Check** → Verifies SMS permissions
4. **Alert Display** → Shows notification and dialog
5. **Activity Launch** → Opens MainActivity if not already open

## 📁 Code Structure

```
app/
├── src/main/
│   ├── java/com/shobhu/assignment9/
│   │   ├── MainActivity.kt          # Main UI component
│   │   └── SmsReceiver.kt           # SMS broadcast receiver
│   ├── res/
│   │   ├── layout/
│   │   │   └── activity_main.xml    # Main activity layout
│   │   ├── values/
│   │   │   ├── strings.xml          # String resources
│   │   │   ├── colors.xml           # Color definitions
│   │   │   └── themes.xml           # App themes
│   │   └── drawable/                # Drawable resources
│   └── AndroidManifest.xml          # App configuration
├── build.gradle.kts                 # App-level build config
└── proguard-rules.pro              # ProGuard configuration
```

### Key Files Explained

#### `MainActivity.kt`
- **Purpose**: Main UI component and permission handler
- **Key Methods**:
  - `onCreate()`: Initializes activity and checks permissions
  - `checkSmsPermission()`: Requests SMS permissions
  - `showAlert()`: Displays SMS alert dialog

#### `SmsReceiver.kt`
- **Purpose**: Background SMS monitoring service
- **Key Methods**:
  - `onReceive()`: Handles incoming SMS broadcasts
  - SMS parsing and data extraction
  - Activity launching logic

#### `activity_main.xml`
- **Purpose**: Main activity layout
- **Components**: Simple LinearLayout with status TextView

## 🔐 Permissions

### Required Permissions
```xml
<uses-permission android:name="android.permission.RECEIVE_SMS" />
<uses-permission android:name="android.permission.READ_SMS" />
```

### Permission Handling
- **Runtime Requests**: Permissions requested when app starts
- **User-Friendly**: Clear permission request dialogs
- **Graceful Degradation**: App handles permission denials

### Security Considerations
- **Minimal Permissions**: Only requests necessary SMS permissions
- **No Data Storage**: Does not store SMS data permanently
- **Local Processing**: All SMS processing happens locally

## 📚 API Reference

### MainActivity Class
```kotlin
class MainActivity : AppCompatActivity() {
    private val SMS_PERMISSION_CODE = 101
    
    override fun onCreate(savedInstanceState: Bundle?)
    private fun checkSmsPermission()
    fun showAlert(sender: String, message: String)
}
```

### SmsReceiver Class
```kotlin
class SmsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent)
}
```

### Key Constants
- `SMS_PERMISSION_CODE`: Permission request identifier (101)
- `SMS_RECEIVED`: Broadcast action for incoming SMS
- `FLAG_ACTIVITY_NEW_TASK`: Intent flag for activity launching

## 🧪 Testing

### Unit Tests
```bash
./gradlew test
```

### Instrumented Tests
```bash
./gradlew connectedAndroidTest
```

### Test Coverage
- **ExampleUnitTest**: Basic unit test example
- **ExampleInstrumentedTest**: Android device testing

### Manual Testing
1. **Permission Testing**: Verify permission requests work
2. **SMS Testing**: Send test SMS to device
3. **Background Testing**: Test app behavior when closed
4. **UI Testing**: Verify alert dialogs display correctly

## 🔧 Troubleshooting

### Common Issues

#### Permission Denied
**Problem**: App doesn't receive SMS alerts
**Solution**: 
1. Go to Settings > Apps > SMS Alert App > Permissions
2. Enable SMS permissions manually

#### App Not Receiving SMS
**Problem**: SMS messages not triggering alerts
**Solution**:
1. Check if SMS permissions are granted
2. Verify app is not in battery optimization
3. Restart the app

#### Alert Not Showing
**Problem**: SMS received but no alert displayed
**Solution**:
1. Check if notifications are enabled
2. Verify app is not in Do Not Disturb mode
3. Check device notification settings

### Debug Information
```bash
# Enable debug logging
adb shell setprop log.tag.SmsReceiver VERBOSE
```

## 🚀 Performance Considerations

### Memory Usage
- **Minimal Footprint**: Lightweight app with minimal memory usage
- **Efficient Processing**: Quick SMS parsing and alert display
- **Background Optimization**: Optimized for background operation

### Battery Usage
- **Low Impact**: Minimal battery drain
- **Event-Driven**: Only processes SMS when received
- **No Polling**: No continuous background processes

## 🔄 Future Enhancements

### Planned Features
- [ ] **Message Filtering**: Filter SMS by sender or keywords
- [ ] **Custom Alerts**: Customizable alert sounds and vibrations
- [ ] **Message History**: Store and display SMS history
- [ ] **Multiple Recipients**: Support for multiple phone numbers
- [ ] **Scheduled Alerts**: Time-based alert scheduling

### Technical Improvements
- [ ] **Database Integration**: SQLite for message storage
- [ ] **Notification Channels**: Android notification channels
- [ ] **Material Design 3**: Updated UI components
- [ ] **Accessibility**: Screen reader support

## 🤝 Contributing

### How to Contribute
1. **Fork the Repository**: Create your own fork
2. **Create Feature Branch**: `git checkout -b feature/amazing-feature`
3. **Commit Changes**: `git commit -m 'Add amazing feature'`
4. **Push to Branch**: `git push origin feature/amazing-feature`
5. **Open Pull Request**: Submit your changes for review

### Development Guidelines
- **Code Style**: Follow Kotlin coding conventions
- **Documentation**: Add comments for complex logic
- **Testing**: Include tests for new features
- **Performance**: Optimize for minimal resource usage

### Issue Reporting
- **Bug Reports**: Use GitHub Issues with detailed descriptions
- **Feature Requests**: Describe the feature and its benefits
- **Security Issues**: Report privately to maintainers

## 📞 Support

### Getting Help
- **Documentation**: Check this README for common issues
- **GitHub Issues**: Open an issue for bugs or feature requests
- **Email**: Contact the maintainers for support

### Community
- **Discussions**: Use GitHub Discussions for questions
- **Contributing**: See the Contributing section above
- **Code of Conduct**: Please be respectful and inclusive

## 🏆 Acknowledgments

- **Android Team**: For the excellent Android platform
- **Kotlin Team**: For the powerful Kotlin language
- **Material Design**: For the beautiful design system
- **Open Source Community**: For inspiration and support

---

**Made with ❤️ for the Android community**

*Last updated: December 2024*
