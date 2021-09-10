# DateView

![DateView](https://i.ibb.co/8g3sn3d/Screen-Shot-2021-09-10-at-17-49-11.png)
![DateView](https://i.ibb.co/2jpPSJ5/Screen-Shot-2021-09-10-at-17-49-18.png)

## Setup
### Gradle

Add this to your project level `build.gradle`:
```groovy
allprojects {
 repositories {
    maven { url "https://jitpack.io" }
 }
}
```
Add this to your app `build.gradle`:
```groovy
dependencies {
    implementation 'com.github.edtslib:dateview:latest'
}
```
# Usage

The DateView is very easy to use. Just add it to your layout like any other view.
##### Via XML

Here's a basic implementation.

```xml
     <id.co.edtslib.dateview.DateView
                android:id="@+id/dateView"
                app:dateViewFormat="dd-MM-yyyy"
                app:layout_constraintTop_toBottomOf="@id/etName"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                android:layout_marginTop="@dimen/dimen_24dp"
                android:paddingTop="@dimen/dimen_24dp"
                android:paddingBottom="@dimen/dimen_20dp"
                android:paddingStart="@dimen/dimen_12dp"
                android:paddingEnd="@dimen/dimen_12dp"
                android:background="@drawable/bg_edit_text"
                android:drawableEnd="@drawable/ic_date"
                android:hint="@string/birthdate"
                style="@style/RubikRegular.Size14"
                android:textColorHint="@color/colorBlack40"
                android:textColor="@color/colorBlack70"
                app:hint="@string/name"
                android:layout_width="0dp"
                android:layout_height="match_parent" />
```

### Attributes information

##### _app:dateViewFormat_
[string]: date format which shown on text view

### Method for navigation actions on the PINCounterView
You can set minimum and maximum date with this method

```kotlin
    fun setMaxDate(maxDate: Long)
    fun setMinDate(minDate: Long)
```

### Listening for DateView

You can set a listener to be notified when user set date. An example is shown below.

```kotlin
        popupBinding.dateView.delegate = object : DateDelegate {
            override fun onChanged(date: Date) {
                completeProfileViewModel.birthdate = date
            }
        }
```

