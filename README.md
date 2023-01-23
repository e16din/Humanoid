# Humanoid

Humanoid is handy logger to debug with links (look like at exception stacktrace)

## Using Humanoid

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    "Try to use and give me feedback :)".logH()
}
```

![slide_1](https://github.com/e16din/Humanoid/blob/master/README_slide_1.jpg)

## Test drive video

<div align="left">
      <a href="https://www.youtube.com/watch?v=65G61hYqiUk">
         <img src="https://img.youtube.com/vi/65G61hYqiUk/0.jpg" style="width:100%;">
      </a>
</div>


## Implementation

```groovy
// settings.gradle:

dependencyResolutionManagement {
    repositories {
        // ..
        maven { url 'https://jitpack.io' }
    }
}
```

```groovy
// build.gradle:

dependencies {
    // ..
    implementation 'com.github.e16din:humanoid:1.0.0'
}
```



