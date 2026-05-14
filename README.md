<div align="center">

<h1>KAVYA-KANAJA</h1>

<h3><i>Duolingo for Kannada Literature</i></h3>

<p>
An Android educational application designed to preserve, promote, and simplify Kannada literary heritage through interactive digital learning.
</p>

</div>

<hr>

<h2>PROJECT OVERVIEW</h2>

<p>
Kavya-Kanaja is a modern Android application developed using Kotlin and Jetpack Compose to make Kannada literature accessible for students and literature enthusiasts. The application allows users to read Kannada poems, understand difficult words through Bhavartha explanations, listen to poem recitations, explore poet biographies, and use AI-generated explanations for deeper understanding.
</p>

<hr>

<h2>PROBLEM STATEMENT</h2>

<p>
Kannada literature has a rich cultural history spanning thousands of years, but modern learners often face difficulty in understanding classical poetry due to language complexity and limited digital learning resources.
</p>

<p>
This project bridges traditional literature and modern technology by creating an engaging mobile learning platform.
</p>

<hr>

<h2>FEATURES</h2>

<ul>
<li>Browse 50+ Kannada poems</li>
<li>Offline poem and poet data storage using JSON assets</li>
<li>Bhavartha word meaning support</li>
<li>Poet biography section</li>
<li>Audio poem playback</li>
<li>Favorites/bookmark functionality</li>
<li>AI-based poem explanation</li>
<li>Modern clean user interface using Jetpack Compose</li>
</ul>

<hr>

<h2>TECH STACK</h2>

<table>
<tr>
<th>Technology</th>
<th>Purpose</th>
</tr>

<tr>
<td>Kotlin</td>
<td>Android application development</td>
</tr>

<tr>
<td>Jetpack Compose</td>
<td>UI development</td>
</tr>

<tr>
<td>MVVM Architecture</td>
<td>Application architecture</td>
</tr>

<tr>
<td>Repository Pattern</td>
<td>Data handling</td>
</tr>

<tr>
<td>JSON Assets</td>
<td>Offline poem and poet storage</td>
</tr>

<tr>
<td>MediaPlayer</td>
<td>Audio playback</td>
</tr>

<tr>
<td>Gemini API</td>
<td>AI explanation support</td>
</tr>

</table>

<hr>

<h2>PROJECT STRUCTURE</h2>

<pre>
KavyaKanaja/
│
├── app/
│   ├── src/
│   │   ├── androidTest/
│   │   ├── main/
│   │   │   ├── assets/
│   │   │   │   ├── poems.json
│   │   │   │   └── poets.json
│   │   │   │
│   │   │   ├── java/com.example.kavyakanaja/
│   │   │   │   ├── data/
│   │   │   │   │   ├── model/
│   │   │   │   │   │   ├── Poem.kt
│   │   │   │   │   │   └── Poet.kt
│   │   │   │   │
│   │   │   │   ├── repository/
│   │   │   │   │
│   │   │   │   ├── ui/
│   │   │   │   │   ├── components/
│   │   │   │   │   ├── screens/
│   │   │   │   │   │   ├── PoemScreen.kt
│   │   │   │   │   │   └── PoetScreen.kt
│   │   │   │   │   └── theme/
│   │   │   │   │       ├── Color.kt
│   │   │   │   │       ├── Theme.kt
│   │   │   │   │       └── Type.kt
│   │   │   │   │
│   │   │   │   ├── viewmodel/
│   │   │   │   └── MainActivity.kt
│   │   │   │
│   │   │   ├── res/
│   │   │   └── AndroidManifest.xml
│   │
│   ├── test/
│   ├── build.gradle.kts
│   └── proguard-rules.pro
│
├── gradle/
├── build.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
└── settings.gradle.kts
</pre>

<hr>

<h2>APPLICATION WORKFLOW</h2>

<ol>
<li>Launch the Android application</li>
<li>Browse available Kannada poems</li>
<li>Select and read a poem</li>
<li>Tap difficult words for meaning support</li>
<li>Listen to poem recitation audio</li>
<li>Explore poet biographies</li>
<li>Save favorite poems</li>
<li>Use AI explanation for detailed understanding</li>
</ol>

<hr>

<h2>INSTALLATION</h2>

<pre>
git clone https://github.com/PSS004/KavyaKanaja.git
</pre>

<p>
Open the project in Android Studio and run it on an emulator or physical Android device.
</p>

<hr>

<h2>ARCHITECTURE</h2>

<p>
The application follows the MVVM (Model-View-ViewModel) architecture pattern with Repository Pattern for clean code organization and maintainability.
</p>

<ul>
<li>Model Layer → Poem.kt, Poet.kt</li>
<li>Repository Layer → Data access and content handling</li>
<li>ViewModel Layer → Business logic management</li>
<li>UI Layer → Jetpack Compose screens and components</li>
</ul>

<hr>

<h2>DEVELOPER</h2>

<p>
<b>Prasad S Sannamani</b>
</p>

<p>
GitHub:
<a href="https://github.com/PSS004">https://github.com/PSS004</a>
</p>

<hr>

<div align="center">
<h3>Kannada Literature Meets Modern Technology</h3>
</div>
