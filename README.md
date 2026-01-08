# **ZoomLayout Library**

---

ZoomLayout is a lightweight custom Android ViewGroup that enables **zooming, panning, and scrolling** for any child view using **XML-only configuration**. No runtime Kotlin/Java changes are required after setup.

---

## ✨ **Features**

---

- Pinch-to-zoom
- Drag / pan (horizontal & vertical)
- One, two, or three-finger scroll control
- Min & max zoom limits
- Overscroll & over-pinch support
- Works with any child view (TextView, ImageView, layouts)
- Fully configurable via XML attributes
- Single ViewGroup wrapper

  ---

# **Preview**
---
<p align="center">
  <img src="https://github.com/user-attachments/assets/6d6f8142-9385-4014-a63d-0a1e63961e5c"
       alt="Demo GIF"
       width="200">



</p>


## ⚡ **Installation**

**Step 1:** Add JitPack repository to your root build.gradle:

```gradle
maven { url = uri("https://jitpack.io") }
```

**Step 2:** Add the dependency in your app `build.gradle` (example if hosted on JitPack):  

```gradle
dependencies {
	        implementation 'com.github.Excelsior-Technologies-Community:Android_ColorPickerView:1.0.0'

}
```
## ⚡ **attrs file**

```
<?xml version="1.0" encoding="utf-8"?>
<resources>

    <declare-styleable name="ZoomLayout">

        <!-- Enable / Disable -->
        <attr name="zoomEnabled" format="boolean"/>
        <attr name="scrollEnabled" format="boolean"/>
        <attr name="flingEnabled" format="boolean"/>

        <!-- Pan -->
        <attr name="horizontalPanEnabled" format="boolean"/>
        <attr name="verticalPanEnabled" format="boolean"/>

        <!-- Gesture -->
        <attr name="oneFingerScrollEnabled" format="boolean"/>
        <attr name="twoFingersScrollEnabled" format="boolean"/>
        <attr name="threeFingersScrollEnabled" format="boolean"/>

        <!-- Zoom -->
        <attr name="minZoom" format="float"/>
        <attr name="maxZoom" format="float"/>

        <!-- Overscroll -->
        <attr name="overScrollHorizontal" format="boolean"/>
        <attr name="overScrollVertical" format="boolean"/>
        <attr name="overPinchable" format="boolean"/>

        <!-- Animation -->
        <attr name="animationDuration" format="integer"/>

        <!-- Alignment -->
        <attr name="alignment">
            <enum name="center" value="0"/>
            <enum name="topLeft" value="1"/>
        </attr>

        <!-- Click handling -->
        <attr name="hasClickableChildren" format="boolean"/>

    </declare-styleable>

</resources>



```

## ⚡ **Usage**

1. Add in XML 
```

<com.ext.android_zoom_layout.ZoomLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:scrollbars="vertical|horizontal"
    app:zoomEnabled="true"
    app:scrollEnabled="true"
    app:flingEnabled="true"
    app:minZoom="0.7"
    app:maxZoom="2.5"
    app:animationDuration="280"
    app:horizontalPanEnabled="true"
    app:verticalPanEnabled="true"
    app:oneFingerScrollEnabled="true"
    app:twoFingersScrollEnabled="true"
    app:threeFingersScrollEnabled="false"
    app:overScrollHorizontal="true"
    app:overScrollVertical="true"
    app:overPinchable="true"
    app:alignment="center"
    app:hasClickableChildren="false">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Zoomable Text Content"
        android:textSize="18sp"
        android:padding="24dp"/>

</com.ext.android_zoom_layout.ZoomLayout>
```

## **📄 License**

**MIT License**  
```
Copyright (c) 2025 Excelsior Technologies

Permission is hereby granted, free of charge, to any person obtaining a copy  
of this software and associated documentation files (the "Software"), to deal  
in the Software without restriction, including without limitation the rights  
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell  
copies of the Software, and to permit persons to whom the Software is  
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all  
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED **"AS IS"**, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR  
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,  
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
```



  
