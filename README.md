# MVVM Guid For beginner
Used:

1.  Retrofit
2.  Navigation component
3.  Hilt
4.  ViewModel

## Sample functions:
For collect data through Kotlin Flows to reduse code repetation

```kotlin 

    fun <T> LifecycleOwner.collectLatestLifecycleFlow(
        flow: Flow<T>,
        collect: suspend (T) -> Unit
    ) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collectLatest(collect)
            }
        }
    }

    fun <T> /*AppCompatActivity*/LifecycleOwner.collectLifecycleFlow(
        flow: Flow<T>,
        collect: suspend (T) -> Unit
    ) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collect(collect)
            }
        }
    }
 
```

## Sealed class of Type T to handle Result State 

```kotlin 

    sealed class Resource<T>(val data: T? = null, val message: String? = null) {
        class Success<T>(data: T) : Resource<T>(data)
        class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
        class Loading<T>(data: T? = null) : Resource<T>(data)
    }
 
```
