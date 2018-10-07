# Grinnell AppDev Android-Training-Fall-2018

![screen shot 2018-09-16 at 12 22 49 pm](https://user-images.githubusercontent.com/20831683/45599089-48662500-b9ab-11e8-927a-c8d5f31b88f2.png)

# Week 3
Topics covered
 - Activities [Review here]
 - Activities hierachy / stack [Review here]
 - R.java files [Review here]

#### ListViews
 - [Revisit ListViews here](https://www.tutorialspoint.com/android/android_list_view.htm)

## Summary of listviews: In order to create a ListView in your activity you need to do the following. 

#### Activity
- Create a new Activity that will host the Listview 

#### Xml files
- In the Activity's xml file, include the ListView as a child view
- Create an item_view xml file separately. An item_view is an xml that defines
what each row of the listview will look like (Usually a textview)

#### Finally 
- Inside the Activity in your onCreate method, create an Adapter that will add stuff to our ListView from an array/database
or some other data source. If you are obtaining the data from an array, use an ArrayAdapter
- When constructing the Adapter, pass the Context, item_view as well as the array as arguments
- Create a reference to the listview in the Activity, and then call the listview.setAdapter method
and pass the adapter as an argument.
- Assuming you have created the respective xml files, your Activity's onCreate should look something like the code shown below

```
@Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      String[] arr = {"One", "Two", "Three"}; 
      ArrayAdapter adapter = new ArrayAdapter<String>(this, 
         R.layout.item_view, arr);

      ListView listView = (ListView) findViewById(R.id.mobile_list);
      listView.setAdapter(adapter);
   }
   ```

## Readings for next class 
 - [ListViews](https://www.tutorialspoint.com/android/android_list_view.htm)
 - [Data and file storage overview](https://developer.android.com/guide/topics/data/data-storage)

## Exercise for next class
 - If you haven't, please [review ListViews](https://www.tutorialspoint.com/android/android_list_view.htm) before starting this exercise. 
  
   - For this exercise, you are going to create a new app called MyAnimalsApp. The first page should display a list of animal names, ideally 
   5 animals. This means you need to use a ListView. 
   - After you create a listview, use the ListView's setOnItemClickListener method to respond to click events in the listview. You should open
   a new empty activity for each animal clicked. Lastly, make sure that the title bar on the toolbar changes based on the activity you are currently on. For example, if in my list I have an animal called in the list dog and I click that animal name, the app should open a new empty activity and the title bar should read DogActivity.
   - Also make sure you are using good android navigation guidelines i.e you should provide back buttons to the main activity for any sub-activity you create. 

   - If you need some clues, I have added some sample code for my animal app [here](https://github.com/GrinnellAppDev/Android-Training-Fall-2018/tree/master/MyAnimalsApp). You can refer to it if you get stuck.

#### Here are some photos of what your app should look like:
   
![firstpage](https://user-images.githubusercontent.com/20831683/46588014-a510a900-ca5a-11e8-8522-e5c50cb284b3.png)

- If the user clicks on cat, this is the activity that shows up. Do the same should be for each animal. 
![mycatactivity](https://user-images.githubusercontent.com/20831683/46588018-a93cc680-ca5a-11e8-89ad-c3d359e1c5ea.png)







