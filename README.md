# Humanoid

Humanoid is handy logger to debug with links (look like at exception stacktrace)

## Using Humanoid

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    "Try to use and give me feedback :)".logH()
}
```

<div align="left">
      <a href="https://www.youtube.com/watch?v=5JcKSEcdQlM">
         <img src="https://img.youtube.com/vi/5JcKSEcdQlM/0.jpg" style="width:100%;">
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



