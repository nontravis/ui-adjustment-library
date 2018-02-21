# **「 UI Adjustment Library 」**

<img src="./pictures/cover.png" width="1000">


[README ENG](./README.md)


#### UI Adjustment Library เป็น library ที่ช่วยแก้ปัญหาการปรับเปลี่ยน UI ได้ตั้งแต่ตอน runtime (ไม่ต้อง rebuild หรือ restart) และยังสามารถเชื่อม business logic ที่มีหลายกรณีในหน้านั้นๆ กับ UI state ซึ่งจะช่วยลดระยะเวลาการทดสอบโค๊ดลงได้ :)

> NOTE: Boolean, Color, Integer, Float และ String เป็นค่าที่เราสามารถปรับเปลียนได้ในโปรเจคนี้


<img src="./pictures/uiadjustment-debug.gif">



## 「 DEMO APPLICATION 」

```
clone repo and build it :)
```


## 「 Installation 」

Gradle

```gradle
compile 'com.github.thekhaeng:ui-adjustment-core-library:1.0.5'
debugCompile 'com.github.thekhaeng:ui-adjustment-debug-library:1.0.8'
releaseCompile 'com.github.thekhaeng:ui-adjustment-release-library:1.0.3'
compile 'com.google.code.gson:gson:2.8.2'
```

## 「 Debug VS Release 」
โปรเจคนี้แยก build สำหรับ **debug** และ **release**
ซึ่งจะทำให้โค๊ดไม่ปนกันจนทำให้แอปบวม

ด้านซ้ายจะเป็นการ **build แบบ debug** ส่วนด้านขวาจะเป็นการ **build แบบ release** (จะไม่มีส่วนของ UI Adjustment)

<img src="./pictures/uiadjustment-debug.png" width="240"> <img src="./pictures/uiadjustment-release.png" width="240">

## 「  วิธี setup ให้ project เรา build debug/release แยกจากกัน 」

ในโปรเจคให้เราไปสร้าง folder เพิ่มคือ **debug** กับ **release** ตามด้านล่าง

```
── <YOUR PROJECT>
 │
 └─ src
     │
     ├─ debug: จะใช้โค๊ดในนี้ตอนเรา build แบบ "debug"
     │
     ├─ main: โค๊ดในนี้จะถูกใช้เสมอ
     │
     └─ release: จะใช้โค๊ดในนี้ตอนเรา build แบบ "release"
```

> **NOTE:** ไม่ต้องทำอะไรเพิ่มเติม แค่นี้เราก็สามารถแยกโค๊ด debug กับ release ออกจากกันได้แล้ว :)




## 「 Usage 」

##### 1. เลือก activity/fragment ที่เราต้องการจะใช้ UI Adjustment
##### 2. สร้าง class ที่ extends [UIActivityAdjustment.class](./app/src/debug/java/com/thekhaeng/library/uiadjustlibrary/UIAdjustMainActivity.java)/[UIFragmentAdjustment.class](./app/src/release/java/com/thekhaeng/library/uiadjustlibrary/UIAdjustMainActivity.java) ไว้ท้ง [debug](./app/src/debug/java/com/thekhaeng/library/uiadjustlibrary/) และ [release](./app/src/release/java/com/thekhaeng/library/uiadjustlibrary/) (ตามตัวอย่าง link)
##### 3. Override createAdjustItemList() เพื่อสร้าง UI Adjustment ผูกกับ ID ตามตัวอย่าง
	
> **NOTE:** class ที่สามารถใช้ได้ **BooleanAdjustment.class, ColorAdjustment.class, IntegerAdjustment.class, RangeFloatAdjustment.class และ StringAdjustment.class**

