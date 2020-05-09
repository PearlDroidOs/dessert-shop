# Dessert shop
   This project is used to answer an Ascend Commerce Mobile Developer exam

# How to run the application
```
1. Download Zip File
2. Unzip and open it on Android Studio
3. Click Run on AVD or your device
```

# How to run test of the application

Unit test is only on package Unit test which is 'com.pearldroidos.dessertshop (test)'
```
1. Go to 'app'
2. Go to 'java'
3. Go to 'com.pearldroidos.dessertshop (test)' --> right click then Run 'Tests in dessertshop'
```

# External Implementation in Gardle
```
def nav_version = "2.3.0-alpha06"
    implementation "androidx.cardview:cardview:1.0.0"
    implementation "androidx.recyclerview:recyclerview:1.1.0"
    implementation 'com.google.code.gson:gson:2.8.5'
    implementation "android.arch.navigation:navigation-fragment-ktx:$nav_version"
    implementation "android.arch.navigation:navigation-ui-ktx:$nav_version"
    //Mockito framework
    testImplementation 'org.mockito:mockito-core:1.10.19'
```

# Tutorial UI of the project

Tutorial 1: Loading data

<img src="https://github.com/PearlDroidOs/dessert-shop/blob/master/app/sampledata/tu_1.png" width="40%" height="40%">

Tutorial 2: Data list views

<img src="https://github.com/PearlDroidOs/dessert-shop/blob/master/app/sampledata/tu_2.png" width="40%" height="40%">

Tutorial 3: Data Detail views

<img src="https://github.com/PearlDroidOs/dessert-shop/blob/master/app/sampledata/tu_3.png" width="40%" height="40%">

Tutorial 4: Data Detail views in unload image case

<img src="https://github.com/PearlDroidOs/dessert-shop/blob/master/app/sampledata/tu_4.png" width="40%" height="40%">

Tutorial 5: Internet handle with dialog

<img src="https://github.com/PearlDroidOs/dessert-shop/blob/master/app/sampledata/tu_5.png" width="40%" height="40%">

Tutorial 6: Refresh views

<img src="https://github.com/PearlDroidOs/dessert-shop/blob/master/app/sampledata/tu_6.png" width="40%" height="40%">

# Project pattern
  
  Developer used one activity and 2 fragments which has a project structure as below
  
  ```
  MainActivity
     |____MainFragment -- MainPresenter -- [ Model | LoadInformationService | LoadImageService ]
            |
            |
            |___DessertDetailsFragment -- DessertDetailsPresenter -- Model
  ```

<img src="https://github.com/PearlDroidOs/dessert-shop/blob/master/app/sampledata/tu_7.png" width="40%" height="40%">
