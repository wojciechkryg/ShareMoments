# ShareMoments
Android application which can be used to share photos with all users of the app.

Written in Kotlin with use of MVP.

Features:
* See photos from other users
* Take photos and apply filters on them
* Save taken photo
* Share taken photo to online gallery for other users
* See location of taken photo

## Screenshots

### Gallery
<p align="center">
  <img src="https://github.com/wojciechkryg/ShareMoments/blob/master/screenshots/01.png?raw=true" alt="01" style="width: 360px;"/>
</p>

### Photo details
<p align="center">
  <img src="https://github.com/wojciechkryg/ShareMoments/blob/master/screenshots/02.png?raw=true" alt="02" style="width: 360px;"/>
  <img src="https://github.com/wojciechkryg/ShareMoments/blob/master/screenshots/03.png?raw=true" alt="03" style="width: 360px;"/>
</p>

### Take a photo and apply filters
<p align="center">
  <img src="https://github.com/wojciechkryg/ShareMoments/blob/master/screenshots/04.png?raw=true" alt="04" style="width: 360px;"/>
  <img src="https://github.com/wojciechkryg/ShareMoments/blob/master/screenshots/05.png?raw=true" alt="05" style="width: 360px;"/>
  <img src="https://github.com/wojciechkryg/ShareMoments/blob/master/screenshots/06.png?raw=true" alt="06" style="width: 360px;"/>
  <img src="https://github.com/wojciechkryg/ShareMoments/blob/master/screenshots/07.png?raw=true" alt="07" style="width: 360px;"/>
</p>

## API URL and Google Maps API Token
To compile app you have to add your own Google Maps API Token.

Go to `[USER_HOME]/.gradle/gradle.properties`.
If you do not have `gradle.properties` file, you have to create one.

Then add the following lines:
```
ShareMomentsUrl="API_URL"
GOOGLE_MAPS_API_KEY=token
```