```java
public class UIAdjustMainActivity extends UIActivityAdjustment<MainActivity>{

	...

    @NonNull
    @Override
    public List<BaseAdjustItem> createAdjustItemList(){
        List<BaseAdjustItem> itemList = new ArrayList<>();
        Map<String, AdjustColor> mapColor = new LinkedHashMap<>();
        mapColor.put( "1", new AdjustColor( "#F44336" ) );
        mapColor.put( "2", new AdjustColor( "#E91E63", true ) );
        mapColor.put( "3", new AdjustColor( "#9C27B0" ) );

        Map<String, AdjustInteger> mapInteger = new LinkedHashMap<>();
        mapInteger.put( "Theme 1", new AdjustInteger( MainActivity.THEME_1, true ) );
        mapInteger.put( "Theme 2", new AdjustInteger( MainActivity.THEME_2 ) );
        mapInteger.put( "Theme 3", new AdjustInteger( MainActivity.THEME_3 ) );

        Map<String, AdjustString> mapString = new LinkedHashMap<>();
        mapString.put( "Message 1", new AdjustString( getActivity().getString( R.string.message_1 ), true ) );
        mapString.put( "Message 2", new AdjustString( getActivity().getString( R.string.message_2 ) ) );
        mapString.put( "Message 3", new AdjustString( getActivity().getString( R.string.message_3 ) ) );


        itemList.add( ColorAdjustment.create( R.id.tv_color, "Color View", mapColor ) );
        itemList.add( BooleanAdjustment.create( R.id.tv_show, "Show View", true ) );
        itemList.add( IntegerAdjustment.create( THEME_ID, "Choose Theme", mapInteger ) );
        itemList.add( RangeFloatAdjustment.create( R.id.tv_size, "Text Size", new AdjustRangeFloat( 12, 42, 1, 16 ) ) );
        itemList.add( StringAdjustment.create( R.id.tv_message, "Message", mapString ) );
        return itemList;
    }
	...

}

```

##### 4. ดักฟังค่าที่ adjust เสร็จแล้ว ดูตัวอย่างได้ที่ [UIActivityAdjustment.class](./app/src/debug/java/com/thekhaeng/library/uiadjustlibrary/UIAdjustMainActivity.java)

```java
public class UIAdjustMainActivity extends UIActivityAdjustment<MainActivity>{
	
	...


    @Override
    protected void onColor( Activity activity, int id, @ColorInt int color ){
        super.onColor( activity, id, color );
        ...
    }

    @Override
    protected void onBoolean( Activity activity, int id, boolean value ){
        super.onBoolean( activity, id, value );
		 ...
    }

    @Override
    protected void onInteger( Activity activity, int id, int value ){
        super.onInteger( activity, id, value );
	    ...
    }

    @Override
    protected void onRangeFloat( Activity activity, int id, float value ){
        super.onRangeFloat( activity, id, value );
        ...
    }

    @Override
    protected void onString( Activity activity, int id, String value ){
        super.onString( activity, id, value );
        ...
    }
    
}
```

##### 5. ผูก class ที่สร้างไว้กับ activity/fragment ตามตัวอย่าง
```java
public class MainActivity extends AppCompatActivity{

    private View fab;

    @Override
    protected void onCreate( Bundle savedInstanceState ){
 		 ...

        fab = findViewById( R.id.fab_adjust );

        if( BuildConfig.DEBUG ){
            UIAdjustMainActivity
                    .create( this, fab )
                    .setDelayMillisTime( 500 )  // delay listener
                    .setUseLocalStorage( true, true );
        }

    }
	...
}
```

## 「 Full Option 」

```java
UIAdjustMainActivity
       .create( this, fab )
       .setTitle( "Example Title" )
       .setDelayMillisTime( 500 )
       .setUseLocalStorage( true, true );

```
**`setTitle( title )`**

* string: **title**: เปลี่ยน default title

**`create( activity/fragment, view )`**

* **activity/fragment**: ฝั่ง activity/fragment เพื่อนำไปใช้ใน class
* **view**: ไว้คลิ๊กเพื่อแสดง UI Adjustment `NOTE: ถ้า build แบบ release จะซ้อน view ใหัอัตโนมัต`


**`setDelayMillisTime( millis )`**
 
* ทำการ delay ตัว listener หลัง adjustment เสร็จสินเพื่อดูการเปลี่ยนแปลงของ UI


**`setUseLocalStorage( useLocalStorage, bindDataImmediately )`** 

* boolean: **useLocalStorage**: เปิดใช้ local storage (sharepreference) ในการเก็บค่าเมื่อ adjustment เสร็จ
* boolean: **bindDataImmediately**: ให้ทำการเซ็ตข้อมูลเข้ากับ UI ทันทีที่เปิดใช้งานหน้านั้นๆ


# Licence

Copyright 2017 TheKhaeng

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License. You may obtain a copy of the License in the LICENSE file, or at:

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.


### Developed By Thai android developer.


<img src="./pictures/profile2_circle.png" width="170"> ![alt text](./pictures/thekhaeng_logo.png)


Follow [facebook.com/thekhaeng.io](https://www.facebook.com/thekhaeng.io) on Facebook page.
or [@nonthawit](https://medium.com/@nonthawit) at my Medium blog. :)

For contact, shoot me an email at nonthawit.thekhaeng@gmail.com

